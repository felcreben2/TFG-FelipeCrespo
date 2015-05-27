package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RespuestaRepository;
import domain.Respuesta;

@Component
@Transactional
public class StringToRespuestaConverter implements Converter<String, Respuesta>{
	
	@Autowired
	RespuestaRepository respuestaRepository;

	@Override
	public Respuesta convert(String text) {
		Respuesta result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = respuestaRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
