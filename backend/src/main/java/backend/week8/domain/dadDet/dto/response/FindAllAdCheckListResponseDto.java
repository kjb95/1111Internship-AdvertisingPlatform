package backend.week8.domain.dadDet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class FindAllAdCheckListResponseDto {
	private List<AdCheckListResponseDto> adCheckList;
}
