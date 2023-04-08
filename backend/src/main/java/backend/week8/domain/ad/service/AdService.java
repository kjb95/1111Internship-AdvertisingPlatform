package backend.week8.domain.ad.service;

import backend.week8.domain.ad.dto.request.RegisterAdKeywordRequestDto;
import backend.week8.domain.ad.dto.request.RegisterAdRequestDto;
import backend.week8.domain.ad.dto.request.UpdateAdActOffRequestDto;
import backend.week8.domain.ad.dto.request.UpdateAdUseConfigAndDadUseConfigRequestDto;
import backend.week8.domain.ad.entity.Ad;
import backend.week8.domain.ad.repository.AdRepository;
import backend.week8.domain.adv.entity.Adv;
import backend.week8.domain.adv.repository.AdvRepository;
import backend.week8.domain.agroup.entity.AGroup;
import backend.week8.domain.agroup.repository.AGroupRepository;
import backend.week8.domain.cnrReq.entity.CnrReq;
import backend.week8.domain.cnrReq.repository.CnrReqRepository;
import backend.week8.domain.dadDet.entity.DadDet;
import backend.week8.domain.dadDet.repository.DadDetRepository;
import backend.week8.domain.dadDetBid.entity.DadDetBid;
import backend.week8.domain.dadDetBid.repository.DadDetBidRepository;
import backend.week8.domain.item.entity.Item;
import backend.week8.domain.item.repository.ItemRepository;
import backend.week8.domain.kwd.entity.Kwd;
import backend.week8.domain.kwd.repository.KwdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static backend.week8.common.constant.Constant.NOT_FOUND_ADV;
import static backend.week8.common.constant.Constant.NOT_FOUND_ITEM;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AdService {
	private static final String NUMBER_REGEX = "-?\\d+(\\.\\d+)?";
	private static final String UNSELLABLE_KEYWORD = "는(은) 판매가 불가능한 키워드 입니다";
	private final AdRepository adRepository;
	private final ItemRepository itemRepository;
	private final AdvRepository advRepository;
	private final AGroupRepository aGroupRepository;
	private final KwdRepository kwdRepository;
	private final CnrReqRepository cnrReqRepository;
	private final DadDetBidRepository dadDetBidRepository;
	private final DadDetRepository dadDetRepository;

	/**
	 * 광고 등록
	 */
	@Transactional
	public void registerAd(RegisterAdRequestDto registerAdRequestDto) {
		AGroup aGroup = registerAGroupById(registerAdRequestDto.getAgroupId());
		Ad ad = registerAd(aGroup, registerAdRequestDto.getItemId(), registerAdRequestDto.getAdvId());
		registerDirectAdDetails(ad, registerAdRequestDto.getKeywordList());
	}

	private AGroup registerAGroupById(String aGroupId) {
		if (!aGroupId.matches(NUMBER_REGEX)) {
			return aGroupRepository.save(new AGroup(aGroupId));
		}
		AGroup aGroup = aGroupRepository.findById(Long.parseLong(aGroupId))
				.orElseGet(() -> new AGroup(aGroupId));
		return aGroupRepository.save(aGroup);
	}

	private Ad registerAd(AGroup aGroup, long itemId, String advId) {
		Item item = itemRepository.findById(itemId)
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ITEM));
		Adv adv = advRepository.findById(advId)
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ADV));
		return adRepository.save(new Ad(aGroup, item, adv));
	}

	private void registerDirectAdDetails(Ad ad, List<RegisterAdKeywordRequestDto> keywordList) {
		keywordList.forEach(keyword -> registerDirectAdDetails(ad, keyword));
	}

	private void registerDirectAdDetails(Ad ad, RegisterAdKeywordRequestDto keyword) {
		Kwd kwd = kwdRepository.findKwdByKwdName(keyword.getKeywordName())
				.orElseGet(() -> new Kwd(keyword.getKeywordName()));
		if (kwd.getSellPossKwdYn() == 0) {
			throw new IllegalArgumentException(kwd.getKwdName() + UNSELLABLE_KEYWORD);
		}
		CnrReq cnrReq = cnrReqRepository.save(new CnrReq(kwd.getManualCnrKwdYn()));
		kwd = kwdRepository.save(kwd);
		DadDet dadDet = new DadDet(ad, kwd, cnrReq);
		dadDetBidRepository.save(new DadDetBid(dadDet, keyword.getBid()));
		cnrReq.updateDadDetId(dadDet.getDadDetId());
	}

	/**
	 * 광고 사용 설정 여부, 직접광고 사용 설정 여부 변경
	 */
	@Transactional
	public void updateAdUseConfigAndDadUseConfig(UpdateAdUseConfigAndDadUseConfigRequestDto updateAdUseConfigAndDadUseConfigRequestDto) {
		int isOn = updateAdUseConfigAndDadUseConfigRequestDto.isOn() ? 1 : 0;
		adRepository.updateUseConfig(updateAdUseConfigAndDadUseConfigRequestDto.getItemIds(), isOn);
		dadDetRepository.updateUseConfigByItemIds(updateAdUseConfigAndDadUseConfigRequestDto.getItemIds(), isOn);
	}

	/**
	 * 광고 활성 여부 끄기
	 */
	@Transactional
	public void updateAdActOff(UpdateAdActOffRequestDto updateAdActOffRequestDto) {
		adRepository.updateActOff(updateAdActOffRequestDto.getItemIds());
	}
}
