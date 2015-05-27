package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ResultadoParticipantesRepository;
import domain.ResultadoParticipantes;

@Component
@Transactional
public class StringToResultadoParticipantesConverter implements Converter<String, ResultadoParticipantes>{
	
	@Autowired
	ResultadoParticipantesRepository resultadoParticipantesRepository;

	@Override
	public ResultadoParticipantes convert(String text) {
		ResultadoParticipantes result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = resultadoParticipantesRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
