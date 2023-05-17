package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Evento;

public class EventoDAO {
	private static final Logger logger = LoggerFactory.getLogger(EventoDAO.class);
	private final EntityManager em;

	public EventoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Evento event) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(event);
			transaction.commit();
			logger.info("Evento salvato correttamente: " + event);
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Errore durante il salvataggio dell'evento.", e);
			throw e;
		}
	}

	public Evento getById(Long id) {
		Evento found = em.find(Evento.class, id);
		return found;
	}

	public void delete(Evento event) {
		if (event != null) {
			EntityTransaction transaction = em.getTransaction();
			try {
				transaction.begin();
				em.remove(event);
				transaction.commit();
				logger.info("Evento eliminato : " + event);
			} catch (Exception e) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
				logger.error("Errore eliminazione.", e);
				throw e;
			}
		}
	}

	public void refresh(Evento event) {
		if (event != null) {
			logger.info("Evento aggiornato:" + event);
			em.refresh(event);
			logger.info("Evento recuperato :" + event);
		}
	}
}