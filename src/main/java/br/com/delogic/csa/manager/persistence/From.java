package br.com.delogic.csa.manager.persistence;

public interface From {

    <E> With<E> from(Class<E> clazz);

}
