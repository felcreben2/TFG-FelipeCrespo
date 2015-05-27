package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida,Integer>{
	
	
}
