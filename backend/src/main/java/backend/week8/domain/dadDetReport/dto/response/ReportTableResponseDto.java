package backend.week8.domain.dadDetReport.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportTableResponseDto {
	private String key;
	private String date;
	private int impressions;
	private int clicks;
	private String clicksRate;
	private double averageImpressionRank;
	private double averageClickCost;
	private int advertisingCost;
}