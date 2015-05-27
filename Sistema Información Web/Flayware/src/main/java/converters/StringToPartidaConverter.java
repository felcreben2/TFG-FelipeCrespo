package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PartidaRepository;
import domain.Partida;

@Component
@Transactional
public class StringToPartidaConverter implements Converter<String, Partida>{
	
	@Autowired
	PartidaRepository partidaRepository;

	@Override
	public Partida convert(String text) {
		Partida result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = partidaRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
