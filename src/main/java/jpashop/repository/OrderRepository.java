package jpashop.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpashop.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class OrderRepository {

	@PersistenceContext
	EntityManager em;

	private final JPAQueryFactory queryFactory;

	public OrderRepository(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}


	public void save(Order order){
		em.persist(order);
	}

	public Order findOne(Long id){
		QOrder order = QOrder.order;
		return  queryFactory
			.selectFrom(order)
			.where(order.id.eq(id))
			.fetchOne();
	}

	public List<Order> findAll(OrderSearch orderSearch){
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QOrder order = QOrder.order;
		QMember member = QMember.member;

		BooleanBuilder whereBuilder = new BooleanBuilder();

		if(orderSearch.getOrderStatus() != null){
			whereBuilder.and(order.status.eq(orderSearch.getOrderStatus()));
		}

		if (StringUtils.hasText(orderSearch.getMemberName())) {
			whereBuilder.and(member.name.like("%" + orderSearch.getMemberName() + "%"));
		}

		List<Order> result = queryFactory.selectFrom(order)
				.innerJoin(order.member, member)
				.where(whereBuilder)
				.fetch();
		return result;
	}

}
