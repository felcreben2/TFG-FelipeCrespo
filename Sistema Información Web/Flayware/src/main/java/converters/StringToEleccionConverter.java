package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.EleccionRepository;
import domain.Eleccion;

@Component
@Transactional
public class StringToEleccionConverter implements Converter<String, Eleccion>{
	
	@Autowired
	EleccionRepository eleccionRepository;

	@Override
	public Eleccion convert(String text) {
		Eleccion result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = eleccionRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
