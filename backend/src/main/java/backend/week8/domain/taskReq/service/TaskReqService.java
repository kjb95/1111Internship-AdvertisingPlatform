package backend.week8.domain.taskReq.service;

import backend.week8.domain.member.entity.Member;
import backend.week8.domain.member.repository.MemberRepository;
import backend.week8.domain.taskReq.dto.request.CreateTaskReqRequestDto;
import backend.week8.domain.taskReq.dto.request.FindTaskReqHistoryResponseDto;
import backend.week8.domain.taskReq.dto.response.TaskReqResponseDto;
import backend.week8.domain.taskReq.dto.response.UploadTaskReqFileResponseDto;
import backend.week8.domain.taskReq.entity.TaskReq;
import backend.week8.domain.taskReq.repository.TaskReqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static backend.week8.common.constant.Constant.NOT_FOUND_MEMBER;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TaskReqService {
	private final TaskReqRepository taskReqRepository;
	private final MemberRepository memberRepository;
	@Value("${file.dir}")
	private String fileDir;

	/**
	 * 테스크 작업 요청
	 */
	@Transactional
	public void createTaskReq(CreateTaskReqRequestDto createTaskReqRequestDto) {
		Member member = memberRepository.findById(createTaskReqRequestDto.getMemberId())
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MEMBER));
		TaskReq taskReq = new TaskReq(member, createTaskReqRequestDto.getTaskName(), createTaskReqRequestDto.getTaskReqFile());
		taskReqRepository.save(taskReq);
	}

	/**
	 * 테스크 요청 파일 삭제
	 */
	@Transactional
	public void deleteTaskReqFile(String taskReqFile) {
		File file = new File(fileDir + taskReqFile);
		file.delete();
	}

	/**
	 * 테스크 요청 파일 업로드
	 */
	@Transactional
	public UploadTaskReqFileResponseDto uploadTaskReqFile(MultipartFile file) throws IOException {
		String saveFileName = createSaveFileName(file.getOriginalFilename());
		String fullPath = fileDir + saveFileName;
		file.transferTo(new File(fullPath));
		return new UploadTaskReqFileResponseDto(saveFileName);
	}

	private String createSaveFileName(String fileName) {
		String uuid = UUID.randomUUID()
				.toString();
		String extension = fileName.split("\\.")[1];
		return uuid + "." + extension;
	}

	/**
	 * 작업 요청 내역 조회
	 */
	public FindTaskReqHistoryResponseDto findTaskReqHistory() {
		List<TaskReqResponseDto> taskReqHistory = taskReqRepository.findAll()
				.stream()
				.map(this::createTaskReqResponseDto)
				.collect(Collectors.toList());
		return new FindTaskReqHistoryResponseDto(taskReqHistory);
	}

	private TaskReqResponseDto createTaskReqResponseDto(TaskReq task) {
		String status = task.getStatus()
				.getDescription();
		String registrant = task.getMember()
				.getMemberId();
		String regTime = task.getReqTime()
				.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
		return new TaskReqResponseDto(task.getId(), task.getName(), status, registrant, regTime, task.getReqFilePath());
	}
}
