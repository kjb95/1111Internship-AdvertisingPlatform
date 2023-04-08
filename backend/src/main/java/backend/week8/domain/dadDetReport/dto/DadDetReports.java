package backend.week8.domain.dadDetReport.dto;

import backend.week8.domain.dadDetReport.entity.DadDetReport;
import lombok.Getter;

import java.util.List;

import static backend.week8.common.utils.Utils.roundOne;

@Getter
public class DadDetReports {

	public DadDetReports(List<DadDetReport> dadDetReports) {
		this.dadDetReports = dadDetReports;
		dadDetReports.forEach(this::computeDadDetReport);
		int size = dadDetReports.size();
		avgOfAverageImpressionRank = roundOne(avgOfAverageImpressionRank / size);
		avgOfAverageClickCost = roundOne(avgOfAverageClickCost / size);
	}

	private List<DadDetReport> dadDetReports;
	private int avgOfImpressions = 0;
	private int avgOfClicks = 0;
	private double avgOfAverageImpressionRank = 0;
	private double avgOfAverageClickCost = 0;
	private int avgOfAdvertisingCost = 0;

	private void computeDadDetReport(DadDetReport dadDetReport) {
		avgOfImpressions += dadDetReport.getImpressions();
		avgOfClicks += dadDetReport.getClicks();
		avgOfAverageImpressionRank += dadDetReport.getAverageImpressionRank();
		avgOfAverageClickCost += dadDetReport.getAverageClickCost();
		avgOfAdvertisingCost += dadDetReport.getAdvertisingCost();
	}
}
