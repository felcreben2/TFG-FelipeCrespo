package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ResultadoParticipantes;

@Repository
public interface ResultadoParticipantesRepository extends JpaRepository<ResultadoParticipantes,Integer>{
	
	
}
