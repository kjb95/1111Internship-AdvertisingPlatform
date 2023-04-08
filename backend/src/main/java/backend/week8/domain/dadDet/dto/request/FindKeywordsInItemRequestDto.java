package backend.week8.domain.dadDet.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindKeywordsInItemRequestDto {
	private long itemId;
	private String keywordNameSearch;
}
