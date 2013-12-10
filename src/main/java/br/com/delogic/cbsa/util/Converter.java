package br.com.delogic.cbsa.util;

public interface Converter<IN, OUT> {

    OUT convert(IN in);

}