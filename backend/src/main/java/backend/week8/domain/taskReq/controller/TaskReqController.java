package backend.week8.domain.taskReq.controller;

import backend.week8.domain.taskReq.dto.request.CreateTaskReqRequestDto;
import backend.week8.domain.taskReq.dto.request.FindTaskReqHistoryResponseDto;
import backend.week8.domain.taskReq.dto.response.UploadTaskReqFileResponseDto;
import backend.week8.domain.taskReq.service.TaskReqService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/taskReq")
public class TaskReqController {
	private final TaskReqService taskReqService;
	@Value("${file.dir}")
	private String fileDir;

	/**
	 * 테스크 요청 파일 업로드
	 */
	@PostMapping("/file")
	public ResponseEntity<UploadTaskReqFileResponseDto> uploadTaskReqFile(@RequestParam MultipartFile file) throws IOException {
		UploadTaskReqFileResponseDto uploadTaskReqFileResponseDto = taskReqService.uploadTaskReqFile(file);
		return ResponseEntity.ok()
				.body(uploadTaskReqFileResponseDto);
	}

	/**
	 * 테스크 작업 요청
	 */
	@PostMapping
	public ResponseEntity<Void> createTaskReq(@RequestBody CreateTaskReqRequestDto createTaskReqRequestDto) {
		taskReqService.createTaskReq(createTaskReqRequestDto);
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 테스크 요청 파일 삭제
	 */
	@DeleteMapping
	public ResponseEntity<Void> deleteTaskReqFile(@RequestParam String taskReqFile) {
		taskReqService.deleteTaskReqFile(taskReqFile);
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 작업 요청 내역 조회
	 */
	@GetMapping("/all")
	public ResponseEntity<FindTaskReqHistoryResponseDto> findTaskReqHistory() {
		FindTaskReqHistoryResponseDto findTaskReqHistoryResponseDto = taskReqService.findTaskReqHistory();
		return ResponseEntity.ok()
				.body(findTaskReqHistoryResponseDto);
	}

	/**
	 * 테스크 요청 파일 다운로드
	 */
	@GetMapping("/file")
	public ResponseEntity<Resource> downloadTaskReqFile(@RequestParam String filename) throws MalformedURLException {
		return ResponseEntity.ok()
				.body(new UrlResource("file:" + fileDir + filename));
	}
}
