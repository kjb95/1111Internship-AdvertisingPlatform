package backend.week8.domain.taskReq.entity;

import backend.week8.domain.member.entity.Member;
import backend.week8.domain.taskReq.entity.enus.TaskStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name = "TASK_REQ")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskReq {

	public TaskReq(Member member, String name, String reqFilePath) {
		this.member = member;
		this.name = name;
		this.reqFilePath = reqFilePath;
		status = TaskStatus.REQ;
		reqTime = LocalDateTime.now();
		baseDate = reqTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}

	@Id
	@Column(name = "TASK_REQ_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "BASE_DATE")
	private String baseDate;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	@Enumerated(EnumType.STRING)
	@Column(name = "TASK_STATUS")
	private TaskStatus status;
	@Column(name = "TASK_NAME")
	private String name;
	@Column(name = "TASK_REQ_FILE_PATH")
	private String reqFilePath;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "TASK_REQ_TIME")
	private LocalDateTime reqTime;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "TASK_START_TIME")
	private LocalDateTime startTime;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "TASK_END_TIME")
	private LocalDateTime endTime;
}
