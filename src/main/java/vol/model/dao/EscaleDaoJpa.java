package vol.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.Application;
import vol.model.Escale;

@Repository
@Transactional
public class EscaleDaoJpa implements EscaleDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public Escale findById(Integer id) {
		return em.find(Escale.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Escale> findAll() {
		Query query = em.createQuery("select c from Escale c");
		return query.getResultList();
	}

	@Override
	public void create(Escale obj) {
		em.persist(obj);
	}

	@Override
	public Escale update(Escale obj) {
		EntityTransaction tx = null;
		Escale escale = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.merge(obj);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return escale;
	}

	@Override
	public void delete(Escale obj) {
		em.remove(em.merge(obj));
	}

}
