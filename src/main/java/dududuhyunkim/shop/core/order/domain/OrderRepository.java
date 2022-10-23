package dududuhyunkim.shop.core.order.domain;

public interface OrderRepository {
	void save(Order order);

	Order findOne(Long id);
}
