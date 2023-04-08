package backend.week8.domain.ad.repository;

import backend.week8.domain.ad.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

	@Query("SELECT a FROM Ad a JOIN FETCH a.item i WHERE i.itemName LIKE %:itemName% AND i.itemNo LIKE %:itemNo% AND a.agroup.agroupId=:agroupId AND i.itemActYn=1 AND a.adActYn=1")
	List<Ad> findAdWithItem(String itemName, String itemNo, Long agroupId);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Ad ad SET ad.adUseConfigYn=:useConfig WHERE ad.item.itemId IN :itemIds")
	void updateUseConfig(List<Long> itemIds, int useConfig);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Ad ad SET ad.adActYn=0 WHERE ad.item.itemId IN :itemIds")
	void updateActOff(List<Long> itemIds);

	@Query("SELECT COUNT(i) FROM Ad a JOIN a.item i ON i.itemActYn=1 WHERE a.agroup.agroupId=:agroupId AND a.adActYn=1")
	int countItemsInAdGroup(Long agroupId);
}