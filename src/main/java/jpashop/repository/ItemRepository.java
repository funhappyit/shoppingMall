package jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jpashop.domain.Item;
import jpashop.domain.QItem;

@Repository
public class ItemRepository {
	@PersistenceContext
	EntityManager em;

	 JPAQueryFactory queryFactory = new JPAQueryFactory(em);

	public ItemRepository(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	public void save(Item item){
		if(item.getId() == null){
			em.persist(item);
		}else{
			em.merge(item);
		}
	}
	public Item findOne(Long id){
		QItem item = QItem.item;
		return queryFactory.selectFrom(item)
			.where(item.id.eq(id))
			.fetchOne();
	}

	public List<Item> findAll(){
		QItem item = QItem.item;
		return queryFactory.selectFrom(item).fetch();
	}

}
