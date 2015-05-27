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
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Pregunta extends DomainEntity{
	
	private String enunciado;
	private double valorPregunta;
	private Integer numero;
	private Collection<Respuesta> respuestas;
	private Curso curso;
	
	public Pregunta(){
		super();
		respuestas= new ArrayList<Respuesta>();
	}

	@NotBlank
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	@Max(1)
	public double getValorPregunta() {
		return valorPregunta;
	}

	public void setValorPregunta(double valorPregunta) {
		this.valorPregunta = valorPregunta;
	}



	
	
	
//RelationSHIP

	@Min(0)
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy="pregunta",fetch=FetchType.LAZY)
	public Collection<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(Collection<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
	
}
