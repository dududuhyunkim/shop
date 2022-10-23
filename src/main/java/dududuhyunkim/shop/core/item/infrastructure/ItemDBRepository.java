package dududuhyunkim.shop.core.item.infrastructure;

import dududuhyunkim.shop.core.item.domain.Item;
import dududuhyunkim.shop.core.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemDBRepository implements ItemRepository {

	private final EntityManager em;

	@Override
	public void save(Item item) {
		if (item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
		}
	}

	@Override
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	@Override
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}
}
