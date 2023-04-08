package backend.week8.domain.cnrReq.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FindAllCnrFailCauseResponseDto {
	private List<CnrFailCauseResponseDto> failCauses;
}
