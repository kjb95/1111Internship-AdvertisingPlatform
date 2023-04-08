package backend.week8.domain.agroup.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateAdGroupActOffRequestDto {
	private List<Long> adGroupIds;
}
