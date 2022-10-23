package dududuhyunkim.shop.core.membership.application;

import dududuhyunkim.shop.core.membership.domain.Member;
import dududuhyunkim.shop.core.membership.infrastructure.MemberDBRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberManagerTest {


	@Autowired
	MemberManager memberManager;
	@Autowired
	MemberDBRepository memberRepository;

	// TODO : setter 제거
	@Test
	@DisplayName("회원가입")
	void join() {
		// Given
		Member member = new Member();
		member.setName("kim");

		// When
		Long savedId = memberManager.join(member);

		// Then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(member).isEqualTo(memberRepository.findOne(savedId));
		});
	}

	// TODO : setter 제거
	@Test()
	@DisplayName("회원가입 중복 예외")
	void join_exception() throws Exception {
		// Given
		Member member = new Member();
		member.setName("kim");
		memberRepository.save(member);

		Member newMember = new Member();
		newMember.setName("kim");

		// Then
		Assertions.assertThrows(IllegalStateException.class, () -> {
			// When
			Long savedId = memberManager.join(member);
		});

	}
}