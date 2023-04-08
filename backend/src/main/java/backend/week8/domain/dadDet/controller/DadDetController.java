package backend.week8.domain.dadDet.controller;

import backend.week8.domain.dadDet.dto.request.FindKeywordsInItemRequestDto;
import backend.week8.domain.dadDet.dto.request.UpdateDadDetActOffRequestDto;
import backend.week8.domain.dadDet.dto.request.UpdateDadDetUseConfigRequestDto;
import backend.week8.domain.dadDet.dto.response.FindAdStatusResponseDto;
import backend.week8.domain.dadDet.dto.response.FindKeywordsInItemResponseDto;
import backend.week8.domain.dadDet.service.DadDetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dadDet")
public class DadDetController {
	private final DadDetService dadDetService;

	/**
	 * 한 상품이 가지는 키워드 조회
	 */
	@PostMapping("/kwd/search")
	public ResponseEntity<FindKeywordsInItemResponseDto> findKeywordsInItem(@RequestBody FindKeywordsInItemRequestDto findKeywordsInItemRequestDto) {
		FindKeywordsInItemResponseDto findKeywordsInItemResponseDto = dadDetService.findKeywordsInItem(findKeywordsInItemRequestDto);
		return ResponseEntity.ok()
				.body(findKeywordsInItemResponseDto);
	}

	/**
	 * 직접광고 사용 설정 여부 변경
	 */
	@PutMapping("/useConfig")
	public ResponseEntity<Void> updateDadDetUseConfig(@RequestBody UpdateDadDetUseConfigRequestDto updateDadDetUseConfigRequestDto) {
		dadDetService.updateDadDetUseConfig(updateDadDetUseConfigRequestDto);
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 직접광고 활성 여부 끄기
	 */
	@PutMapping("/actOff")
	public ResponseEntity<Void> updateDadDetActOff(@RequestBody UpdateDadDetActOffRequestDto updateDadDetActOffRequestDto) {
		dadDetService.updateDadDetActOff(updateDadDetActOffRequestDto);
		return ResponseEntity.ok()
				.build();
	}

	/**
	 * 광고 현황(직접광고 상세 ID, 상품 명, 키워드 명, 성인 여부) 조회
	 */
	@GetMapping("/adStatus")
	public ResponseEntity<FindAdStatusResponseDto> findAdStatus() {
		FindAdStatusResponseDto findAdStatusResponseDto = dadDetService.findAdStatus();
		return ResponseEntity.ok()
				.body(findAdStatusResponseDto);
	}

}
