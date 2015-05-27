package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Respuesta;

@Component
@Transactional
public class RespuestaToStringConverter implements Converter<Respuesta, String>{
	
	@Override
	public String convert(Respuesta respuesta) {
		String result;

		if (respuesta == null)
			result = null;
		else
			result = String.valueOf(respuesta.getId());

		return result;
	}

}