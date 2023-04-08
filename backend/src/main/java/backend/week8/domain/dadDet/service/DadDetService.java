package backend.week8.domain.dadDet.service;

import backend.week8.domain.dadDet.dto.request.FindKeywordsInItemRequestDto;
import backend.week8.domain.dadDet.dto.request.UpdateDadDetActOffRequestDto;
import backend.week8.domain.dadDet.dto.request.UpdateDadDetUseConfigRequestDto;
import backend.week8.domain.dadDet.dto.response.AdStatusResponseDto;
import backend.week8.domain.dadDet.dto.response.FindAdStatusResponseDto;
import backend.week8.domain.dadDet.dto.response.FindKeywordsInItemResponseDto;
import backend.week8.domain.dadDet.dto.response.KeywordInItemResponseDto;
import backend.week8.domain.dadDet.entity.DadDet;
import backend.week8.domain.dadDet.repository.DadDetRepository;
import backend.week8.domain.kwd.entity.Kwd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DadDetService {
	private final DadDetRepository dadDetRepository;

	/**
	 * 한 상품이 가지는 키워드 조회
	 */
	public FindKeywordsInItemResponseDto findKeywordsInItem(FindKeywordsInItemRequestDto findKeywordsInItemRequestDto) {
		List<DadDet> dadDets = dadDetRepository.findDadDetInItemLikeKeywordName(findKeywordsInItemRequestDto.getItemId(), findKeywordsInItemRequestDto.getKeywordNameSearch());
		List<KeywordInItemResponseDto> keywords = dadDets.stream()
				.map((this::createKeywordInItemResponseDto))
				.collect(Collectors.toList());
		return new FindKeywordsInItemResponseDto(keywords);
	}

	private KeywordInItemResponseDto createKeywordInItemResponseDto(DadDet dadDet) {
		Kwd kwd = dadDet.getKwd();
		String isOn = dadDet.getDadUseConfigYn() == 1 ? "ON" : "OFF";
		return new KeywordInItemResponseDto(kwd.getKwdId(), kwd.getKwdName(), isOn);
	}

	/**
	 * 직접광고 사용 설정 여부 변경
	 */
	@Transactional
	public void updateDadDetUseConfig(UpdateDadDetUseConfigRequestDto updateDadDetUseConfigRequestDto) {
		dadDetRepository.updateUseConfigByKwdIds(updateDadDetUseConfigRequestDto.getKwdIds(), updateDadDetUseConfigRequestDto.isOn() ? 1 : 0);
	}

	/**
	 * 직접광고 활성 여부 끄기
	 */
	@Transactional
	public void updateDadDetActOff(UpdateDadDetActOffRequestDto updateDadDetActOffRequestDto) {
		dadDetRepository.updateDadDetActOff(updateDadDetActOffRequestDto.getKwdIds());
	}

	/**
	 * 광고 현황(직접광고 상세 ID, 상품 명, 키워드 명, 성인 여부) 조회
	 */
	public FindAdStatusResponseDto findAdStatus() {
		List<AdStatusResponseDto> adStatus = dadDetRepository.findAdStatus();
		return new FindAdStatusResponseDto(adStatus);
	}
}
