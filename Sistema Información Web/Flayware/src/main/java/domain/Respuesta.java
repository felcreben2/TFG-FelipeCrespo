package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Respuesta extends DomainEntity{
	
	private String texto;
	private byte[] image;
	private double puntuacion;
	private Boolean solucion; 
	private Pregunta pregunta;
	private Collection<Eleccion> elecciones;
	
	public Respuesta(){
		super();
		elecciones= new ArrayList<Eleccion>();
	}

	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Max(1)
	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	
	public Boolean getSolucion() {
		return solucion;
	}

	public void setSolucion(Boolean solucion) {
		this.solucion = solucion;
	}



	@Lob
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Transient
	public boolean getValidImage(){
		return !(this.getImage() == null || this.getImage().length == 0);
	}
	
	
	//RelationSHIP

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy="respuesta")
	public Collection<Eleccion> getElecciones() {
		return elecciones;
	}

	public void setElecciones(Collection<Eleccion> elecciones) {
		this.elecciones = elecciones;
	}

	
	
	
}
