package dududuhyunkim.shop.core.membership.application;

import dududuhyunkim.shop.core.membership.application.dto.MemberDto;
import dududuhyunkim.shop.core.membership.domain.Member;
import dududuhyunkim.shop.core.membership.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberManager implements MemberFinder, MemberEditor {
	private final MemberRepository memberRepository;

	@Transactional
	@Override
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<MemberDto> findMembers() {
		return memberRepository.findAll().stream().map(MemberDto::ofEntity).toList();
	}

	@Transactional(readOnly = true)
	@Override
	public MemberDto findOne(Long id) {
		return MemberDto.ofEntity(memberRepository.findOne(id));
	}
}
