package backend.week8.batch.writer;

import backend.week8.domain.dadDetReport.dto.DadDetReports;
import backend.week8.domain.dadDetReport.entity.DadDetReport;
import backend.week8.domain.dadDetReport.entity.DadDetReportId;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomJpaItemWriter<T> extends JpaItemWriter<T> {
	private final EntityManagerFactory entityManagerFactory;

	@Override
	public void write(List<? extends T> items) {
		List<DadDetReport> dadDetReports = (List<DadDetReport>) items;
		HashMap<Integer, List<DadDetReport>> dadDetReportMap = new HashMap<>();
		dadDetReports.forEach(dadDetReport -> processOfMakingDadDetReportMap(dadDetReportMap, dadDetReport));
		List convertedItems = dadDetReportMap.values()
				.stream()
				.map(this::computeAvgDadDetReport)
				.collect(Collectors.toList());
		// JpaItemWriter Original Code
		EntityManager entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
		if (entityManager == null) {
			throw new DataAccessResourceFailureException("Unable to obtain a transactional EntityManager");
		}
		doWrite(entityManager, convertedItems);
		entityManager.flush();
	}

	private void processOfMakingDadDetReportMap(HashMap<Integer, List<DadDetReport>> dadDetReportMap, DadDetReport dadDetReport) {
		int dadDetReportIdHashcode = dadDetReport.getDadDetReportId()
				.hashCode();
		List<DadDetReport> reports = dadDetReportMap.getOrDefault(dadDetReportIdHashcode, new ArrayList<>());
		reports.add(dadDetReport);
		dadDetReportMap.put(dadDetReportIdHashcode, reports);
	}

	private DadDetReport computeAvgDadDetReport(List<DadDetReport> reports) {
		DadDetReports detReports = new DadDetReports(reports);
		DadDetReportId dadDetReportId = reports.get(0)
				.getDadDetReportId();
		return new DadDetReport(dadDetReportId, detReports.getAvgOfImpressions(), detReports.getAvgOfClicks(), detReports.getAvgOfAverageImpressionRank(), detReports.getAvgOfAverageClickCost(), detReports.getAvgOfAdvertisingCost());
	}
}
