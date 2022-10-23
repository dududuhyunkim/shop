package dududuhyunkim.shop.core.order.application;

import dududuhyunkim.shop.core.common.domain.Address;
import dududuhyunkim.shop.core.item.domain.Book;
import dududuhyunkim.shop.core.item.domain.Item;
import dududuhyunkim.shop.core.item.exception.NotEnoughStockException;
import dududuhyunkim.shop.core.membership.domain.Member;
import dududuhyunkim.shop.core.order.domain.Order;
import dududuhyunkim.shop.core.order.domain.OrderRepository;
import dududuhyunkim.shop.core.order.domain.OrderStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class OrderManagerTest {

	@Autowired
	EntityManager em;
	@Autowired
	OrderManager orderManager;
	@Autowired
	OrderRepository orderRepository;

	@Test
	@DisplayName("상품 주문")
	public void order() {
		// Given
		Member member = createMember();
		Item book = createBook("시골 JPA", 10000, 10);

		int orderCount = 2;

		// When
		Long orderId = orderManager.order(member.getId(), book.getId(), orderCount);

		// Then
		Order order = orderRepository.findOne(orderId);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER);
			softAssertions.assertThat(order.getOrderItems().size()).isEqualTo(1);
			softAssertions.assertThat(order.getTotalPrice()).isEqualTo(10000 * orderCount);
			softAssertions.assertThat(book.getStockQuantity()).isEqualTo(10 - orderCount);
		});
	}


	@Test
	@DisplayName("상품 주문 재고 수량 초과")
	public void order_exception() {
		// Given
		Member member = createMember();
		Book item = createBook("시골 JPA", 10000, 10);

		int orderCount = 11;

		// Then
		Assertions.assertThrows(NotEnoughStockException.class, () -> {
			// When
			orderManager.order(member.getId(), item.getId(), orderCount);
		});
	}


	@Test
	@DisplayName("주문 취소")
	public void cancelOrder() {
		// Given
		Member member = createMember();
		Book item = createBook("시골 JPA", 10000, 10);

		int orderCount = 2;
		Long orderId = orderManager.order(member.getId(), item.getId(), orderCount);

		// When
		orderManager.cancelOrder(orderId);

		// Then
		Order order = orderRepository.findOne(orderId);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
			softAssertions.assertThat(item.getStockQuantity()).isEqualTo(10);
		});
	}


	private Member createMember() {
		Member member = new Member();
		member.setName("회원 1");
		member.setAddress(new Address("서울", "강가", "123-123"));
		em.persist(member);
		return member;
	}

	private Book createBook(String name, int price, int quantity) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(quantity);
		em.persist(book);
		return book;
	}


}