package backend.week8.domain.agroup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FindAllAdGroupByAdGroupNameResponseDto {
	private List<AdGroupByAdGroupNameResponseDto> agroups;
}
