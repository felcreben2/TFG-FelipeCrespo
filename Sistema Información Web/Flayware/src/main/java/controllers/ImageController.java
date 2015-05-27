package controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RespuestaService;

import domain.Respuesta;

@Controller
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private RespuestaService respuestaService;	

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletResponse response, @RequestParam int respuestaId) throws IOException {		
		Respuesta respuesta = respuestaService.findOne(respuestaId);
	
		response.setContentType("image/jpeg");
		byte[] buffer = respuesta.getImage();
		InputStream in1 = new ByteArrayInputStream(buffer);
		IOUtils.copy(in1, response.getOutputStream());

		return null;
	}
}
