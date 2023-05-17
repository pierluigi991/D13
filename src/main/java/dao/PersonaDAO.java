package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Partecipazione;
import entities.Persona;

public class PersonaDAO {
	private static final Logger logger = LoggerFactory.getLogger(PersonaDAO.class);
	private final EntityManager em;

	public PersonaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Persona persona) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(persona);
			transaction.commit();
			logger.info("Persona salvata : " + persona);
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Errore salvataggio.", e);
			throw e;
		}
	}

	public Persona getById(Long id) {
		Persona found = em.find(Persona.class, id);
		return found;
	}

	public void delete(Persona persona) {
		if (persona != null) {
			EntityTransaction transaction = em.getTransaction();
			try {
				transaction.begin();
				em.remove(persona);
				transaction.commit();
				logger.info("Persona eliminata: " + persona);
			} catch (Exception e) {
				if (transaction.isActive()) {
					transaction.rollback();
				}
				logger.error("Errore eliminazione.", e);
				throw e;
			}
		}
	}

	public void refresh(Persona persona) {
		if (persona != null) {
			logger.info("Persona aggiornata:" + persona);
			em.refresh(persona);
			logger.info("Persona recuperata :" + persona);
		}
	}


	public void refresh(Partecipazione partecipazione) {
		if (partecipazione != null) {
			logger.info("Partecipazione aggiornata:" + partecipazione);
			em.refresh(partecipazione);
			logger.info("Partecipazione recuperata:" + partecipazione);
		}
	}
}