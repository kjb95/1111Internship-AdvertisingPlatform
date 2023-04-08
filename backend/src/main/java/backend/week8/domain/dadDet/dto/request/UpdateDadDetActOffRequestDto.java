package backend.week8.domain.dadDet.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateDadDetActOffRequestDto {
	private List<Long> kwdIds;
}
