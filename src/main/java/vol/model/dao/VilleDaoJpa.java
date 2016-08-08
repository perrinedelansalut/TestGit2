package vol.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.model.Passager;
import vol.model.Reservation;
import vol.model.Ville;
import vol.model.Vol;



@Repository
@Transactional
public class VilleDaoJpa implements VilleDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public Ville findById(Integer id) {
		return em.find(Ville.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Ville> findAll() {
		Query query = em.createQuery("select vi from Ville vi");
		return query.getResultList();
	}

	@Override
	public void create(Ville obj) {
		em.persist(obj);
	}

	@Override
	public Ville update(Ville obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Ville obj) {
		em.remove(em.merge(obj));
	}



}
