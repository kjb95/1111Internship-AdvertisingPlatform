package backend.week8.domain.agroup.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateAdGroupUseConfigRequestDto {
	private List<Long> adGroupIds;
	private boolean on;
}
