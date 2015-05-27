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
public class Administrador extends Actor{
	

	private Collection<Curso> cursos;
	private Collection<Reto> retos;
	
	
	public Administrador(){
		super();
		cursos= new ArrayList<Curso>();
		retos= new ArrayList<Reto>();
	}


	@Valid
	@NotNull
	@OneToMany(mappedBy="administrador")
	public Collection<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Collection<Curso> cursos) {
		this.cursos = cursos;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="administrador")
	public Collection<Reto> getRetos() {
		return retos;
	}

	public void setRetos(Collection<Reto> retos) {
		this.retos = retos;
	}
}
