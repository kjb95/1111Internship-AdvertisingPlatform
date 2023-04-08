package backend.week8.domain.adv.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDayLimitBudgetRequestDto {
	private String advId;
	private int dayLimitBudget;
}
