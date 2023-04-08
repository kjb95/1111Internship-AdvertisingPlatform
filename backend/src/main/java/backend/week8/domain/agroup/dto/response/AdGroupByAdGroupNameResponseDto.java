package backend.week8.domain.agroup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdGroupByAdGroupNameResponseDto {
	private long key;
	private String agroupName;
	private String agroupUseConfigYn;
	private String itemCountLiveAndAll;
}
