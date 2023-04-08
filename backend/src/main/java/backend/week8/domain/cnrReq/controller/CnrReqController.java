package backend.week8.domain.cnrReq.controller;

import backend.week8.domain.cnrReq.dto.request.UpdateCheckProcessRequestDto;
import backend.week8.domain.cnrReq.dto.response.FindAllCnrFailCauseResponseDto;
import backend.week8.domain.cnrReq.service.CnrReqService;
import backend.week8.domain.dadDet.dto.response.FindAllAdCheckListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cnrReq")
public class CnrReqController {
	private final CnrReqService cnrReqService;

	/**
	 * 검수 진행 상태, 검수 처리 시간, 검수 완료 여부, 직접광고 검수 상태, 검수 실패 사유, 검수 실패 코멘트 변경
	 */
	@PutMapping
	public ResponseEntity<Void> updateCheckProcess(@RequestBody UpdateCheckProcessRequestDto updateCheckProcessRequestDto) {
		cnrReqService.updateCheckProcess(updateCheckProcessRequestDto);
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 모든 검수 실패 사유 조회
	 */
	@GetMapping("/cnrFailCause")
	public ResponseEntity<FindAllCnrFailCauseResponseDto> findAllCnrFailCause() {
		FindAllCnrFailCauseResponseDto findAllCnrFailCauseResponseDto = cnrReqService.findAllCnrFailCause();
		return ResponseEntity.ok()
				.body(findAllCnrFailCauseResponseDto);
	}

	/**
	 * 광고 검수 대상 리스트 조회
	 */
	@GetMapping("/checkList")
	public ResponseEntity<FindAllAdCheckListResponseDto> findAllAdCheckList(@RequestParam String kwdName) {
		FindAllAdCheckListResponseDto findAllAdCheckListResponseDto = cnrReqService.findAllAdCheckList(kwdName);
		return ResponseEntity.ok()
				.body(findAllAdCheckListResponseDto);
	}
}
