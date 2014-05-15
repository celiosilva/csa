package br.com.delogic.csa.repository;

import java.util.List;

public class RepositoryData<E> {

    private final Long    size;
    private final List<E> data;

    public RepositoryData(Long size, List<E> data) {
        this.size = size;
        this.data = data;
    }

    public Long getSize() {
        return size;
    }

    public List<E> getData() {
        return data;
    }

}
