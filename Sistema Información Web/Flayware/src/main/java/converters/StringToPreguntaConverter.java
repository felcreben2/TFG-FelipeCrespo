package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PreguntaRepository;
import domain.Pregunta;

@Component
@Transactional
public class StringToPreguntaConverter implements Converter<String, Pregunta>{
	
	@Autowired
	PreguntaRepository preguntaRepository;

	@Override
	public Pregunta convert(String text) {
		Pregunta result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = preguntaRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
