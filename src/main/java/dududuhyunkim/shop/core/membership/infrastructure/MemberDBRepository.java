package dududuhyunkim.shop.core.membership.infrastructure;

import dududuhyunkim.shop.core.membership.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberDBRepository implements dududuhyunkim.shop.core.membership.domain.MemberRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Member member) {
		em.persist(member);
	}

	@Override
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

	@Override
	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
}
