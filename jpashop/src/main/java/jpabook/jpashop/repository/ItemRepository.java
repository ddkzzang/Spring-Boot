package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	
	private final EntityManager em;
	
	// 상품 저장
	public void save(Item item) {
		if(item.getId() == null) { // 해당 아이템이 이전에 있던 상품인지 
			em.persist(item); 
		} else {
			em.merge(item);
		}
	}
	
	// 특정 상품 찾기
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	// 상품 전체 조회
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}
	
	
}
