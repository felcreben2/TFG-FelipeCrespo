package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Jugador extends Actor{
	
	private Collection<ResultadoParticipantes> resultadoParticipantes;
	
	public Jugador(){
		super();
		resultadoParticipantes= new ArrayList<ResultadoParticipantes>();
	}


	@Valid
	@NotNull
	@OneToMany(mappedBy="jugador")
	public Collection<ResultadoParticipantes> getResultadoParticipantes() {
		return resultadoParticipantes;
	}

	public void  setResultadoParticipantes(Collection<ResultadoParticipantes> p) {
		resultadoParticipantes=p;
	}


	

}
