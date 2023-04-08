package backend.week8.domain.item.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindItemsInAdGroupRequestDto {
	private Long adGroupId;
	private String itemName;
	private String itemNo;
}