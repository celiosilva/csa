package br.com.delogic.csa.repository;

import java.util.List;

import br.com.delogic.jfunk.data.Result;

public interface QueryRepository<E> {

    List<E> getList();

    List<E> getList(Criteria criteria);

    RepositoryData<E> getData();

    RepositoryData<E> getData(Criteria criteria);

    long count();

    long count(Criteria criteria);

    /**
     * Returns the first value found, wrapped in a result object.
     *
     * @return Never null Result object with a possible valid result value
     *         inside.
     */
    Result<E> getFirst();

    /**
     * Returns the first value found, wrapped in a result object based on the
     * criteria.
     *
     * @param criteria
     * @return Never null Result object with a possible valid result value
     *         inside.
     */
    Result<E> getFirst(Criteria criteria);

}
