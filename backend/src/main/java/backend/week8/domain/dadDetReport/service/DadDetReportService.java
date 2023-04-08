package backend.week8.domain.dadDetReport.service;

import backend.week8.domain.dadDetReport.dto.enums.ChartReportCategory;
import backend.week8.domain.dadDetReport.dto.response.*;
import backend.week8.domain.dadDetReport.entity.DadDetReport;
import backend.week8.domain.dadDetReport.repository.DadDetReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DadDetReportService {
	private final DadDetReportRepository dadDetReportRepository;

	/**
	 * 리포트 차트 데이터 조회
	 */
	public FindDadDetReportResponseDto findDadDetReport(long dadDetId) {
		List<ReportChartResponseDto> reportChart = new ArrayList<>();
		ReportTableResponseDtos reportTableResponseDtos = new ReportTableResponseDtos();
		dadDetReportRepository.findByDadDetReportId_DadDetIdOrderByDadDetReportId_BaseDateDesc(dadDetId)
				.forEach(dadDetReport -> computeDadDetReport(reportChart, reportTableResponseDtos, dadDetReport));
		reportTableResponseDtos.computeStatistics();
		Collections.sort(reportChart);
		return new FindDadDetReportResponseDto(reportChart, reportTableResponseDtos.getReportTable());
	}

	private void computeDadDetReport(List<ReportChartResponseDto> reportChart, ReportTableResponseDtos reportTableResponseDtos, DadDetReport dadDetReport) {
		reportChart.addAll(dadDetReport.createReportChart());
		reportTableResponseDtos.add(dadDetReport.createReportTable());
	}

	/**
	 * 직접광고 리포트의 모든 데이터 카테고리 조회
	 */
	public FindAllDadDetReportCategoryResponseDto findAllDadDetReportCategory() {
		List<DadDetReportCategoryResponseDto> categories = Arrays.stream(ChartReportCategory.values())
				.map(chartReportCategory -> new DadDetReportCategoryResponseDto(chartReportCategory.getDescription(), chartReportCategory.getDescription()))
				.collect(Collectors.toList());
		return new FindAllDadDetReportCategoryResponseDto(categories);
	}
}