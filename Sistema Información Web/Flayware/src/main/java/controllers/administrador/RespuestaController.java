package controllers.administrador;


import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import services.PreguntaService;
import services.RespuestaService;
import controllers.AbstractController;
import domain.Respuesta;

@Controller
@RequestMapping("/respuesta/administrador")
public class RespuestaController extends AbstractController{
	
	// Services ----------------------------------------------------------------
		
		@Autowired
		private PreguntaService preguntaService;		
		
		@Autowired
		private RespuestaService respuestaService;		
	
	
		// Constructor ---------------------------------------------------------------
		public RespuestaController() {
			super();
		}
		
		// Listing -------------------------------------------------------------------
		
		@RequestMapping("/listaRespuestasPorPregunta")
		public ModelAndView listaRespuestas(@RequestParam int preguntaId) {
			ModelAndView result;
		
			Collection<Respuesta> respuestas = respuestaService.findRespuestasPregunta(preguntaId);
			String uri= "respuesta/administrador/listaRespuestasPorPregunta";
			String requestURI= "respuesta/administrador/listaRespuestasPorPregunta.do";
			result = createListModelAndView(respuestas,requestURI,uri);
			result.addObject("pregunta",preguntaService.findOne(preguntaId));
			
			return result;
		}
		
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int preguntaId) {
			ModelAndView result;
			
			Respuesta respuesta = respuestaService.create(preguntaId);		
			
			
			result = createEditModelAndView(respuesta);
			result.addObject("create", true);
			
			return result;
		}
				
		
		// Edition -------------------------------------------------------------------
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int respuestaId) throws IOException {		
			ModelAndView result;
			Respuesta respuesta = respuestaService.findOne(respuestaId);
			
			result = createEditModelAndView(respuesta);
			result.addObject("create", false);			
			
			return result;
		}
		

		
				 
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Respuesta respuesta, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = createEditModelAndView(respuesta);
			} else {
				try {
					respuestaService.save(respuesta);				
					result = new ModelAndView("redirect:../../respuesta/administrador/listaRespuestasPorPregunta.do?preguntaId=" +respuesta.getPregunta().getId());
					} catch (Throwable oops) {
						result = createEditModelAndView(respuesta, "respuesta.commit.error");	
					}
				result.addObject("create", false);
			}

			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(@Valid Respuesta respuesta, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = createEditModelAndView(respuesta);
			} else {
				try {
					respuestaService.delete(respuesta);				
					result = new ModelAndView("redirect:../../respuesta/administrador/listaRespuestasPorPregunta.do?preguntaId=" +respuesta.getPregunta().getId());
	} catch (Throwable oops) {
						result = createEditModelAndView(respuesta, "pregunta.commit.error");	
					}
				
				result.addObject("create", false);
			}

			return result;
		}
		
		@InitBinder
		protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
				binder.registerCustomEditor(byte[].class,
					new ByteArrayMultipartFileEditor());
		}
		
		
		//Other bussiness method
		protected ModelAndView createEditModelAndView(Respuesta respuesta){
			assert respuesta != null;
			
			ModelAndView result;

			result = createEditModelAndView(respuesta, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Respuesta respuesta, String message) {
			assert respuesta != null;
			
			ModelAndView result;				
			
			result = new ModelAndView("respuesta/administrador/edit");
			result.addObject("respuesta", respuesta);
			result.addObject("message", message);
			
			
			return result;
		}
		
		//Other bussiness method
		protected ModelAndView createListModelAndView(Collection<Respuesta> respuestas, String requestURI, String uri) {
			ModelAndView result;				
			
			
			result = new ModelAndView(uri);
			result.addObject("respuestas", respuestas);
			result.addObject("requestURI", requestURI);
			
			return result;
		}
		
		
	}

