package backend.week8.domain.cnrReq.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCheckProcessRequestDto {
	private Long dadDetId;
	private boolean cnrIngStatus;
	private String selectedCnrFailCause;
	private String cnrFailComt;
}
