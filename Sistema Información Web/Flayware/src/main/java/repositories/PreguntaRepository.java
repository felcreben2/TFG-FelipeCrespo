package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Pregunta;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta,Integer>{

	@Query("select p from Pregunta p where p.curso.id= ?1 order by p.numero")
	Collection<Pregunta> findPreguntasCurso(int cursoId);

	@Query("select p from Pregunta p where p.curso.id= ?2 and p.numero= ?1")
	Pregunta findPreguntaByNumero(int i,int j);

	
	
	
	
}
