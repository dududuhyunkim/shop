package dududuhyunkim.shop.core.item.domain;

import java.util.List;

public interface ItemRepository {
	void save(Item item);

	Item findOne(Long id);

	List<Item> findAll();
}
