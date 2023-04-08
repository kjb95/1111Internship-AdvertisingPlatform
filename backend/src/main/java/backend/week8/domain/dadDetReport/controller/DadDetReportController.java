package backend.week8.domain.dadDetReport.controller;

import backend.week8.domain.dadDetReport.dto.response.FindAllDadDetReportCategoryResponseDto;
import backend.week8.domain.dadDetReport.dto.response.FindDadDetReportResponseDto;
import backend.week8.domain.dadDetReport.service.DadDetReportService;
import backend.week8.domain.item.dto.response.FindItemsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Transactional
@RestController
@RequestMapping("/api/dadDetReport")
@RequiredArgsConstructor
public class DadDetReportController {
	private final DadDetReportService dadDetReportService;

	/**
	 * 리포트 차트 데이터 조회
	 */
	@GetMapping("/{dadDetId}")
	public ResponseEntity<FindDadDetReportResponseDto> findDadDetReport(@PathVariable Long dadDetId) {
		FindDadDetReportResponseDto findDadDetReportResponseDto = dadDetReportService.findDadDetReport(dadDetId);
		return ResponseEntity.ok()
				.body(findDadDetReportResponseDto);
	}

	/**
	 * 직접광고 리포트의 모든 데이터 카테고리 조회
	 */
	@GetMapping("/category")
	public ResponseEntity<FindAllDadDetReportCategoryResponseDto> findAllDadDetReportCategory() {
		FindAllDadDetReportCategoryResponseDto findAllDadDetReportCategoryResponseDto = dadDetReportService.findAllDadDetReportCategory();
		return ResponseEntity.ok()
				.body(findAllDadDetReportCategoryResponseDto);
	}
}
