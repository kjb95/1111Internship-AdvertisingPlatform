package backend.week8.domain.taskReq.entity.enus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {
	REQ("요청"), ING("진행"), COMPLETE("완료"), ERROR("에러");
	private String description;
}
