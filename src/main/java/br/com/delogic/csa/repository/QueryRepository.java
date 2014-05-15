package br.com.delogic.csa.repository;

import java.util.List;

public interface QueryRepository<E> {

    List<E> getList();

    List<E> getList(Criteria criteria);

    RepositoryData<E> getData();

    RepositoryData<E> getData(Criteria criteria);

    long count();

    long count(Criteria criteria);

}
