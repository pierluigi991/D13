package entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
public class Persona {
	@Id
	@GeneratedValue(generator = "persona_seq")
	@SequenceGenerator(name = "persona_seq", sequenceName = "persona_seq", allocationSize = 1)
	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private LocalDate dataNascita;
	private Sesso sesso;

	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
	private List<Partecipazione> listaPartecipazioni;

	public Persona(String nome, String cognome, String email, LocalDate dataNascita, Sesso sesso) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
	}

	public enum Sesso {
		MASCHIO, FEMMINA
	}

	@Override
	public String toString() {
		return "[id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", dataNascita="
				+ dataNascita + ", sesso=" + sesso + "]";
	}
}
