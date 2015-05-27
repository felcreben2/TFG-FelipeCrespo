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
import services.CursoService;
import services.PreguntaService;
import controllers.AbstractController;
import domain.Administrador;
import domain.Curso;
import domain.Pregunta;

@Controller
@RequestMapping("/curso/administrador")
public class CursoController extends AbstractController{
	
	// Services ----------------------------------------------------------------
		
		@Autowired
		private CursoService cursoService;
		
		@Autowired
		private AdministradorService administradorService;	
		
		@Autowired
		private PreguntaService preguntaService;	
		
		// Constructor ---------------------------------------------------------------
		public CursoController() {
			super();
		}
		
		// Listing -------------------------------------------------------------------
		
		@RequestMapping("/listaCursos")
		public ModelAndView listaCursos() {
			ModelAndView result;
		
			Boolean other=true;
			Collection<Curso> cursos = cursoService.findAll();
			String uri= "curso/administrador/listaCursos";
			String requestURI= "curso/administrador/listaCursos.do";
			result = createListModelAndView(cursos,requestURI,uri);
			
			return result;
		}
		
		@RequestMapping("/eleccion")
		public ModelAndView eleccion() {
			ModelAndView result;
		
			Collection<Curso> cursos = cursoService.findAll();
			result = new ModelAndView("curso/administrador/eleccion");
			result.addObject("cursos",cursos);
			
			return result;
		}
		
		@RequestMapping("/listaMisCursos")
		public ModelAndView listaMisCursos() {
			ModelAndView result;
		
			Boolean other=true;
			Administrador administrador= administradorService.findByPrincipal();
			Collection<Curso> cursos = cursoService.findCursoByAdministradorId(administrador.getId());
			String uri= "curso/administrador/listaMisCursos";
			String requestURI= "curso/administrador/listaMisCursos.do";
			result = createListModelAndView(cursos,requestURI,uri);
			
			return result;
		}
		
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			
			Curso curso = cursoService.create();		
			
			
			result = createEditModelAndView(curso);
			result.addObject("create", true);
			
			return result;
		}
		
		@RequestMapping(value = "/detalles", method = RequestMethod.GET)
		public ModelAndView details(@RequestParam int cursoId) {		
			ModelAndView result;
		
			Curso curso = cursoService.findOne(cursoId);
			Collection<Pregunta> preguntas= preguntaService.findPreguntasCurso(curso.getId());
			Boolean admin= false;
			if(administradorService.findByPrincipal().equals(curso.getAdministrador())){
				admin=true;
			}
			
							
			result = new ModelAndView("curso/administrador/detalles");
			result.addObject("curso", curso);
			result.addObject("npreguntas",curso.getNumeroPreguntas());
			result.addObject("preguntas",preguntas);
			String requestURI= "curso/administrador/detalles.do";
			result.addObject("requestURI",requestURI);
			result.addObject("admin", admin);
			
			

			
			return result;
		}
		
		
		// Edition -------------------------------------------------------------------
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit( @RequestParam int cursoId) throws IOException {		
			ModelAndView result;
			Curso curso = cursoService.findOne(cursoId);
			
			result = createEditModelAndView(curso);
			result.addObject("create", false);			
			
			return result;
		}
				 
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Curso curso, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = createEditModelAndView(curso);
			} else {
				try {
					cursoService.save(curso);				
					result = new ModelAndView("redirect:listaCursos.do");
				} catch (Throwable oops) {
						result = createEditModelAndView(curso, "curso.commit.error");	
					}
				if(cursoService.findOne(curso.getId())==null){
					result.addObject("create", true);
				}else{
				result.addObject("create", false);
			
				}
			}
			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(@Valid Curso curso, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = createEditModelAndView(curso);
			} else {
				try {
					cursoService.delete(curso);				
					result = new ModelAndView("redirect:listaCursos.do");
				} catch (Throwable oops) {
						result = createEditModelAndView(curso, "curso.commit.error");	
					}
				
				result.addObject("create", false);
			}

			return result;
		}
		
		
		//Other bussiness method
		protected ModelAndView createEditModelAndView(Curso curso) {
			assert curso != null;
			
			ModelAndView result;

			result = createEditModelAndView(curso, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Curso curso, String message) {
			assert curso != null;
			
			ModelAndView result;				
			
			result = new ModelAndView("curso/administrador/edit");
			result.addObject("curso", curso);
			result.addObject("message", message);
			
			
			return result;
		}
		
		//Other bussiness method
				protected ModelAndView createCreateModelAndView(Curso curso) {
					assert curso != null;
					
					ModelAndView result;

					result = createCreateModelAndView(curso, null);
					
					return result;
				}
				
				protected ModelAndView createCreateModelAndView(Curso curso, String message) {
					assert curso != null;
					
					ModelAndView result;				
					
					result = new ModelAndView("curso/administrador/añadir");
					result.addObject("curso", curso);
					result.addObject("message", message);
					
					
					return result;
				}
		
		
		protected ModelAndView createListModelAndView(Collection<Curso> cursos, String requestURI, String uri) {
			ModelAndView result;				
			
			
			result = new ModelAndView(uri);
			result.addObject("cursos", cursos);
			result.addObject("requestURI", requestURI);
			
			return result;
		}
		
	}

