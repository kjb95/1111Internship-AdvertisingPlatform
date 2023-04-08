package backend.week8.domain.dadDetReport.entity;

import backend.week8.domain.dadDetReport.dto.enums.ChartReportCategory;
import backend.week8.domain.dadDetReport.dto.response.ReportChartResponseDto;
import backend.week8.domain.dadDetReport.dto.response.ReportTableResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static backend.week8.common.utils.Utils.roundOne;

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
	private double averageClickCost;
	@Column(name = "ADVERTISING_COST")
	private int advertisingCost;

	public List<ReportChartResponseDto> createReportChart() {
		List<ReportChartResponseDto> reportChart = new ArrayList<>();
		reportChart.add(new ReportChartResponseDto(dadDetReportId.getBaseDate(), ChartReportCategory.IMPRESSIONS.getDescription(), impressions));
		reportChart.add(new ReportChartResponseDto(dadDetReportId.getBaseDate(), ChartReportCategory.CLICKS.getDescription(), clicks));
		reportChart.add(new ReportChartResponseDto(dadDetReportId.getBaseDate(), ChartReportCategory.AVERAGE_IMPRESSION_RANK.getDescription(), averageImpressionRank));
		reportChart.add(new ReportChartResponseDto(dadDetReportId.getBaseDate(), ChartReportCategory.AVERAGE_CLICK_COST.getDescription(), averageClickCost));
		reportChart.add(new ReportChartResponseDto(dadDetReportId.getBaseDate(), ChartReportCategory.ADVERTISING_COST.getDescription(), advertisingCost));
		return reportChart;
	}

	public ReportTableResponseDto createReportTable() {
		String baseDate = dadDetReportId.getBaseDate();
		String date = baseDate.substring(0, 4) + "." + baseDate.substring(4, 6) + "." + baseDate.substring(6);
		String clicksRate = roundOne(((double) clicks * 100 / impressions)) + "%";
		return new ReportTableResponseDto(dadDetReportId.getBaseDate(), date, impressions, clicks, clicksRate, averageImpressionRank, averageClickCost, advertisingCost);
	}
}
