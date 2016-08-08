package vol.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.Application;
import vol.model.CompagnieAerienneVol;

@Repository
@Transactional
public class CompagnieAerienneVolDaoJpa implements CompagnieAerienneVolDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public CompagnieAerienneVol findById(Integer id) {
		return em.find(CompagnieAerienneVol.class, id);
	}

	@Override
	public List<CompagnieAerienneVol> findAll() {
		Query query = em.createQuery("select c from CompagnieAerienneVol c");
		return query.getResultList();
	}

	@Override
	public void create(CompagnieAerienneVol obj) {
		em.persist(obj);
	}

	@Override
	public CompagnieAerienneVol update(CompagnieAerienneVol obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(CompagnieAerienneVol obj) {
		em.remove(em.merge(obj));
	}

}
