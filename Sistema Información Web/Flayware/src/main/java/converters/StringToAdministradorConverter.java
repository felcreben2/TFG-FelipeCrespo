package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AdministradorRepository;
import domain.Administrador;

@Component
@Transactional
public class StringToAdministradorConverter implements Converter<String, Administrador>{
	
	@Autowired
	AdministradorRepository administradorRepository;

	@Override
	public Administrador convert(String text) {
		Administrador result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = administradorRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
