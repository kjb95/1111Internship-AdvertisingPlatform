package backend.week8.domain.item.service;

import backend.week8.domain.ad.entity.Ad;
import backend.week8.domain.ad.repository.AdRepository;
import backend.week8.domain.item.dto.request.FindItemsInAdGroupRequestDto;
import backend.week8.domain.item.dto.request.FindItemsRequestDto;
import backend.week8.domain.item.dto.response.*;
import backend.week8.domain.item.entity.Item;
import backend.week8.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static backend.week8.common.constant.Constant.NOT_FOUND_ITEM;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	private final AdRepository adRepository;

	/**
	 * 조건에 따른 상품 조회
	 */
	public FindItemsResponseDto findItems(FindItemsRequestDto findItemsRequestDto) {
		List<ItemResponseDto> items = itemRepository.findByItemNameContainsAndItemNoContains(findItemsRequestDto.getItemName(), findItemsRequestDto.getItemNo())
				.stream()
				.map((item) -> new ItemResponseDto(item.getItemId(), item.getItemNo(), item.getItemName(), item.getAdultYn(), item.getItemOrgCost(), item.getItemActYn()))
				.collect(Collectors.toList());
		return new FindItemsResponseDto(items);
	}

	/**
	 * 조건(상품 명, 상품 번호)에 따라 한 광고 그룹에 속한 상품들 조회
	 */
	public FindItemsInAdGroupResponseDto findItemsInAdGroup(FindItemsInAdGroupRequestDto findItemsInAdGroupRequestDto) {
		List<ItemInAdGroupResponseDto> items = adRepository.findAdWithItem(findItemsInAdGroupRequestDto.getItemName(), findItemsInAdGroupRequestDto.getItemNo(), findItemsInAdGroupRequestDto.getAdGroupId())
				.stream()
				.map(this::createItemsInAdGroupDto)
				.collect(Collectors.toList());
		return new FindItemsInAdGroupResponseDto(items);
	}

	private ItemInAdGroupResponseDto createItemsInAdGroupDto(Ad ad) {
		Item item = ad.getItem();
		String isOnAdUseConfig = ad.getAdUseConfigYn() == 1 ? "ON" : "OFF";
		return new ItemInAdGroupResponseDto(item.getItemId(), item.getItemNo(), item.getItemName(), isOnAdUseConfig);
	}

	/**
	 * 상품 한개 조회
	 */
	public FindItemResponseDto findItem(Long itemId) {
		Item item = itemRepository.findById(itemId)
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ITEM));
		return new FindItemResponseDto(item.getItemNo(), item.getItemName(), item.getAdultYn() == 1, item.getItemOrgCost());
	}
}
