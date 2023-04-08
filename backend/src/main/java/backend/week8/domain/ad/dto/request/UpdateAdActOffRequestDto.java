package backend.week8.domain.ad.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateAdActOffRequestDto {
	private List<Long> itemIds;
}
