package br.com.delogic.csa.repository.filter;

public interface From {

    <E> With<E> from(Class<E> clazz);

}
