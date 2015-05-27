package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CursoRepository;
import domain.Curso;
import domain.Pregunta;
import domain.Reto;

@Transactional
@Service
public class CursoService{
		
		// Managed repository-----------------------

		@Autowired
		private CursoRepository cursoRepository;

		// Supporting services -----------------
		
		@Autowired
		private AdministradorService administradorService;
		

		@Autowired
		private RetoService retoService;
		
		@Autowired
		private PreguntaService preguntaService;
		
		
		// Constructors --------------------------
		public CursoService() {
			super();
		}

		// Simple CRUD methods -----------------
		
		
		 public Curso create() {
			Curso curso = new Curso();	
			curso.setAdministrador(administradorService.findByPrincipal());
			curso.setNumeroPreguntas(0);
			
			return curso;
		}
		 
		 
		 
		public Collection<Curso> findAll() {
			return cursoRepository.findAll();
		}


		public Curso findOne(int cursoId) {
			return cursoRepository.findOne(cursoId);
		}
		
		
		public void save(Curso curso) {
			//TODO Restricciones de Save
			Assert.notNull(curso);
			Assert.isTrue(curso.getAdministrador().equals(administradorService.findByPrincipal()));
//			
			cursoRepository.save(curso);
//			
		}
		
		
		public void delete(Curso curso) {
//			//TODO Restricciones de Borrado
			Assert.notNull(curso);
			//			
			Collection<Reto> retos= retoService.findRetoByCursoId(curso.getId());
			//Permite borrar el curso si no tiene partidas ya.
			Assert.isTrue(retos.isEmpty());
			Collection<Pregunta> preguntas = preguntaService.findPreguntasCurso(curso.getId());
			if(preguntas.size()>0){
			for(Pregunta p: preguntas){
				preguntaService.delete(p);
			}
			}
			cursoRepository.delete(curso);
		}

		public void updatePreguntas(Curso curso) {
			Assert.notNull(curso);
			
			cursoRepository.save(curso);
		}
		

		// Other business methods ----------------

		public Collection<Curso> findCursoByAdministradorId(int administradorId) {	
			Assert.notNull(administradorService.findOne(administradorId));
			return cursoRepository.findByAdministrador(administradorId);
		}

		
		//Asserts
		
//		public void cursoIsMyself(Curso curso){
//			Assert.isTrue(customerService.findByPrincipal().getId() == curso.getCustomer().getId(),"You aren't the owner.");	
//		}
		
}


