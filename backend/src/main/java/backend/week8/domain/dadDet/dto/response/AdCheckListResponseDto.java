package backend.week8.domain.dadDet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdCheckListResponseDto {
	private Long key;

	public AdCheckListResponseDto(Long key, String itemName, String kwdName) {
		this.key = key;
		this.itemName = itemName;
		this.kwdName = kwdName;
		checkReason = "검수 대상 키워드 : " + kwdName;
	}

	private String itemName;
	private String kwdName;
	private String checkReason;
}
