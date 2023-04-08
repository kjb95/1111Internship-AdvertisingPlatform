package backend.week8.domain.dadDet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class KeywordInItemResponseDto {
	private long key;
	private String kwdName;
	private String dadUseConfigYn;
}
