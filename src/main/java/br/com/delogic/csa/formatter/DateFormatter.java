package br.com.delogic.csa.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");

	@Override
	public String print(Date date, Locale locale) {
		return date == null ? "" : dateFormat.format(date);
	}

	@Override
	public Date parse(String date, Locale locale) throws ParseException {
		return date != null && !date.isEmpty() ? dateFormat.parse(date) : null;
	}
}
