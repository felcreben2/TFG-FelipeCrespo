package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Curso extends DomainEntity{
	
	private String nombre;
	private int numeroPreguntas;
	private String tematica;
	private Administrador administrador;
	private Collection<Reto> retos;
	private Collection<Pregunta> preguntas;
	
	public Curso(){
		super();
		retos= new ArrayList<Reto>();
		preguntas= new ArrayList<Pregunta>();
	}
		

	@NotBlank
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@NotBlank
	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	
//RelationSHIP
	

	@Min(0)
	public int getNumeroPreguntas() {
		return numeroPreguntas;
	}

	public void setNumeroPreguntas(int numeroPreguntas) {
		this.numeroPreguntas = numeroPreguntas;
	}


	@Valid
	@ManyToOne(optional=false)
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}




	@Valid
	@NotNull
	@OneToMany(mappedBy="curso")
	public Collection<Reto> getRetos() {
		return retos;
	}

	public void setRetos(Collection<Reto> retos) {
		this.retos = retos;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy="curso",fetch=FetchType.LAZY)
	public Collection<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Collection<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	
	
}
