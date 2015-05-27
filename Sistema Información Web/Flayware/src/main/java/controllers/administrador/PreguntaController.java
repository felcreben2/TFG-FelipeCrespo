package controllers.administrador;


import java.io.IOException;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CursoService;
import services.PreguntaService;
import controllers.AbstractController;
import domain.Pregunta;

@Controller
@RequestMapping("/pregunta/administrador")
public class PreguntaController extends AbstractController{
	
	// Services ----------------------------------------------------------------
		
		@Autowired
		private CursoService cursoService;		
		
		@Autowired
		private PreguntaService preguntaService;		
	
	
		// Constructor ---------------------------------------------------------------
		public PreguntaController() {
			super();
		}
		
		// Listing -------------------------------------------------------------------
		
		@RequestMapping("/listaPreguntasPorCurso")
		public ModelAndView listaPreguntas(@RequestParam int cursoId) {
			ModelAndView result;
		
			Collection<Pregunta> preguntas = preguntaService.findPreguntasCurso(cursoId);
			String uri= "pregunta/administrador/listaPreguntasPorCurso";
			String requestURI= "pregunta/administrador/listaPreguntasPorCurso.do";
			result = createListModelAndView(preguntas,requestURI,uri);
			
			return result;
		}
		
		@RequestMapping("/subir")
		public ModelAndView subir(@RequestParam int preguntaId) {
			ModelAndView result;
		
			Pregunta preguntaCambio=preguntaService.findOne(preguntaId);
			int numanterior= preguntaCambio.getNumero();
			Pregunta preguntavuelve=preguntaService.findByNumero(numanterior-1,preguntaCambio.getCurso().getId());
			preguntaCambio.setNumero(numanterior-1);
			preguntaService.saveModif(preguntaCambio);
			preguntavuelve.setNumero(numanterior);
			preguntaService.saveModif(preguntavuelve);
			result=new ModelAndView("redirect:../../curso/administrador/detalles.do?cursoId="+preguntaCambio.getCurso().getId());
		
			return result;
		}
		@RequestMapping("/bajar")
		public ModelAndView bajar(@RequestParam int preguntaId) {
			ModelAndView result;
		
			Pregunta preguntaCambio=preguntaService.findOne(preguntaId);
			int numanterior= preguntaCambio.getNumero();
			Pregunta preguntavuelve=preguntaService.findByNumero(numanterior+1,preguntaCambio.getCurso().getId());
			preguntaCambio.setNumero(numanterior+1);
			preguntaService.saveModif(preguntaCambio);
			preguntavuelve.setNumero(numanterior);
			preguntaService.saveModif(preguntavuelve);
			result=new ModelAndView("redirect:../../curso/administrador/detalles.do?cursoId="+preguntaCambio.getCurso().getId());
		
			return result;
		}
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int cursoId) {
			ModelAndView result;
			
			Pregunta pregunta = preguntaService.create(cursoId);		
			
			
			result = createEditModelAndView(pregunta);
			result.addObject("create", true);
			
			return result;
		}
				
		
		// Edition -------------------------------------------------------------------
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int preguntaId) throws IOException {		
			ModelAndView result;
			Pregunta pregunta = preguntaService.findOne(preguntaId);
			
			result = createEditModelAndView(pregunta);
			result.addObject("create", false);			
			
			return result;
		}
				 
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Pregunta pregunta, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = createEditModelAndView(pregunta);
			} else {
				try {
					if(pregunta.getVersion()==0){
					preguntaService.save(pregunta);
					}else{
						preguntaService.saveModif(pregunta);
					}
					result = new ModelAndView("redirect:../../curso/administrador/detalles.do?cursoId="+pregunta.getCurso().getId());
				} catch (Throwable oops) {
						result = createEditModelAndView(pregunta, "pregunta.commit.error");	
					}
				result.addObject("create", false);
			}

			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(@Valid Pregunta pregunta, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = createEditModelAndView(pregunta);
			} else {
				try {
					preguntaService.delete(pregunta);				
					result = new ModelAndView("redirect:../../curso/administrador/detalles.do?cursoId="+pregunta.getCurso().getId());
				} catch (Throwable oops) {
						result = createEditModelAndView(pregunta, "curso.commit.error");	
					}
				
				result.addObject("create", false);
			}

			return result;
		}
		
		
		//Other bussiness method
		protected ModelAndView createEditModelAndView(Pregunta pregunta){
			assert pregunta != null;
			
			ModelAndView result;

			result = createEditModelAndView(pregunta, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Pregunta pregunta, String message) {
			assert pregunta != null;
			
			ModelAndView result;				
			
			result = new ModelAndView("pregunta/administrador/edit");
			result.addObject("pregunta", pregunta);
			result.addObject("message", message);
			
			
			return result;
		}
		
		//Other bussiness method
		protected ModelAndView createListModelAndView(Collection<Pregunta> preguntas, String requestURI, String uri) {
			ModelAndView result;				
			
			
			result = new ModelAndView(uri);
			result.addObject("preguntas", preguntas);
			result.addObject("requestURI", requestURI);
			
			return result;
		}
		
		
	}

