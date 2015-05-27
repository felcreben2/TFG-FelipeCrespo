package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador,Integer>{

	@Query("select j from Jugador j where j.userAccount.id= ?1")
	Jugador findByUserAccountId(int id);
	
	@Query("select j from Jugador j where j.userAccount.active = 0")
	Collection<Jugador> findBannerJugadores();
	
}
