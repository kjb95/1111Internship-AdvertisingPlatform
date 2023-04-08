package backend.week8.domain.ad.controller;

import backend.week8.domain.ad.dto.request.RegisterAdRequestDto;
import backend.week8.domain.ad.dto.request.UpdateAdActOffRequestDto;
import backend.week8.domain.ad.dto.request.UpdateAdUseConfigAndDadUseConfigRequestDto;
import backend.week8.domain.ad.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ad")
public class AdController {
	private final AdService adService;

	/**
	 * 광고 등록
	 */
	@PostMapping
	public ResponseEntity<Void> registerAd(@RequestBody RegisterAdRequestDto registerAdRequestDto) {
		adService.registerAd(registerAdRequestDto);
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 광고 사용 설정 여부, 직접광고 사용 설정 여부 변경
	 */
	@PutMapping("/useConfig")
	public ResponseEntity<Void> updateAdUseConfigAndDadUseConfig(@RequestBody UpdateAdUseConfigAndDadUseConfigRequestDto updateAdUseConfigAndDadUseConfigRequestDto) {
		adService.updateAdUseConfigAndDadUseConfig(updateAdUseConfigAndDadUseConfigRequestDto);
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 광고 활성 여부 끄기
	 */
	@PutMapping("/actOff")
	public ResponseEntity<Void> updateAdActOff(@RequestBody UpdateAdActOffRequestDto updateAdActOffRequestDto) {
		adService.updateAdActOff(updateAdActOffRequestDto);
		return ResponseEntity.ok()
				.build();
	}
}
