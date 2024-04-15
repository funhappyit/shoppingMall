package jpashop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpashop.domain.Order;

@Repository
public class OrderRepository {

	@PersistenceContext
	EntityManager em;

	public void save(Order order){
		em.persist(order);
	}

	public Order findOne(Long id){
		return em.find(Order.class,id);
	}



}
