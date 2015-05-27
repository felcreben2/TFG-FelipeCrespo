package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta,Integer>{

	@Query("select r from Respuesta r where r.pregunta.id= ?1")
	Collection<Respuesta> findRespuestasPregunta(int preguntaId);
	
	
}
