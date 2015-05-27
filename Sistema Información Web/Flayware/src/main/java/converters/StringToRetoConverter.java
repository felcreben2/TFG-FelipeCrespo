package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RetoRepository;
import domain.Reto;

@Component
@Transactional
public class StringToRetoConverter implements Converter<String, Reto>{
	
	@Autowired
	RetoRepository retoRepository;

	@Override
	public Reto convert(String text) {
		Reto result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = retoRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
