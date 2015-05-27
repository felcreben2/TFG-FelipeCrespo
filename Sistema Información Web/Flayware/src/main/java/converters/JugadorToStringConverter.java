package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Jugador;

@Component
@Transactional
public class JugadorToStringConverter implements Converter<Jugador, String>{
	
	@Override
	public String convert(Jugador jugador) {
		String result;

		if (jugador == null)
			result = null;
		else
			result = String.valueOf(jugador.getId());

		return result;
	}

}