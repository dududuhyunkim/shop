package dududuhyunkim.shop.core.order.application.dto;

import dududuhyunkim.shop.core.order.domain.OrderStatus;
import lombok.Getter;

@Getter
public class OrderSearchCommand {
	private String memberName;
	private OrderStatus orderStatus;
}
