package backend.week8.domain.ad.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateAdUseConfigAndDadUseConfigRequestDto {
	private List<Long> itemIds;
	private boolean on;
}
