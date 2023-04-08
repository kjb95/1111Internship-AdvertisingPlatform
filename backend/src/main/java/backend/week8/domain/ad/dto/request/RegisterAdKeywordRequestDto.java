package backend.week8.domain.ad.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAdKeywordRequestDto {
	private String keywordName;
	private int bid;
}
