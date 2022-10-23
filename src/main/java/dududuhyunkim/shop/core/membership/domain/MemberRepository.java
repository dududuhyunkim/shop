package dududuhyunkim.shop.core.membership.domain;

import java.util.List;

public interface MemberRepository {
	void save(Member member);

	Member findOne(Long id);

	List<Member> findAll();

	List<Member> findByName(String name);
}
