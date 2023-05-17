package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.EventoDAO;
import dao.LuogoDAO;
import dao.PartecipazioneDAO;
import dao.PersonaDAO;
import entities.Evento;
import entities.Evento.TipoEvento;
import entities.Luogo; 
import entities.Partecipazione;
import entities.Persona;
import utils.JpaUtil;

public class GestioneEventi {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		PersonaDAO personaDAO = new PersonaDAO(em);
		LuogoDAO locationDAO = new LuogoDAO(em);
		EventoDAO eventoDAO = new EventoDAO(em);
		PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO();

		Persona persona = new Persona("Novak", "Djokovic", "Novak.djoko@gmail.it", LocalDate.of(1990, 5, 10),
				Persona.Sesso.MASCHIO);
		personaDAO.save(persona);

		Luogo location = new Luogo("Foro italico", "Roma");
		locationDAO.save(location);

		Evento evento = new Evento();
		evento.setTitolo("Match-Day");
		evento.setDataEvento(LocalDate.of(2023, 5, 17));
		evento.setDescrizione("Atp Roma");
		evento.setTipoEvento(TipoEvento.PUBBLICO);
		evento.setNumeroMassimoPartecipanti(10000);
		evento.setLuogo(Luogo);
		eventoDAO.save(evento);

		Partecipazione partecipazione = new Partecipazione(persona, evento,
				Partecipazione.StatoPartecipazione.DA_CONFERMARE);
		partecipazioneDAO.save(partecipazione);

		em.close();
		emf.close();

	}

}