package dududuhyunkim.shop.core.order.infrastructure;

import dududuhyunkim.shop.core.order.application.dto.OrderSearchCommand;
import dududuhyunkim.shop.core.order.domain.Order;
import dududuhyunkim.shop.core.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDBRepository implements OrderRepository {
	private final EntityManager em;

	@Override
	public void save(Order order) {
		em.persist(order);
	}

	@Override
	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}

	/**
	 * JPA Criteria
	 */
	public List<Order> findAll(OrderSearchCommand orderSearchCommand) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);

		Root<Order> o = cq.from(Order.class);
		Join<Object, Object> m = o.join("member", JoinType.INNER);

		List<Predicate> criteria = new ArrayList<>();

		if (orderSearchCommand.getOrderStatus() != null) {
			Predicate status = cb.equal(o.get("status"), orderSearchCommand.getOrderStatus());
			criteria.add(status);
		}

		if (StringUtils.hasText(orderSearchCommand.getMemberName())) {
			Predicate name = cb.like(m.get("name"), "%" + orderSearchCommand.getMemberName() + "%");
			criteria.add(name);
		}

		cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
		TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
		return query.getResultList();
	}
}
