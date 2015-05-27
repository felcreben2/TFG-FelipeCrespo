package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Eleccion extends DomainEntity{
	
	private Date selloDelTiempo;
	private Respuesta respuesta;
	private ResultadoParticipantes resultadoParticipantes;
	
	public Eleccion(){
		super();
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getSelloDelTiempo() {
		return selloDelTiempo;
	}



	public void setSelloDelTiempo(Date selloDelTiempo) {
		this.selloDelTiempo = selloDelTiempo;
	}


	
	
	//RelationSHIP

	@ManyToOne(optional=false)
	public Respuesta getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@ManyToOne(optional=false)
	public ResultadoParticipantes getResultadoParticipantes() {
		return resultadoParticipantes;
	}


	public void setResultadoParticipantes(
			ResultadoParticipantes resultadoParticipantes) {
		this.resultadoParticipantes = resultadoParticipantes;
	}



	
	
	
	



	

}
