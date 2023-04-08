package backend.week8.domain.item.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponseDto {
	public ItemResponseDto(Long key, String itemNo, String itemName, int adultYn, int itemOrgCost, int itemActYn) {
		this.key = key;
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.adultYn = adultYn == 1 ? "YES" : "NO";
		this.itemOrgCost = itemOrgCost;
		this.itemActYn = itemActYn == 1 ? "활성화" : "비활성화";
	}

	private Long key;
	private String itemNo;
	private String itemName;
	private String adultYn;
	private int itemOrgCost;
	private String itemActYn;
}
