package br.com.delogic.showcase.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.delogic.csa.util.is;
import br.com.delogic.showcase.entity.Account;
import br.com.delogic.showcase.repository.AccountRepository;

public class AccountConverter implements Converter<String, Account> {

	@Override
	public Account convert(String source) {
		if (is.notEmpty(source)) {
			return AccountRepository.get().find(Long.valueOf(source));
		}
		return new Account();
	}

}
