package backend.week8.batch.config;

import backend.week8.batch.dto.DadDetReportCsvDto;
import backend.week8.batch.listener.TaskReqListener;
import backend.week8.batch.processor.TaskReqItemProcessor;
import backend.week8.batch.writer.CustomJpaItemWriter;
import backend.week8.domain.dadDetReport.entity.DadDetReport;
import backend.week8.domain.taskReq.entity.TaskReq;
import backend.week8.domain.taskReq.entity.enus.TaskStatus;
import backend.week8.domain.taskReq.repository.TaskReqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final TaskReqItemProcessor taskReqItemProcessor;
	private final EntityManagerFactory entityManagerFactory;
	private final TaskReqListener taskReqListener;
	private final MultiResourceItemReader multiResourceItemReader;
	private final TaskReqRepository taskReqRepository;
	private final JobLauncher jobLauncher;

	@Bean
	public Job taskReqJob() {
		return jobBuilderFactory.get("taskReqJob")
				.start(taskReqStep())
				.build();
	}

	@Bean
	public Step taskReqStep() {
		return stepBuilderFactory.get("taskReqStep")
				.<DadDetReportCsvDto, DadDetReport>chunk(1024)
				.reader(multiResourceItemReader)
				.processor(taskReqItemProcessor)
				.writer(new CustomJpaItemWriter(entityManagerFactory))
				.listener(taskReqListener)
				.faultTolerant()
				.skipPolicy(new AlwaysSkipItemSkipPolicy())
				.build();
	}

	@Scheduled(fixedDelay = 30000)
	public void runTaskReqJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		List<TaskReq> taskReqs = taskReqRepository.findByStatus(TaskStatus.REQ);
		if (taskReqs == null || taskReqs.size() == 0) {
			return;
		}
		Map<String, JobParameter> jobParameterMap = new HashMap<>();
		jobParameterMap.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(jobParameterMap);
		jobLauncher.run(taskReqJob(), jobParameters);
	}
}
