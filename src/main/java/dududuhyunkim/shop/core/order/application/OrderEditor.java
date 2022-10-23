package dududuhyunkim.shop.core.order.application;

public interface OrderEditor {
	Long order(Long memberId, Long itemId, int count);

	void cancelOrder(Long orderId);
}
