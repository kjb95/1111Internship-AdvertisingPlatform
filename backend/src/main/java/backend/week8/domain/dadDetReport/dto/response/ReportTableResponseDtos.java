package backend.week8.domain.dadDetReport.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static backend.week8.common.utils.Utils.roundOne;

@Getter
public class ReportTableResponseDtos {
	private List<ReportTableResponseDto> reportTable = new ArrayList<>();
	private int sumOfImpressions = 0;
	private int sumOfClicks = 0;
	private double sumOfAverageImpressionRank = 0;
	private double sumOfAverageClickCost = 0;
	private int sumOfAdvertisingCost = 0;

	public void add(ReportTableResponseDto responseDto) {
		reportTable.add(responseDto);
		sumOfImpressions += responseDto.getImpressions();
		sumOfClicks += responseDto.getClicks();
		sumOfAverageImpressionRank += responseDto.getAverageImpressionRank();
		sumOfAverageClickCost += responseDto.getAverageClickCost();
		sumOfAdvertisingCost += responseDto.getAdvertisingCost();
	}

	public String computeClickRate() {
		return roundOne((double) sumOfClicks / sumOfImpressions * 100) + "%";
	}

	public double computeAverageImpressionRank() {
		return roundOne(sumOfAverageImpressionRank / reportTable.size());
	}

	public double computeAverageClickCost() {
		return Math.round(sumOfAverageClickCost / reportTable.size());
	}

	public void computeStatistics() {
		reportTable.add(new ReportTableResponseDto("statistics", "총 " + reportTable.size() + "건 합계", sumOfImpressions, sumOfClicks, computeClickRate(), computeAverageImpressionRank(), computeAverageClickCost(),
				sumOfAdvertisingCost));
	}
}
