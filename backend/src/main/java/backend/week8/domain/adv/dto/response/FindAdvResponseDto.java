package backend.week8.domain.adv.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindAdvResponseDto {
	public FindAdvResponseDto() {
		adIngActYn = true;
	}

	private boolean adIngActYn;
	private int balance;
	private int eventMoney;
	private int dayLimitBudget;
}
