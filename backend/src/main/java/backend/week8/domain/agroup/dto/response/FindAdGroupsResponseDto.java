package backend.week8.domain.agroup.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindAdGroupsResponseDto {
	public FindAdGroupsResponseDto(Long key, String agroupName, int agroupUseConfigYn, Long countAdAct, Long countAdUseConfig) {
		this.key = key;
		this.agroupName = agroupName;
		this.agroupUseConfigYn = agroupUseConfigYn;
		this.countAdAct = countAdAct != null ? countAdAct : 0;
		this.countAdUseConfig = countAdUseConfig != null ? countAdUseConfig : 0;
	}

	private Long key;
	private String agroupName;
	private int agroupUseConfigYn;
	private Long countAdAct;
	private Long countAdUseConfig;
}
