package backend.week8.domain.adv.repository;

import backend.week8.domain.adv.entity.Adv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvRepository extends JpaRepository<Adv, String> {

}
