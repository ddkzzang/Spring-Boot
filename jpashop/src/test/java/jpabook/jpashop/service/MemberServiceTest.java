package jpabook.jpashop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	EntityManager em;
	
	// 회원 가입
	@Test
	public void memberJoin() throws Exception {
		
		// given : test에 주어진 조건 
		Member member = new Member();
		member.setName("kim");
		
		// when 
		Long saveId = memberService.join(member);
		
		// then
		assertEquals(member, memberRepository.findOne(saveId));
	}
	
	
	// 중복 회원 제외
	@Test(expected = IllegalStateException.class)
	public void validate() throws Exception {
		
		// given
		Member member1 = new Member();
		member1.setName("kim1");

		Member member2 = new Member();
		member2.setName("kim1");
		
		// when
		memberService.join(member1);
		memberService.join(member2); // 여기서 에러발생해야함
	
		// then
		fail("예외가 발생해야함");
	}
}
