package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ResultadoParticipantes;

@Component
@Transactional
public class ResultadoParticipantesToStringConverter implements Converter<ResultadoParticipantes, String>{
	
	@Override
	public String convert(ResultadoParticipantes resultadoParticipantes) {
		String result;

		if (resultadoParticipantes == null)
			result = null;
		else
			result = String.valueOf(resultadoParticipantes.getId());

		return result;
	}

}