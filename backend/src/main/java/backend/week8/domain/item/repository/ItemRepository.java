package backend.week8.domain.item.repository;

import backend.week8.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByItemNameContainsAndItemNoContains(String itemName, String itemNo);
}