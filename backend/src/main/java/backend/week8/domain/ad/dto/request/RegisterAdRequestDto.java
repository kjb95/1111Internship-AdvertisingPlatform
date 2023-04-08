package backend.week8.domain.ad.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class RegisterAdRequestDto {
	private String agroupId;
	private long itemId;
	private String advId;
	private List<RegisterAdKeywordRequestDto> keywordList;
}
