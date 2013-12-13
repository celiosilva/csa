package br.com.delogic.csa.manager.util;

import java.util.Iterator;

public class TimeIterator implements Iterator<Long> {

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public synchronized Long next() {
        try {
            Thread.sleep(1);
            return System.currentTimeMillis();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove() {
        System.out.println("not implemented");
    }

}
