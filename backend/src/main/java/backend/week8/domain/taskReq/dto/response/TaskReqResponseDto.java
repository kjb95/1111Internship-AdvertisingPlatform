package backend.week8.domain.taskReq.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskReqResponseDto {
	private Long key;
	private String taskName;
	private String taskStatus;
	private String registrant;
	private String regTime;
	private String reqFile;
}
