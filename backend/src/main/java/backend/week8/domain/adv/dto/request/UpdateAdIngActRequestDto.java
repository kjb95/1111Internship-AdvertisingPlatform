package backend.week8.domain.adv.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateAdIngActRequestDto {
	private String advId;
	private boolean on;
}
