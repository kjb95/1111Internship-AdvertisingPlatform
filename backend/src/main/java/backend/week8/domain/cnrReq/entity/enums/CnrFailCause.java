package backend.week8.domain.cnrReq.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CnrFailCause {
	LOW_QUALITY("품질 저하"), WRONG_SPECIFICATION("잘못된 규격"), FAULT_EXIST("결함 존재");

	private String description;
}
