package backend.week8.domain.item.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FindItemResponseDto {
	private String itemNo;
	private String itemName;
	private boolean adultYn;
	private int itemOrgCost;
}
