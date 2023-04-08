package backend.week8.domain.kwd.repository;

import backend.week8.domain.kwd.entity.Kwd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KwdRepository extends JpaRepository<Kwd, Long> {
	Optional<Kwd> findKwdByKwdName(String kwdName);

	List<Kwd> findByManualCnrKwdYnAndKwdNameContaining(int manualCnrKwdYn, String kwdName);
}
