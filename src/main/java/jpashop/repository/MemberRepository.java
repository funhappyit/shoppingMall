package jpashop.repository;

import jpashop.domain.Member;
import jpashop.domain.QMember;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MemberRepository {

    @PersistenceContext //엔티티 메니저 주입
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public MemberRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        QMember member = QMember.member;
        return queryFactory.selectFrom(member)
            .where(member.id.eq(id))
            .fetchOne();
    }

    public List<Member> findAll(){
        QMember member = QMember.member;
        return queryFactory.selectFrom(member)
            .fetch();
    }
    public List<Member> findByName(String name){
        QMember member = QMember.member;
        return queryFactory.selectFrom(member)
            .where(member.name.eq(name))
            .fetch();
    }

}
