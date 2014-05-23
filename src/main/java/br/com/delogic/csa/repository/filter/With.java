package br.com.delogic.csa.repository.filter;

public interface With<E> {

    Filter<E> atr();

    Filter<E> andAttribute();

    Filter<E> orAttribute();

    Get<E> get();

    Then then();

    E orderByAsc();

    E orderByDesc();

}
