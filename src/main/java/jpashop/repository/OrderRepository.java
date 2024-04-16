package jpashop.repository;

import jpashop.domain.*;
import jpashop.repository.custom.CustomOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>, CustomOrderRepository{
}
