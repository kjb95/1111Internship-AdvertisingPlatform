package backend.week8.domain.item.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemInAdGroupResponseDto {
	private Long key;
	private String itemNo;
	private String itemName;
	private String adUseConfigYn;
}
