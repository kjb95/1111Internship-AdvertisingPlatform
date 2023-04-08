package backend.week8.domain.agroup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindAllAdGroupResponseDto {
	private List<AdGroupResponseDto> agroups;
}
