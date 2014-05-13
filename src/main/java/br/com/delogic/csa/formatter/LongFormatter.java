package br.com.delogic.csa.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.delogic.csa.util.is;

public class LongFormatter implements Formatter<Long> {

	@Override
	public String print(Long object, Locale locale) {
		return object == null ? "" : object.toString();
	}

	@Override
	public Long parse(String text, Locale locale) throws ParseException {
		return is.notEmpty(text) && is.number(text) ? Long.valueOf(text) : null;
	}

}
