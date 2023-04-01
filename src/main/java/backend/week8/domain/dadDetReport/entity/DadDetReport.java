package backend.week8.domain.dadDetReport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "DAD_DET_REPORT")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadDetReport {
	@EmbeddedId
	private DadDetReportId dadDetReportId;
	@Column(name = "IMPRESSIONS")
	private int impressions;
	@Column(name = "CLICKS")
	private int clicks;
	@Column(name = "AVERAGE_IMPRESSION_RANK")
	private double averageImpressionRank;
	@Column(name = "AVERAGE_CLICK_COST")
	private int averageClickCost;
	@Column(name = "ADVERTISING_COST")
	private int advertisingCost;
}
