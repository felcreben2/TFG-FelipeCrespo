package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Reto extends DomainEntity{
	
	private String nombre;
	private double tiempoMax;
	private Date fechaValida;
	private Curso curso;
	private Boolean visible;
	private Administrador administrador;
	private Collection<Partida> partidas;
	
	public Reto(){
		super();
		partidas= new ArrayList<Partida>();
	}

	@NotBlank
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Min(1)
	public double getTiempoMax() {
		return tiempoMax;
	}

	public void setTiempoMax(double tiempoMax) {
		this.tiempoMax = tiempoMax;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getFechaValida() {
		return fechaValida;
	}

	public void setFechaValida(Date fechaValida) {
		this.fechaValida = fechaValida;
	}
	
	


	
	//RelationSHIP
	
	
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Valid
	@ManyToOne(optional=false)
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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
	@OneToMany(mappedBy="reto",fetch=FetchType.LAZY)
	public Collection<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(Collection<Partida> partidas) {
		this.partidas = partidas;
	}

	
	

}
