package dududuhyunkim.shop.core.membership.application.dto;

import dududuhyunkim.shop.core.membership.domain.Member;
import lombok.Getter;

@Getter
public class MemberDto {
	private Long id;
	private String name;
	private Address address;

	public static MemberDto ofEntity(Member member) {
		MemberDto memberDto = new MemberDto();
		memberDto.id = member.getId();
		memberDto.name = member.getName();
		memberDto.address = Address.ofEmbedded(member.getAddress());

		return memberDto;
	}

	public static class Address {
		private String city;
		private String street;
		private String zipcode;

		static Address ofEmbedded(dududuhyunkim.shop.core.common.domain.Address address) {
			Address addressDto = new Address();
			addressDto.city = address.getCity();
			addressDto.street = address.getStreet();
			addressDto.zipcode = address.getZipcode();

			return addressDto;
		}
	}
}
