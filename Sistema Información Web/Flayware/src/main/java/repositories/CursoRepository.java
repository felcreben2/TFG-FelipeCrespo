package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Integer>{

	@Query("select c from Curso c where c.administrador.id= ?1")
	Collection<Curso> findByAdministrador(int administradorId);
	
	
}
