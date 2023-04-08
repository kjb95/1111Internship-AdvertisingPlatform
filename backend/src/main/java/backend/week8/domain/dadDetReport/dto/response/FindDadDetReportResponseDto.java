package backend.week8.domain.dadDetReport.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FindDadDetReportResponseDto {
	private List<ReportChartResponseDto> reportChart;
	private List<ReportTableResponseDto> reportTable;
}
