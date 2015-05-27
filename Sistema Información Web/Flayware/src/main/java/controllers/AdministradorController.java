/* AdministradorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministradorService;
import domain.Administrador;
import domain.Pregunta;
import forms.AdministradorForm;

@Controller
@RequestMapping("/administrador")
public class AdministradorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	
	public AdministradorController() {
		super();
	}
	

	@Autowired
	private AdministradorService administradorService;
	
	
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/listaAdministradores")
	public ModelAndView listaAdmin() {
		ModelAndView result;
	
		Collection<Administrador> administradores = administradorService.findAll();
		String uri= "administrador/listaAdministradores";
		String requestURI= "administrador/listaAdministradores.do";
		result = createListModelAndView(administradores,requestURI,uri);
		
		return result;
	}
	
	@RequestMapping(value = "/registrarAdministrador", method = RequestMethod.GET)
	public ModelAndView registerA() {
		ModelAndView result;
		
		AdministradorForm administradorForm= administradorService.constructNew();
	
		result = createEditModelAndViewAdministrador(administradorForm);
		
		return result;
	}
	
	
	// Edition -------------------------------------------------------------------

	@RequestMapping(value = "/saveAdministrador", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAdministrador(@Valid AdministradorForm administradorForm, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndViewAdministrador(administradorForm);
		} else {
			try {
				Administrador administrador = this.administradorService.reconstruct(administradorForm);
				administradorService.save(administrador);				
				
				result = new ModelAndView("redirect:listaAdministradores.do");
				
			} catch (Throwable oops) {
				if (oops.getMessage() == "Passwords are different") {
					result = createEditModelAndViewAdministrador(administradorForm, "register.password.error");
				}else if (oops.getMessage() == "TOS is False"){
					result = createEditModelAndViewAdministrador(administradorForm, "register.tos.error");
				}else if (oops instanceof DataIntegrityViolationException) {
					result = createEditModelAndViewAdministrador(administradorForm, "register.duplicated.username");
				} else {
					result = createEditModelAndViewAdministrador(administradorForm, "register.commit.error");	
				}
			}
		}

		return result;
	}
	
		
	protected ModelAndView createEditModelAndViewAdministrador(AdministradorForm administradorForm) {
		ModelAndView result;

		result = createEditModelAndViewAdministrador(administradorForm, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndViewAdministrador(AdministradorForm administradorForm, String message) {
		ModelAndView result;				

		result = new ModelAndView("administrador/registrarAdministrador");
		result.addObject("message", message);
		result.addObject("administradorForm", administradorForm);
		
		return result;
	}
	
	protected ModelAndView createListModelAndView(Collection<Administrador> administradores, String requestURI, String uri) {
		ModelAndView result;				
		
		
		result = new ModelAndView(uri);
		result.addObject("administradores", administradores);
		result.addObject("requestURI", requestURI);
		
		return result;
	}
	
}
	
