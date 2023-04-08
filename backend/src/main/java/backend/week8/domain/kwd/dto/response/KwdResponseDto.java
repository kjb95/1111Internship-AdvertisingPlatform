package backend.week8.domain.kwd.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KwdResponseDto {
	private Long key;
	private String kwdName;
}
