package backend.week8.domain.dadDetReport.repository;

import backend.week8.domain.dadDetReport.entity.DadDetReport;
import backend.week8.domain.dadDetReport.entity.DadDetReportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DadDetReportRepository extends JpaRepository<DadDetReport, DadDetReportId> {
	List<DadDetReport> findByDadDetReportId_DadDetIdOrderByDadDetReportId_BaseDateDesc(Long dadDetId);
}
