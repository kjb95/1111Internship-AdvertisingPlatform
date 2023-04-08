package backend.week8.domain.item.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindItemsRequestDto {
	private String itemName;
	private String itemNo;
}
