package jpashop.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.persistence.criteria.Join;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import jpashop.domain.Delivery;
import jpashop.domain.Item;
import jpashop.domain.Member;
import jpashop.domain.Order;
import jpashop.domain.OrderItem;
import jpashop.domain.OrderSearch;
import jpashop.domain.QOrder;
import jpashop.repository.MemberRepository;
import jpashop.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	@Autowired MemberRepository memberRepository;
	@Autowired OrderRepository orderRepository;
	@Autowired ItemService itemService;

	/* 주문 */
	public Long order(Long memberId,Long itemId,int count){
		//엔티티 조회
		Optional<Member> memberOptional = memberRepository.findById(memberId);
		Member member = memberOptional.orElseThrow(() -> new IllegalArgumentException("Member not found"));

		Optional<Item> itemOptional = itemService.findOne(itemId);
		Item item = itemOptional.orElseThrow(() -> new IllegalArgumentException("Item not found"));

		//배송정보 생성
		Delivery delivery = new Delivery(member.getAddress());
		//주문 상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);
		//주문 생성
		Order order = Order.createOrder(member,delivery,orderItem);
		//주문 저장
		orderRepository.save(order);
		return order.getId();
	}
	/*주문 취소 */
	public void cancelOrder(Long orderId){
		//주문 엔티티 조회
		Optional<Order> orderOptional = orderRepository.findById(orderId);
		Order order = orderOptional.orElseThrow(() -> new IllegalArgumentException("order not found"));
		//주문 취소
		order.cancel();
	}

	/** 주문 검색 */
	public List<Order> findOrders(OrderSearch orderSearch) {
		QOrder order = QOrder.order;
		BooleanBuilder builder = new BooleanBuilder();

		if (orderSearch.getOrderStatus() != null) {
			builder.and(order.status.eq(orderSearch.getOrderStatus()));
		}

		if (orderSearch.getMemberName() != null) {
			builder.and(order.member.name.like("%" + orderSearch.getMemberName() + "%"));
		}

		return (List<Order>) orderRepository.findAll(builder);
	}



}
