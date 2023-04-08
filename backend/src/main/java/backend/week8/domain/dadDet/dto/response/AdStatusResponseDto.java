package backend.week8.domain.dadDet.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdStatusResponseDto {

	public AdStatusResponseDto(Long key, String itemNo, String kwdName, int adultYn) {
		this.key = key;
		this.itemNo = itemNo;
		this.kwdName = kwdName;
		this.adultYn = adultYn == 1 ? "성인 상품" : "일반 상품";
	}

	private Long key;
	private String itemNo;
	private String kwdName;
	private String adultYn;
}
