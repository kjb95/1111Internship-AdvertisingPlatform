package backend.week8.batch.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DadDetReportCsvDto {
	private String baseDate;
	private Long dadDetId;
	private int impressions;
	private int clicks;
	private double averageImpressionRank;
	private int averageClickCost;
	private int advertisingCost;
	private String fileName;
}
