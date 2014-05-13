package br.com.delogic.csa.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.delogic.csa.util.is;

public class IntegerFormatter implements Formatter<Integer> {

	@Override
	public String print(Integer object, Locale locale) {
		return object == null ? "" : object.toString();
	}

	@Override
	public Integer parse(String text, Locale locale) throws ParseException {
		return is.notEmpty(text) && is.number(text) ? Integer.valueOf(text)
				: null;
	}

}
