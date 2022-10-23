package dududuhyunkim.shop.core.item.application;

import dududuhyunkim.shop.core.item.application.dto.ItemDto;
import dududuhyunkim.shop.core.item.domain.Item;
import dududuhyunkim.shop.core.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemManager implements ItemFinder, ItemEditor {

	private final ItemRepository itemRepository;


	@Transactional
	@Override
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ItemDto> findItems() {
		return itemRepository.findAll().stream().map(ItemDto::ofEntity).toList();
	}

	@Transactional(readOnly = true)
	@Override
	public ItemDto findOne(Long itemId) {
		return ItemDto.ofEntity(itemRepository.findOne(itemId));
	}
}
