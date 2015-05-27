package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PreguntaRepository;
import domain.Curso;
import domain.Pregunta;
import domain.Respuesta;
import domain.Reto;

@Transactional
@Service
public class PreguntaService{
		
		// Managed repository-----------------------

		@Autowired
		private PreguntaRepository preguntaRepository;

		// Supporting services -----------------
		
		@Autowired
		private RespuestaService respuestaService;
		@Autowired
		private AdministradorService administradorService;
		@Autowired
		private CursoService cursoService;

		@Autowired
		private RetoService retoService;
		
		// Constructors --------------------------
		public PreguntaService() {
			super();
		}

		// Simple CRUD methods -----------------
		
		
		 public Pregunta create(Integer id) {
			 Pregunta pregunta = new Pregunta();	
			 Curso curso= cursoService.findOne(id);
			 pregunta.setNumero(curso.getNumeroPreguntas()+1);
			 pregunta.setValorPregunta(1);
			 pregunta.setCurso(curso);
			
			return pregunta;
		}
		 
		 
		 
		public Collection<Pregunta> findAll() {
			return preguntaRepository.findAll();
		}


		public Pregunta findOne(int preguntaId) {
			return preguntaRepository.findOne(preguntaId);
		}
		
		
		public void save(Pregunta pregunta) {
			//TODO Restricciones de Save
			Assert.notNull(pregunta);
			Assert.isTrue(pregunta.getCurso().getAdministrador().equals(administradorService.findByPrincipal()));
			if(pregunta.getId()!=0){
				Boolean res =retoService.findPreguntaEnRetoPartida(pregunta.getId());
				Assert.isTrue(res==false);
			}
			
			Assert.isTrue(pregunta.getCurso().getPreguntas().size()<=20);
			preguntaRepository.save(pregunta);
			
			Curso c= pregunta.getCurso();
			int other= c.getNumeroPreguntas();
			c.setNumeroPreguntas(other+1);
			cursoService.updatePreguntas(c);
//			
		}
		
		
		public void delete(Pregunta pregunta) {
//			//TODO Restricciones de Borrado
			Assert.notNull(pregunta);
			//			
			Boolean res =retoService.findPreguntaEnRetoPartida(pregunta.getId());
			Assert.isTrue(res==false);
			Collection<Respuesta> respuestas = respuestaService.findRespuestas(pregunta.getId());
			if(respuestas.size()>0){
			for(Respuesta p: respuestas){
				respuestaService.delete(p);
			}
			}
			
			preguntaRepository.delete(pregunta);
			Curso c= pregunta.getCurso();
			int other= c.getNumeroPreguntas();
			c.setNumeroPreguntas(other-1);
			cursoService.updatePreguntas(c);
		}
		
		

		public void saveModif(Pregunta pregunta) {
			Assert.notNull(pregunta);
			Assert.isTrue(pregunta.getCurso().getAdministrador().equals(administradorService.findByPrincipal()));
//			
			Assert.isTrue(pregunta.getCurso().getPreguntas().size()<=20);
			preguntaRepository.save(pregunta);
		}


		// Other business methods ----------------

		public Collection<Pregunta> findPreguntasCurso(int cursoId) {	
			Assert.notNull(cursoService.findOne(cursoId));
			return preguntaRepository.findPreguntasCurso(cursoId);
		}

		public Pregunta findByNumero(int i,int j) {
			// TODO Auto-generated method stub
			return preguntaRepository.findPreguntaByNumero(i,j);
		}
		
		
		
		//Asserts
		
//		public void preguntaIsMyself(pregunta pregunta){
//			Assert.isTrue(customerService.findByPrincipal().getId() == pregunta.getCustomer().getId(),"You aren't the owner.");	
//		}
		
}


