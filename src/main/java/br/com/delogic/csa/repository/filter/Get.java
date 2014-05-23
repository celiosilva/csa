package br.com.delogic.csa.repository.filter;

import java.util.List;

public interface Get<E> {

    List<E> list();

    E first();

    List<E> first(int rows);

    long count();

    boolean exists();

}
