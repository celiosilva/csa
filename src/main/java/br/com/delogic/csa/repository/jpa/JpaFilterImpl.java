package br.com.delogic.csa.repository.jpa;

import br.com.delogic.csa.repository.filter.Filter;

public class JpaFilterImpl<E> implements Filter<E> {

    private final E                      proxy;
    private final JpaWhereFilterRecorder filter;
    private boolean                      called = false;

    public JpaFilterImpl(E proxy, JpaWhereFilterRecorder filter) {
        this.proxy = proxy;
        this.filter = filter;
    }

    private void checkDoubleCall() {
        if (called) {
            throw new IllegalStateException("Um filtro nao pode ser chamado duas vezes. E necessario partir do Where novamente");
        } else {
            called = true;
        }
    }

    @Override
    public E equals() {
        checkDoubleCall();
        filter.setCondition("=");
        return proxy;
    }

    @Override
    public E notEquals() {
        checkDoubleCall();
        filter.setCondition("<>");
        return proxy;
    }

    @Override
    public E like() {
        checkDoubleCall();
        filter.setCondition("like");
        return proxy;
    }

    @Override
    public E notLike() {
        checkDoubleCall();
        filter.setCondition("not like");
        return proxy;
    }

    @Override
    public E greaterThan() {
        checkDoubleCall();
        filter.setCondition(">");
        return proxy;
    }

    @Override
    public E greaterEqualsThan() {
        checkDoubleCall();
        filter.setCondition(">=");
        return proxy;
    }

    @Override
    public E lessThan() {
        checkDoubleCall();
        filter.setCondition("<");
        return proxy;
    }

    @Override
    public E lessEqualsThan() {
        checkDoubleCall();
        filter.setCondition("<=");
        return proxy;
    }

    @Override
    public E likeIgnoreCase() {
        checkDoubleCall();
        filter.setCondition("like");
        filter.setUseUpperCase(true);
        return proxy;
    }

    @Override
    public E notNull() {
        checkDoubleCall();
        filter.setCondition("is not null");
        return proxy;
    }

    @Override
    public E isNull() {
        checkDoubleCall();
        filter.setCondition("is null");
        return proxy;
    }

}
