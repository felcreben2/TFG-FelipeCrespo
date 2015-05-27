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

import services.AdministradorService;
import services.RetoService;
import services.PreguntaService;
import controllers.AbstractController;
import domain.Administrador;
import domain.Reto;
import domain.Pregunta;

@Controller
@RequestMapping("/reto/administrador")
public class RetoController extends AbstractController{
	
	// Services ----------------------------------------------------------------
		
		@Autowired
		private RetoService retoService;
		
		@Autowired
		private AdministradorService administradorService;	
		
		@Autowired
		private PreguntaService preguntaService;	
		
		// Constructor ---------------------------------------------------------------
		public RetoController() {
			super();
		}
		
		// Listing -------------------------------------------------------------------
		
		@RequestMapping("/listaRetos")
		public ModelAndView listaRetos() {
			ModelAndView result;
		
			Boolean other=true;
			Collection<Reto> retos = retoService.findAll();
			String uri= "reto/administrador/listaRetos";
			String requestURI= "reto/administrador/listaRetos.do";
			result = createListModelAndView(retos,requestURI,uri);
			
			return result;
		}
		
		
		@RequestMapping("/listaMisRetos")
		public ModelAndView listaMisRetos() {
			ModelAndView result;


			Administrador administrador= administradorService.findByPrincipal();
			Collection<Reto> retos = retoService.findRetoByAdministradorId(administrador.getId());
			String uri= "reto/administrador/listaMisRetos";
			String requestURI= "reto/administrador/listaMisRetos.do";
			result = createListModelAndView(retos,requestURI,uri);
			
			return result;
		}
		
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int cursoId) {
			ModelAndView result;
			
			Reto reto = retoService.create(cursoId);		
			
			
			result = createEditModelAndView(reto);
			result.addObject("create", true);
			
			return result;
		}
		
		@RequestMapping(value = "/detalles", method = RequestMethod.GET)
		public ModelAndView details(@RequestParam int retoId) {		
			ModelAndView result;
		
			Reto reto = retoService.findOne(retoId);
			
							
			result = new ModelAndView("reto/administrador/detalles");
			result.addObject("reto", reto);
			String requestURI= "reto/administrador/detalles.do";
			result.addObject("requestURI",requestURI);
			result.addObject("detalles",true);
			

			
			return result;
		}
		
		
		// Edition -------------------------------------------------------------------
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit( @RequestParam int retoId) throws IOException {		
			ModelAndView result;
			Reto reto = retoService.findOne(retoId);
			
			result = createEditModelAndView(reto);
			result.addObject("create", false);			
			
			return result;
		}
				 
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Reto reto, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = createEditModelAndView(reto);
			} else {
				try {
					retoService.save(reto);				
					result = new ModelAndView("redirect:listaRetos.do");
				} catch (Throwable oops) {
						result = createEditModelAndView(reto, "reto.commit.error");	
					}
				result.addObject("create", false);
			}

			return result;
		}
		
		@RequestMapping(value = "/detalles", method = RequestMethod.POST, params = "saveD")
		public ModelAndView saveD(@Valid Reto reto, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = new ModelAndView("reto/administrador/detalles");
				result.addObject("reto", reto);
				String requestURI= "reto/administrador/detalles.do";
				result.addObject("requestURI",requestURI);
				result.addObject("detalles",true);
			} else {
				try {
					retoService.save(reto);				
					result = new ModelAndView("redirect:listaRetos.do");
				} catch (Throwable oops) {
					result = new ModelAndView("reto/administrador/detalles");
					result.addObject("reto", reto);
					String requestURI= "reto/administrador/detalles.do";
					result.addObject("requestURI",requestURI);
					result.addObject("detalles",true);
					}
				result.addObject("create", false);
			}

			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(@Valid Reto reto, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = createEditModelAndView(reto);
			} else {
				try {
					retoService.delete(reto);				
					result = new ModelAndView("redirect:listaRetos.do");
				} catch (Throwable oops) {
						result = createEditModelAndView(reto, "reto.commit.error");	
					}
				
				result.addObject("create", false);
			}

			return result;
		}
		
		
		//Other bussiness method
		protected ModelAndView createEditModelAndView(Reto reto) {
			assert reto != null;
			
			ModelAndView result;

			result = createEditModelAndView(reto, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Reto reto, String message) {
			assert reto != null;
			
			ModelAndView result;				
			
			result = new ModelAndView("reto/administrador/edit");
			result.addObject("reto", reto);
			result.addObject("message", message);
			
			
			return result;
		}
		
		//Other bussiness method
				protected ModelAndView createCreateModelAndView(Reto reto) {
					assert reto != null;
					
					ModelAndView result;

					result = createCreateModelAndView(reto, null);
					
					return result;
				}
				
				protected ModelAndView createCreateModelAndView(Reto reto, String message) {
					assert reto != null;
					
					ModelAndView result;				
					
					result = new ModelAndView("reto/administrador/añadir");
					result.addObject("reto", reto);
					result.addObject("message", message);
					
					
					return result;
				}
		
		
		protected ModelAndView createListModelAndView(Collection<Reto> retos, String requestURI, String uri) {
			ModelAndView result;				
			
			
			result = new ModelAndView(uri);
			result.addObject("retos", retos);
			result.addObject("requestURI", requestURI);
			
			return result;
		}
		
	}

