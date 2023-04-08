package backend.week8.domain.agroup.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class FindAdGroupResponseDto {

	private static final String REG_TIME_FORMAT = "yyyy.MM.dd HH:mm";

	public FindAdGroupResponseDto(String agroupName, int agroupUseConfigYn, int agroupItemsCount, LocalDateTime regTime) {
		this.agroupName = agroupName;
		this.agroupUseConfigYn= agroupUseConfigYn == 1;
		this.agroupItemsCount = agroupItemsCount;
		this.regTime = regTime.format(DateTimeFormatter.ofPattern(REG_TIME_FORMAT));
	}

	private String agroupName;
	private boolean agroupUseConfigYn;
	private int agroupItemsCount;
	private String regTime;
}
