package backend.week8.domain.agroup.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAdGroupNameRequestDto {
	private Long adGroupId;
	private String adGroupName;
}
