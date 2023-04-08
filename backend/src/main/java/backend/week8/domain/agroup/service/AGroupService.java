package backend.week8.domain.agroup.service;

import backend.week8.domain.ad.repository.AdRepository;
import backend.week8.domain.agroup.dto.UpdateAdGroupUseConfigRequestDto;
import backend.week8.domain.agroup.dto.request.FindAdGroupRequestDto;
import backend.week8.domain.agroup.dto.request.UpdateAdGroupActOffRequestDto;
import backend.week8.domain.agroup.dto.request.UpdateAdGroupNameRequestDto;
import backend.week8.domain.agroup.dto.response.*;
import backend.week8.domain.agroup.entity.AGroup;
import backend.week8.domain.agroup.repository.AGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static backend.week8.common.constant.Constant.DUPLICATED_ADGROUP_NAME;
import static backend.week8.common.constant.Constant.NOT_FOUND_AD_GROUP;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AGroupService {
	private final AGroupRepository aGroupRepository;
	private final AdRepository adRepository;

	/**
	 * 모든 광고 그룹의 아이디와 이름만 조회
	 */
	public FindAllAdGroupResponseDto findAllAdGroup() {
		List<AdGroupResponseDto> aGroups = aGroupRepository.findAllByAgroupActYn(1)
				.stream()
				.map(aGroup -> new AdGroupResponseDto(aGroup.getAgroupId(), aGroup.getAgroupName()))
				.collect(Collectors.toList());
		return new FindAllAdGroupResponseDto(aGroups);
	}

	/**
	 * 조건에 따른 그룹 검색
	 */
	public FindAllAdGroupByAdGroupNameResponseDto findAllAdGroupsByAdGroupName(String groupNameCondition) {
		List<FindAdGroupsResponseDto> findAdGroupsResponseDtos = aGroupRepository.findAdGroups(groupNameCondition);
		List<AdGroupByAdGroupNameResponseDto> adGroups = findAdGroupsResponseDtos.stream()
				.map(group -> createAGroupDto(group))
				.collect(Collectors.toList());
		return new FindAllAdGroupByAdGroupNameResponseDto(adGroups);
	}

	private AdGroupByAdGroupNameResponseDto createAGroupDto(FindAdGroupsResponseDto group) {
		String isGroupOn = group.getAgroupUseConfigYn() == 1 ? "ON" : "OFF";
		String itemCountLiveAndAll = group.getCountAdUseConfig() + " / " + group.getCountAdAct();
		return new AdGroupByAdGroupNameResponseDto(group.getKey(), group.getAgroupName(), isGroupOn, itemCountLiveAndAll);
	}

	/**
	 * 광고그룹 사용 설정 변경
	 */
	@Transactional
	public void updateAdGroupUseConfig(UpdateAdGroupUseConfigRequestDto updateAdGroupUseConfigRequestDto) {
		int useConfig = updateAdGroupUseConfigRequestDto.isOn() ? 1 : 0;
		aGroupRepository.updateUseConfig(updateAdGroupUseConfigRequestDto.getAdGroupIds(), useConfig);
	}

	/**
	 * 광고그룹 등록
	 */
	@Transactional
	public void registerAGroupByName(String agroupName) {
		if (aGroupRepository.existsByAgroupName(agroupName)) {
			throw new IllegalArgumentException(DUPLICATED_ADGROUP_NAME);
		}
		aGroupRepository.save(new AGroup(agroupName));
	}

	/**
	 * 광고 그룹 활성 여부 끄기
	 */
	@Transactional
	public void updateAdGroupActOff(UpdateAdGroupActOffRequestDto updateAdGroupActOffRequestDto) {
		aGroupRepository.updateActOff(updateAdGroupActOffRequestDto.getAdGroupIds());
	}

	/**
	 * 광고 그룹 한개 조회
	 */
	public FindAdGroupResponseDto findAdGroup(FindAdGroupRequestDto findAdGroupRequestDto) {
		AGroup aGroup = aGroupRepository.findByAgroupIdAndAgroupActYn(findAdGroupRequestDto.getAdGroupId(), 1)
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_AD_GROUP));
		int agroupItemsCount = adRepository.countItemsInAdGroup(aGroup.getAgroupId());
		return new FindAdGroupResponseDto(aGroup.getAgroupName(), aGroup.getAgroupUseConfigYn(), agroupItemsCount, aGroup.getRegTime());
	}

	/**
	 * 광고 그룹명 변경
	 */
	@Transactional
	public void updateAdGroupName(UpdateAdGroupNameRequestDto updateAdGroupNameRequestDto) {
		boolean isExistAgroupName = aGroupRepository.existsByAgroupName(updateAdGroupNameRequestDto.getAdGroupName());
		if (isExistAgroupName) {
			throw new IllegalArgumentException(DUPLICATED_ADGROUP_NAME);
		}
		AGroup aGroup = aGroupRepository.findById(updateAdGroupNameRequestDto.getAdGroupId())
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_AD_GROUP));
		aGroupRepository.save(new AGroup(aGroup.getAgroupId(), updateAdGroupNameRequestDto.getAdGroupName(), aGroup.getRegTime(), aGroup.getAgroupActYn(), aGroup.getAgroupUseConfigYn()));
	}
}
