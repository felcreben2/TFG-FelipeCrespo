package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Partida;

@Component
@Transactional
public class PartidaToStringConverter implements Converter<Partida, String>{
	
	@Override
	public String convert(Partida partida) {
		String result;

		if (partida == null)
			result = null;
		else
			result = String.valueOf(partida.getId());

		return result;
	}

}