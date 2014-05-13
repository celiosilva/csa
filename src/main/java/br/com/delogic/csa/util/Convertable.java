package br.com.delogic.csa.util;

public interface Convertable<IN, OUT> {

    OUT convert(IN in);

}