package backend.week8.batch.processor;

import backend.week8.batch.dto.DadDetReportCsvDto;
import backend.week8.domain.dadDet.repository.DadDetRepository;
import backend.week8.domain.dadDetReport.entity.DadDetReport;
import backend.week8.domain.dadDetReport.entity.DadDetReportId;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;

import static backend.week8.common.constant.Constant.ERROR_FILE_NAME_LIST;

@Component
@RequiredArgsConstructor
public class TaskReqItemProcessor implements ItemProcessor<DadDetReportCsvDto, DadDetReport> {

	private final DadDetRepository dadDetRepository;
	private ExecutionContext executionContext;
	private Set<String> errorFileNameList;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		executionContext = stepExecution.getExecutionContext();
	}

	@AfterRead
	public void afterRead() {
		errorFileNameList = (Set<String>) executionContext.get(ERROR_FILE_NAME_LIST);
	}

	@Override
	public DadDetReport process(DadDetReportCsvDto item) {
		if (errorFileNameList != null && errorFileNameList.contains(item.getFileName())) {
			return null;
		}
		String advId = dadDetRepository.findAdvIdByDadDetId(item.getDadDetId());
		DadDetReportId dadDetReportId = new DadDetReportId(advId, item.getBaseDate(), item.getDadDetId());
		return new DadDetReport(dadDetReportId, item.getImpressions(), item.getClicks(), item.getAverageImpressionRank(), item.getAverageClickCost(), item.getAdvertisingCost());
	}
}
