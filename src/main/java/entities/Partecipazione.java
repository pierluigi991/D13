package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "partecipazione")
@Getter
@Setter
@NoArgsConstructor
public class Partecipazione {
	@Id
	@GeneratedValue(generator = "partecipazione_seq")
	@SequenceGenerator(name = "partecipazione_seq", sequenceName = "partecipazione_seq", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "persona_id")
	private Persona persona;

	@ManyToOne
	@JoinColumn(name = "evento_id")
	private Evento evento;

	@Enumerated(EnumType.STRING)
	private StatoPartecipazione stato;

	public Partecipazione(Persona persona, Evento evento, StatoPartecipazione stato) {
		this.persona = persona;
		this.evento = evento;
		this.stato = stato;
	}

	public enum StatoPartecipazione {
		CONFERMATA, DA_CONFERMARE
	}

	@Override
	public String toString() {
		return "[id=" + id + ", persona=" + persona + ", evento=" + evento + ", stato=" + stato + "]";
	}
}