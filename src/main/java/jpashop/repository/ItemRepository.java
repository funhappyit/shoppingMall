package jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jpashop.domain.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {

}

