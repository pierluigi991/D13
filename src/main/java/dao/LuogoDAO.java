package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Luogo;

public class LuogoDAO {
	private static final Logger logger = LoggerFactory.getLogger(LuogoDAO.class);
	private final EntityManager em;

	public LuogoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Luogo location) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(location);
			transaction.commit();
			logger.info("Luogo Salvato: " + location);
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Errore salvataggio.", e);
			throw e;
		}
	}

	public Luogo getById(Long id) {
		Luogo found = em.find(Luogo.class, id);
		return found;
	}

	public void delete(Luogo location) {
		if (location != null) {
			EntityTransaction transaction = em.getTransaction();
			try {
				transaction.begin();
				em.remove(location);
				transaction.commit();
				logger.info("Luogo eliminato: " + location);
			} catch (Exception e) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
				logger.error("Errore eliminazione.", e);
				throw e;
			}
		}
	}

	public void refresh(Luogo location) {
		if (location != null) {
			logger.info("Location aggiornata:" + location);
			em.refresh(location);
			logger.info("Location recuperata correttamente:" + location);
		}
	}
}