package br.com.delogic.showcase.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.delogic.cbsa.util.f;
import br.com.delogic.showcase.entity.Account;
import br.com.delogic.showcase.repository.AccountRepository;

public class AccountConverter implements Converter<String, Account> {

	@Override
	public Account convert(String source) {
		if (f.hasValue(source)) {
			return AccountRepository.get().find(Long.valueOf(source));
		}
		return new Account();
	}

}
