package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RespuestaRepository;
import domain.Pregunta;
import domain.Respuesta;

@Transactional
@Service
public class RespuestaService{
		
		// Managed repository-----------------------

		@Autowired
		private RespuestaRepository respuestaRepository;

		// Supporting services -----------------
		
		@Autowired
		private AdministradorService administradorService;
		@Autowired
		private PreguntaService preguntaService;
		
		// Constructors --------------------------
		public RespuestaService() {
			super();
		}

		// Simple CRUD methods -----------------
		
		
		 public Respuesta create(Integer id) {
			 Respuesta respuesta = new Respuesta();	
			 Pregunta pregunta= preguntaService.findOne(id);
			 respuesta.setPregunta(pregunta);
			
			return respuesta;
		}
		 
		 
		 
		public Collection<Respuesta> findAll() {
			return respuestaRepository.findAll();
		}


		public Respuesta findOne(int respuestaId) {
			return respuestaRepository.findOne(respuestaId);
		}
		
		
		public void save(Respuesta respuesta) {
			//TODO Restricciones de Save
			Assert.notNull(respuesta);
			Assert.isTrue(respuesta.getPregunta().getCurso().getAdministrador().equals(administradorService.findByPrincipal()));
//			
			if(respuesta.getId()==0){
				Assert.isTrue(respuesta.getPregunta().getRespuestas().size()<=3);
				respuestaRepository.save(respuesta);
			}else{
				respuestaRepository.save(respuesta);
			}
			
//			
		}
		
		
		public void delete(Respuesta respuesta) {
//			//TODO Restricciones de Borrado
			Assert.notNull(respuesta);
			Assert.isTrue(respuesta.getPregunta().getCurso().getAdministrador().equals(administradorService.findByPrincipal()));
//			
			//			
			respuestaRepository.delete(respuesta);
		}


		// Other business methods ----------------

		public Collection<Respuesta> findRespuestasPregunta(int preguntaId) {	
			Assert.notNull(preguntaService.findOne(preguntaId));
			return respuestaRepository.findRespuestasPregunta(preguntaId);
		}

		public Collection<Respuesta> findRespuestas(int id) {
			// TODO Auto-generated method stub
			return respuestaRepository.findRespuestasPregunta(id);
		}
		
		//Asserts
		
//		public void respuestaIsMyself(respuesta respuesta){
//			Assert.isTrue(customerService.findByPrincipal().getId() == respuesta.getCustomer().getId(),"You aren't the owner.");	
//		}
		
}


