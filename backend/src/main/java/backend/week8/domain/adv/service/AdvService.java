package backend.week8.domain.adv.service;

import backend.week8.domain.adv.dto.request.UpdateAdIngActRequestDto;
import backend.week8.domain.adv.dto.request.UpdateDayLimitBudgetRequestDto;
import backend.week8.domain.adv.dto.response.FindAdvResponseDto;
import backend.week8.domain.adv.entity.Adv;
import backend.week8.domain.adv.repository.AdvRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

 import static backend.week8.common.constant.Constant.NOT_FOUND_ADV;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AdvService {

	private final AdvRepository advRepository;

	/**
	 * 광고주 조회
	 */
	public FindAdvResponseDto findAdv(String advId) {
		Adv adv = advRepository.findById(advId)
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ADV));
		ModelMapper modelMapper = new ModelMapper();
		FindAdvResponseDto findAdvResponseDto = modelMapper.map(adv, FindAdvResponseDto.class);
		if (adv.getAdIngActYn() == 0) {
			findAdvResponseDto.setAdIngActYn(false);
		}
		return findAdvResponseDto;
	}

	/**
	 * 일일 허용 예산 설정 변경
	 */
	@Transactional
	public void updateDayLimitBudget(UpdateDayLimitBudgetRequestDto updateDayLimitBudgetRequestDto) {
		Adv adv = advRepository.findById(updateDayLimitBudgetRequestDto.getAdvId())
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ADV));
		advRepository.save(new Adv(adv.getAdvId(), adv.getMember(), adv.getAdIngActYn(), adv.getBalance(), adv.getEventMoney(), updateDayLimitBudgetRequestDto.getDayLimitBudget()));
	}

	/**
	 * 광고주의 광고 진행 활성 여부 변경
	 */
	@Transactional
	public void updateAdIngAct(UpdateAdIngActRequestDto updateAdIngActRequestDto) {
		Adv adv = advRepository.findById(updateAdIngActRequestDto.getAdvId())
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ADV));
		advRepository.save(new Adv(adv.getAdvId(), adv.getMember(), updateAdIngActRequestDto.isOn() ? 1 : 0, adv.getBalance(), adv.getEventMoney(), adv.getDayLimitBudget()));
	}
}
