package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Partida extends DomainEntity{
	
	private String estado;
	private Date fechaRealizacion;
	private Jugador ganador;
	private Reto reto;
	private Collection<ResultadoParticipantes> resultadoparticipantes;
	
	public Partida(){
		super();
		resultadoparticipantes=new ArrayList<ResultadoParticipantes>();
	}

	@NotBlank
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	@Transient
	public Jugador getGanador() {
		return ganador;
	}

	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}

	

	//RelationSHIP
	
	@Valid
	@ManyToOne(optional=false)
	public Reto getReto() {
		return reto;
	}

	public void setReto(Reto reto) {
		this.reto = reto;
	}

	@NotNull
	@OneToMany(mappedBy="partida",fetch=FetchType.LAZY)
	public Collection<ResultadoParticipantes> getResultadoparticipantes() {
		return resultadoparticipantes;
	}

	public void setResultadoparticipantes(
			Collection<ResultadoParticipantes> resultadoparticipantes) {
		this.resultadoparticipantes = resultadoparticipantes;
	}
	
	
}
