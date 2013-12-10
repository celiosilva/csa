package br.com.delogic.showcase.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.delogic.cbsa.util.f;
import br.com.delogic.showcase.entity.User;
import br.com.delogic.showcase.repository.UserRepository;

public class UserConverter implements Converter<String, User> {

    @Override
    public User convert(String id) {
        return !f.hasValue(id) ? null : new UserRepository().find(Long
            .valueOf(id));
    }

}
