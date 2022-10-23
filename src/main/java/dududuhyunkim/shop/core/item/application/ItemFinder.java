package dududuhyunkim.shop.core.item.application;

import dududuhyunkim.shop.core.item.application.dto.ItemDto;

import java.util.List;

public interface ItemFinder {
	List<ItemDto> findItems();

	ItemDto findOne(Long itemId);
}
