package backend.week8.domain.agroup.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindAdGroupRequestDto {
	private Long adGroupId;
	private String advId;
}
