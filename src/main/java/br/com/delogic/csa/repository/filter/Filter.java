package br.com.delogic.csa.repository.filter;

public interface Filter<E> {

    E equals();

    E notEquals();

    E like();

    E likeIgnoreCase();

    E notLike();

    E greaterThan();

    E greaterEqualsThan();

    E lessThan();

    E lessEqualsThan();

    E notNull();

    E isNull();

}
