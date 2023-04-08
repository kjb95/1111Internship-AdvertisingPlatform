package backend.week8.domain.cnrReq.repository;

import backend.week8.domain.cnrReq.entity.CnrReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnrReqRepository extends JpaRepository<CnrReq, Long> {
}
