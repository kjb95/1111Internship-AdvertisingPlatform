package backend.week8.batch.reader;

import backend.week8.batch.dto.DadDetReportCsvDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@RequiredArgsConstructor
@Configuration
public class TaskReqReader {
	private static final String FILE_TEMPLATE = "날짜,직접 광고 ID,노출수,클릭수,평균 노출 순위,평균 클릭 비용,광고비";
	private static final String INVALID_FILE_TEMPLATE = "잘못된 파일 템플릿";

	public class CustomFieldSetMapper implements FieldSetMapper {
		@Override
		public DadDetReportCsvDto mapFieldSet(FieldSet fieldSet) {
			Resource currentResource = multiResourceItemReader().getCurrentResource();
			String filename = currentResource.getFilename();

			return DadDetReportCsvDto.builder()
					.baseDate(fieldSet.readString("baseDate"))
					.dadDetId(fieldSet.readLong("dadDetId"))
					.impressions(fieldSet.readInt("impressions"))
					.clicks(fieldSet.readInt("clicks"))
					.averageImpressionRank(fieldSet.readDouble("averageImpressionRank"))
					.averageClickCost(fieldSet.readInt("averageClickCost"))
					.advertisingCost(fieldSet.readInt("advertisingCost"))
					.fileName(filename)
					.build();
		}
	}

	@Bean
	public FlatFileItemReader taskReqItemReader() {
		return new FlatFileItemReaderBuilder<DadDetReportCsvDto>().name("taskReqItemReader")
				.targetType(DadDetReportCsvDto.class)
				.skippedLinesCallback(this::validFileTemplate)
				.linesToSkip(1)
				.lineMapper(createLineMapper())
				.build();
	}

	private void validFileTemplate(String line) {
		if (!line.equals(FILE_TEMPLATE)) {
			throw new ItemStreamException(INVALID_FILE_TEMPLATE);
		}
	}

	private DefaultLineMapper createLineMapper() {
		DefaultLineMapper<DadDetReportCsvDto> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(",");
		lineTokenizer.setNames("baseDate", "dadDetId", "impressions", "clicks", "averageImpressionRank", "averageClickCost", "advertisingCost");
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(new CustomFieldSetMapper());
		return lineMapper;
	}

	@Bean
	public MultiResourceItemReader multiResourceItemReader() {
		return new MultiResourceItemReaderBuilder<>().name("multiResourceItemReader")
				.resources(new Resource[]{})
				.delegate(taskReqItemReader())
				.build();
	}
}
