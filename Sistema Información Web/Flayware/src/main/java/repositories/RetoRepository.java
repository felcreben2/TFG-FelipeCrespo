package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Partida;
import domain.Reto;

@Repository
public interface RetoRepository extends JpaRepository<Reto,Integer>{


	@Query("select r from Reto r where r.administrador.id= ?1")
	Collection<Reto> findByAdmin(int id);
	
	@Query("select r from Partida p inner join p.reto r where r.curso.id=?1 and p.reto.id=r.id")
	Collection<Reto> findByCurso(int id);
	
	@Query("select p from Partida p where p.reto.id=?1")
	Collection<Partida> findPartidasByReto(int id);
	
	
	
}
