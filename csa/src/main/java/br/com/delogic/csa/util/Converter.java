package br.com.delogic.csa.util;

public interface Converter<IN, OUT> {

    OUT convert(IN in);

}