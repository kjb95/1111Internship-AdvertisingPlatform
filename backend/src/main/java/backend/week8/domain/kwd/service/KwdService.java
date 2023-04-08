package backend.week8.domain.kwd.service;

import backend.week8.domain.kwd.dto.response.FindAllKwdResponseDto;
import backend.week8.domain.kwd.dto.response.KwdResponseDto;
import backend.week8.domain.kwd.entity.Kwd;
import backend.week8.domain.kwd.repository.KwdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static backend.week8.common.constant.Constant.DUPLICATED_CHECK_KWD;
import static backend.week8.common.constant.Constant.NOT_FOUND_KWD;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class KwdService {
	private final KwdRepository kwdRepository;

	/**
	 * 모든 키워드 조회(수동 검수 키워드 1)
	 */
	public FindAllKwdResponseDto findAllKwd(String kwdName) {
		List<KwdResponseDto> kwds = kwdRepository.findByManualCnrKwdYnAndKwdNameContaining(1, kwdName)
				.stream()
				.map(kwd -> new KwdResponseDto(kwd.getKwdId(), kwd.getKwdName()))
				.collect(Collectors.toList());
		return new FindAllKwdResponseDto(kwds);
	}

	/**
	 * 키워드 등록
	 */
	@Transactional
	public void registerKwd(String kwdName) {
		Optional<Kwd> kwdOptional = kwdRepository.findKwdByKwdName(kwdName);
		if (!kwdOptional.isPresent()) {
			kwdRepository.save(new Kwd(kwdName));
			return;
		}
		Kwd kwd = kwdOptional.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_KWD));
		if (kwd.getManualCnrKwdYn() == 1) {
			throw new IllegalArgumentException(DUPLICATED_CHECK_KWD);
		}
		kwdRepository.save(new Kwd(kwd.getKwdId(), kwd.getKwdName(), kwd.getSellPossKwdYn(), 1));
	}

	/**
	 * 수동 검수 키워드 여부 끄기
	 */
	@Transactional
	public void updateManualCnrKwdYnOff(Long kwdId) {
		Kwd kwd = kwdRepository.findById(kwdId)
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_KWD));
		kwdRepository.save(new Kwd(kwd.getKwdId(), kwd.getKwdName(), kwd.getSellPossKwdYn(), 0));
	}
}
