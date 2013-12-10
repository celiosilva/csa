package br.com.delogic.cbsa.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.delogic.cbsa.util.f;

public class IntegerFormatter implements Formatter<Integer> {

	@Override
	public String print(Integer object, Locale locale) {
		return object == null ? "" : object.toString();
	}

	@Override
	public Integer parse(String text, Locale locale) throws ParseException {
		return f.hasValue(text) && f.isNumber(text) ? Integer.valueOf(text)
				: null;
	}

}
