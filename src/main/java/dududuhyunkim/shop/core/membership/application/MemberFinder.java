package dududuhyunkim.shop.core.membership.application;

import dududuhyunkim.shop.core.membership.application.dto.MemberDto;

import java.util.List;

public interface MemberFinder {
	List<MemberDto> findMembers();

	MemberDto findOne(Long id);
}
