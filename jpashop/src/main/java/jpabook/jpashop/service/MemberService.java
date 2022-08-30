package jpabook.jpashop.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true) // 조회시 DB 리소스 사용을 자제하기 위해 
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	// 생성자로 연결하면 @Autowired 어노테이션이 없어도 injection을 해줌  
	
	// 회원 가입
	@Transactional // default -> readOnly = false
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		
		return member.getId();
	}
	
	// 중복 회원 조회
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) { 
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	// 회원 전체 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	// 회원 한명 조회 
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}

