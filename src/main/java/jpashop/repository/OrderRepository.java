package jpashop.repository;

import jpashop.domain.*;
import jpashop.repository.custom.CustomOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface OrderRepository  extends JpaRepository<Order, Long>, CustomOrderRepository,
	PagingAndSortingRepository<Order, Long>, QuerydslPredicateExecutor<Order> {
}
