package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RetoRepository;
import domain.Curso;
import domain.Partida;
import domain.Pregunta;
import domain.Reto;

@Transactional
@Service
public class RetoService{
		
		// Managed repository-----------------------

		@Autowired
		private RetoRepository retoRepository;

		// Supporting services -----------------
		
		@Autowired
		private AdministradorService administradorService;

		@Autowired
		private PreguntaService preguntaService;
		
		@Autowired
		private CursoService cursoService;
		
		
		// Constructors --------------------------
		public RetoService() {
			super();
		}

		// Simple CRUD methods -----------------
		
		
		 public Reto create(int cursoId) {
			Reto reto = new Reto();	
			Curso x= cursoService.findOne(cursoId)	;
			reto.setCurso(x);
			reto.setAdministrador(administradorService.findByPrincipal());
			Date date= new Date();
			reto.setFechaValida(date);
			return reto;
		}
		 
		 
		 
		public Collection<Reto> findAll() {
			return retoRepository.findAll();
		}


		public Reto findOne(int retoId) {
			return retoRepository.findOne(retoId);
		}
		
		
		public void save(Reto reto) {
			//TODO Restricciones de Save
			Assert.notNull(reto);
			Assert.isTrue(reto.getAdministrador().equals(administradorService.findByPrincipal()));
			Assert.isTrue(reto.getCurso().getNumeroPreguntas()==20);
			Assert.isTrue(findPartidasByReto(reto.getId()).isEmpty());
			//			
//			reto.setCustomer(customerService.findByPrincipal());
			retoRepository.save(reto);
//			
		}
		
		
		public void delete(Reto reto) {
//			//TODO Restricciones de Borrado
			Assert.notNull(reto);
			Assert.isTrue(reto.getAdministrador().equals(administradorService.findByPrincipal()));
			Assert.isTrue(findPartidasByReto(reto.getId()).isEmpty());
			retoRepository.delete(reto);
		}

		public Collection<Reto> findRetoByAdministradorId(int id) {
			// TODO Auto-generated method stub
			return retoRepository.findByAdmin(id);
		}

		public Collection<Reto> findRetoByCursoId(int id) {
			// TODO Auto-generated method stub
			return retoRepository.findByCurso(id);
		}

		public Collection<Partida> findPartidasByReto(int id) {
			// TODO Auto-generated method stub
			return retoRepository.findPartidasByReto(id);
		}

		public boolean findPreguntaEnRetoPartida(int id) {
			// TODO Auto-generated method stub
			Pregunta p =preguntaService.findOne(id);
			Curso c= p.getCurso();
			Collection<Reto> retos= findRetoByCursoId(c.getId());
			boolean res=false;
			for(Reto r:retos){
				Collection<Partida> partidas=findPartidasByReto(r.getId());
				if(!partidas.isEmpty()){
					res=true;
					break;
				}
			}
			return res;
		}

		// Other business methods ----------------


		
		
		
}


