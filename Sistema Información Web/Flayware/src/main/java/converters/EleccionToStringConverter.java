package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Eleccion;

@Component
@Transactional
public class EleccionToStringConverter implements Converter<Eleccion, String>{
	
	@Override
	public String convert(Eleccion eleccion) {
		String result;

		if (eleccion == null)
			result = null;
		else
			result = String.valueOf(eleccion.getId());

		return result;
	}

}