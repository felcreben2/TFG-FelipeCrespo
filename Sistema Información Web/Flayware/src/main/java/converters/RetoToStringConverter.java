package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Reto;

@Component
@Transactional
public class RetoToStringConverter implements Converter<Reto, String>{
	
	@Override
	public String convert(Reto reto) {
		String result;

		if (reto == null)
			result = null;
		else
			result = String.valueOf(reto.getId());

		return result;
	}

}