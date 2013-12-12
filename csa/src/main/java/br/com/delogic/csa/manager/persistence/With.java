package br.com.delogic.csa.manager.persistence;

public interface With<E> {

    Filter<E> attrb();

    Filter<E> andAttribute();

    Filter<E> orAttribute();

    Get<E> get();

    Then then();

    E orderByAsc();

    E orderByDesc();

}
