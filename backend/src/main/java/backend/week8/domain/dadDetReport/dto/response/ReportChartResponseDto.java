package backend.week8.domain.dadDetReport.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportChartResponseDto implements Comparable {
	private String date;
	private String category;
	private double value;

	@Override
	public int compareTo(Object o) {
		ReportChartResponseDto reportChartResponseDto = (ReportChartResponseDto) o;
		return date.compareTo(reportChartResponseDto.date);
	}
}
