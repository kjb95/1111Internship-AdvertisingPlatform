package backend.week8.domain.adv.controller;

import backend.week8.domain.adv.dto.request.UpdateAdIngActRequestDto;
import backend.week8.domain.adv.dto.request.UpdateDayLimitBudgetRequestDto;
import backend.week8.domain.adv.dto.response.FindAdvResponseDto;
import backend.week8.domain.adv.service.AdvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/adv")
public class AdvController {
	private final AdvService advService;

	/**
	 * 광고주 조회
	 */
	@GetMapping("/{advId}")
	public ResponseEntity<FindAdvResponseDto> findAdv(@PathVariable String advId) {
		FindAdvResponseDto findAdvResponseDto = advService.findAdv(advId);
		return ResponseEntity.ok()
				.body(findAdvResponseDto);
	}

	/**
	 * 일일 허용 예산 설정 변경
	 */
	@PostMapping("/dayLimitBudget")
	public ResponseEntity<Void> updateDayLimitBudget(@RequestBody UpdateDayLimitBudgetRequestDto updateDayLimitBudgetRequestDto) {
		advService.updateDayLimitBudget(updateDayLimitBudgetRequestDto);
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 광고주의 광고 진행 활성 여부 변경
	 */
	@PostMapping("/adIngActYn")
	public ResponseEntity<Void> updateAdIngAct(@RequestBody UpdateAdIngActRequestDto updateAdIngActRequestDto) {
		advService.updateAdIngAct(updateAdIngActRequestDto);
		return ResponseEntity.ok()
				.build();
	}

}
