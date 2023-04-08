package backend.week8.domain.dadDetReport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DadDetReportId implements Serializable {
	@Column(name = "ADV_ID")
	private String advId;
	@Column(name = "BASE_DATE")
	private String baseDate;
	@Column(name = "DAD_DET_ID")
	private Long dadDetId;

	@Override
	public int hashCode() {
		int result = advId != null ? advId.hashCode() : 0;
		result = 31 * result + (baseDate != null ? baseDate.hashCode() : 0);
		result = 31 * result + (dadDetId != null ? dadDetId.hashCode() : 0);
		return result;
	}
}
