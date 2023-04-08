package backend.week8.domain.cnrReq.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CnrFailCauseResponseDto {
	private String value;
	private String label;
}
