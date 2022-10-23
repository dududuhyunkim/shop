package dududuhyunkim.shop.core.membership.domain;

import dududuhyunkim.shop.core.common.domain.Address;
import dududuhyunkim.shop.core.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "nember_id")
	private Long id;

	private String name;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();
}
