package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Member;

@Repository
public class MemberRepository {
	
	@PersistenceContext
	private EntityManager em; // 스프링이 entity를 자동 생성
	// EnntityManager은 @PersistenceContext로만 injection이 가능
		
	public void save(Member member) {
		em.persist(member);
	}
	
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll() { // from의 대상이 테이블이 아닌 엔티티
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
	
	public List<Member> findByName(String name) { // 파라미터 바인딩을 통해 select
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name).getResultList();
	}
}
