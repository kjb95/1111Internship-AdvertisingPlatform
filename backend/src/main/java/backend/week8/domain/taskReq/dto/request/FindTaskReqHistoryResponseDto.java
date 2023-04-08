package backend.week8.domain.taskReq.dto.request;

import backend.week8.domain.taskReq.dto.response.TaskReqResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FindTaskReqHistoryResponseDto {
	private List<TaskReqResponseDto> taskReqHistory;
}
