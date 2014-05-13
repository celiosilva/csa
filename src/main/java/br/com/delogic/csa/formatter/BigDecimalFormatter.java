package br.com.delogic.csa.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.com.delogic.csa.util.is;

public class BigDecimalFormatter implements Formatter<BigDecimal> {

    @Override
    public String print(BigDecimal object, Locale locale) {
        return object == null ? "" : object.toString();
    }

    @Override
    public BigDecimal parse(String text, Locale locale) throws ParseException {
        if (is.empty(text)) {
            return null;
        }

        // TODO alterar para utilizar o locale e i18n
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols
            .getInstance(locale));

        try {
            return new BigDecimal(decimalFormat.parse(text).doubleValue());
        } catch (Exception e) {
            // TODO melhorar tratamento de erros aqui
            System.out.println(e);
            return null;
        }
    }

}
