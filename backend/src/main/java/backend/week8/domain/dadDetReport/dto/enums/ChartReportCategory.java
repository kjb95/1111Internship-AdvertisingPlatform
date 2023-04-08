package backend.week8.domain.dadDetReport.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChartReportCategory {
	IMPRESSIONS("노출 수"), CLICKS("클릭 수"), AVERAGE_IMPRESSION_RANK("평균 노출 순위"), AVERAGE_CLICK_COST("평균 CPC"), ADVERTISING_COST("광고비");
	private String description;
}
