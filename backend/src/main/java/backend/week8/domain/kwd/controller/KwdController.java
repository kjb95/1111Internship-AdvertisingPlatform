package backend.week8.domain.kwd.controller;

import backend.week8.domain.kwd.dto.request.RegisterKwdRequestDto;
import backend.week8.domain.kwd.dto.request.UpdateManualCnrKwdYnOff;
import backend.week8.domain.kwd.dto.response.FindAllKwdResponseDto;
import backend.week8.domain.kwd.service.KwdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kwd")
public class KwdController {
	private final KwdService kwdService;

	/**
	 * 모든 키워드 조회(수동 검수 키워드 1)
	 */
	@GetMapping
	public ResponseEntity<FindAllKwdResponseDto> findAllKwd(@RequestParam String kwdName) {
		FindAllKwdResponseDto findAllKwdResponseDto = kwdService.findAllKwd(kwdName);
		return ResponseEntity.ok()
				.body(findAllKwdResponseDto);
	}

	/**
	 * 키워드 등록
	 */
	@PostMapping
	public ResponseEntity<Void> registerKwd(@RequestBody RegisterKwdRequestDto registerKwdRequestDto) {
		kwdService.registerKwd(registerKwdRequestDto.getKwdName());
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 수동 검수 키워드 여부 끄기
	 */
	@PutMapping("/manualCnrKwdYnOff")
	public ResponseEntity<Void> updateManualCnrKwdYnOff(@RequestBody UpdateManualCnrKwdYnOff updateManualCnrKwdYnOff) {
		kwdService.updateManualCnrKwdYnOff(updateManualCnrKwdYnOff.getKwdId());
		return ResponseEntity.ok()
				.build();
	}
}
