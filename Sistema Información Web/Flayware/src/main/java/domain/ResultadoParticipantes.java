package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class ResultadoParticipantes extends DomainEntity{
	
	private Collection<Eleccion> elecciones;
	private Jugador jugador;
	private Partida partida; 
	private double puntuacion;
	private double tiempo;
	private boolean cerrada;
	
	public ResultadoParticipantes(){
		super();
		elecciones=new ArrayList<Eleccion>();
		
	}
	@Min(0)
	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	@Min(0)
	public double getTiempo() {
		return tiempo;
	}
	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	public boolean isCerrada() {
		return cerrada;
	}
	public void setCerrada(boolean cerrada) {
		this.cerrada = cerrada;
	}
	

	
	//RelationSHIP


	@Valid
	@NotNull
	@OneToMany(cascade= CascadeType.ALL)
	public Collection<Eleccion> getElecciones() {
		return elecciones;
	}

	public void setElecciones(Collection<Eleccion> elecciones) {
		this.elecciones = elecciones;
	}

	@Valid
	@ManyToOne(optional=false)
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador= jugador;
	}


	@ManyToOne
	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}


	
	
	

}
