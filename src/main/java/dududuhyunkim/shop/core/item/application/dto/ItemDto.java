package dududuhyunkim.shop.core.item.application.dto;

import dududuhyunkim.shop.core.item.domain.Item;
import lombok.Getter;

@Getter
public class ItemDto {
	private Long id;
	private String name;
	private int price;
	private int stockQuantity;

	public static ItemDto ofEntity(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.id = item.getId();
		itemDto.name = item.getName();
		itemDto.price = item.getPrice();
		itemDto.stockQuantity = item.getStockQuantity();

		return itemDto;
	}
}
