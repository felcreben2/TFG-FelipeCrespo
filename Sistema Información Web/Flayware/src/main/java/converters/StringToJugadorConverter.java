package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.JugadorRepository;
import domain.Jugador;

@Component
@Transactional
public class StringToJugadorConverter implements Converter<String, Jugador>{
	
	@Autowired
	JugadorRepository jugadorRepository;

	@Override
	public Jugador convert(String text) {
		Jugador result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = jugadorRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
