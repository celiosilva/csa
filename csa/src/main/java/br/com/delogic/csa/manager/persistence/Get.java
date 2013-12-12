package br.com.delogic.csa.manager.persistence;

import java.util.List;

public interface Get<E> {

    List<E> list();

    E first();

    List<E> first(int rows);

    long count();

    boolean exists();

}
