package dududuhyunkim.shop.core.order.application;

import dududuhyunkim.shop.core.item.domain.Item;
import dududuhyunkim.shop.core.item.domain.ItemRepository;
import dududuhyunkim.shop.core.membership.domain.Member;
import dududuhyunkim.shop.core.membership.domain.MemberRepository;
import dududuhyunkim.shop.core.order.domain.Delivery;
import dududuhyunkim.shop.core.order.domain.Order;
import dududuhyunkim.shop.core.order.domain.OrderItem;
import dududuhyunkim.shop.core.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderFinder, OrderEditor {
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;

	@Transactional
	@Override
	public Long order(Long memberId, Long itemId, int count) {
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);

		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());

		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

		Order order = Order.createOrder(member, delivery, orderItem);

		orderRepository.save(order);
		return order.getId();
	}

	@Transactional
	@Override
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findOne(orderId);
		order.cancel();
	}

}
