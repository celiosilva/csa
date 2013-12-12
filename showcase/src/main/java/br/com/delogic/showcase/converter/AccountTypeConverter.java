package br.com.delogic.showcase.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.delogic.csa.util.is;
import br.com.delogic.showcase.entity.AccountType;
import br.com.delogic.showcase.repository.AccountTypeRepository;

public class AccountTypeConverter implements Converter<String, AccountType> {

    @Override
    public AccountType convert(String source) {
        if (is.notEmpty(source)) {
            return AccountTypeRepository.get().find(Long.valueOf(source));
        }
        return null;
    }

}
