package backend.week8.domain.dadDetBid.repository;

import backend.week8.domain.dadDetBid.entity.DadDetBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadDetBidRepository extends JpaRepository<DadDetBid, Long> {
}
