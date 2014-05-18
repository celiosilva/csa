package br.com.delogic.csa.repository;

import java.util.List;

public class RepositoryData<E> {

    private final long              size;
    private final List<? extends E> data;

    public RepositoryData(long size, List<? extends E> data) {
        this.size = size;
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public List<? extends E> getData() {
        return data;
    }

}
