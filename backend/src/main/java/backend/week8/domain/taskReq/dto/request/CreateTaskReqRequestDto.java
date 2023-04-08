package backend.week8.domain.taskReq.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTaskReqRequestDto {
	private String memberId;
	private String taskName;
	private String taskReqFile;
}
