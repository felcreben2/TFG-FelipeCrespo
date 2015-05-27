package controllers.administrador;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.JugadorService;
import services.PreguntaService;
import controllers.AbstractController;
import domain.Jugador;
import domain.Respuesta;

@Controller
@RequestMapping("/jugador/administrador")
public class JugadorController extends AbstractController{
	
	// Services ----------------------------------------------------------------
		
		@Autowired
		private JugadorService jugadorService;		
		
		@Autowired
		private PreguntaService preguntaService;		
	
	
		// Constructor ---------------------------------------------------------------
		public JugadorController() {
			super();
		}
		
		// Listing -------------------------------------------------------------------
		
		@RequestMapping("/listaJugadores")
		public ModelAndView listaJugadores() {
			ModelAndView result;
		
			Collection<Jugador> jugadores = jugadorService.findAll();
			String uri= "jugador/administrador/listaJugadores";
			String requestURI= "jugador/administrador/listaJugadores.do";
			result = createListModelAndView(jugadores,requestURI,uri);
			
			return result;
		}
		
		@RequestMapping("/listaBanneados")
		public ModelAndView listaBaneados() {
			ModelAndView result;
		
			Collection<Jugador> jugadores = jugadorService.findBannerJugadores();
			String uri= "jugador/administrador/listaBanneados";
			String requestURI= "jugador/administrador/listaBanneados.do";
			result = createListModelAndView(jugadores,requestURI,uri);
			
			return result;
		}
		
		@RequestMapping("/bloquear")
		public ModelAndView bloquear(@RequestParam int jugadorId) {
			
			jugadorService.disable(jugadorId);
			
			ModelAndView result = new ModelAndView("redirect:listaBanneados.do");
			
			return result;
		}
		
		@RequestMapping("/desbloquear")
		public ModelAndView desbloquear(@RequestParam int jugadorId) {
			
			jugadorService.enable(jugadorId);
			
			ModelAndView result = new ModelAndView("redirect:listaJugadores.do");
			
			return result;
		}
		
		@RequestMapping(value = "/detalles", method = RequestMethod.GET)
		public ModelAndView details(@RequestParam int jugadorId) {
			ModelAndView result;
			Jugador jugador= jugadorService.findOne(jugadorId);

			result = createEditModelAndView(jugador);
			result.addObject("details","true");
			result.addObject("isEnable",jugador.getUserAccount().isEnabled());
			
			return result;
		}
		
		
		//Other bussiness method
		protected ModelAndView createListModelAndView(Collection<Jugador> jugadores, String requestURI, String uri) {
			ModelAndView result;				
			
			
			result = new ModelAndView(uri);
			result.addObject("jugadores", jugadores);
			result.addObject("requestURI", requestURI);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Jugador jugador){
			assert jugador != null;
			
			ModelAndView result;

			result = createEditModelAndView(jugador, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Jugador jugador, String message) {
			assert jugador != null;
			
			ModelAndView result;				
			
			result = new ModelAndView("jugador/administrador/detalles");
			result.addObject("jugador", jugador);
			result.addObject("message", message);
			
			
			return result;
		}
		
	}

