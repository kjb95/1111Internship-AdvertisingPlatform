package backend.week8.domain.taskReq.repository;

import backend.week8.domain.taskReq.entity.TaskReq;
import backend.week8.domain.taskReq.entity.enus.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface TaskReqRepository extends JpaRepository<TaskReq, Long> {
	@Query("SELECT t FROM TaskReq t JOIN FETCH t.member m WHERE t.status=:taskStatus")
	List<TaskReq> findByStatus(TaskStatus taskStatus);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE TaskReq t SET t.status=:status WHERE t.id IN :ids")
	@Transactional
	void updateTaskStatus(TaskStatus status, List<Long> ids);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE TaskReq t SET t.status=:status, t.endTime=:endTime WHERE t.id IN :ids")
	@Transactional
	void updateStatusAndEndTime(List<Long> ids, TaskStatus status, LocalDateTime endTime);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE TaskReq t SET t.status=:status, t.startTime=:startTime WHERE t.id IN :ids")
	@Transactional
	void updateStatusAndStartTime(List<Long> ids, TaskStatus status, LocalDateTime startTime);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE TaskReq t SET t.status=:status, t.endTime=:endTime WHERE t.reqFilePath IN :fileNames")
	@Transactional
	void updateStatusAndEndTime(Set<String> fileNames, TaskStatus status, LocalDateTime endTime);
}
