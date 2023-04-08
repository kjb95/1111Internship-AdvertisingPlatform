package backend.week8.domain.kwd.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FindAllKwdResponseDto {
	List<KwdResponseDto> kwds;
}
