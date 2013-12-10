package br.com.delogic.cbsa.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.delogic.cbsa.util.f;

public class LongFormatter implements Formatter<Long> {

	@Override
	public String print(Long object, Locale locale) {
		return object == null ? "" : object.toString();
	}

	@Override
	public Long parse(String text, Locale locale) throws ParseException {
		return f.hasValue(text) && f.isNumber(text) ? Long.valueOf(text) : null;
	}

}
