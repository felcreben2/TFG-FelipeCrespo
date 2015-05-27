package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Pregunta;

@Component
@Transactional
public class PreguntaToStringConverter implements Converter<Pregunta, String>{
	
	@Override
	public String convert(Pregunta pregunta) {
		String result;

		if (pregunta == null)
			result = null;
		else
			result = String.valueOf(pregunta.getId());

		return result;
	}

}