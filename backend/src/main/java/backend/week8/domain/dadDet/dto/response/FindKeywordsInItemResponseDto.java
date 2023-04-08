package backend.week8.domain.dadDet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FindKeywordsInItemResponseDto {
	private List<KeywordInItemResponseDto> keywords;
}
