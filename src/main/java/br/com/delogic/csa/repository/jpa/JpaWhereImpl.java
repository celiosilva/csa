package br.com.delogic.csa.repository.jpa;

import javax.persistence.EntityManager;

import br.com.delogic.csa.repository.filter.Filter;
import br.com.delogic.csa.repository.filter.Get;
import br.com.delogic.csa.repository.filter.Then;
import br.com.delogic.csa.repository.filter.With;

public class JpaWhereImpl<E> implements With<E> {

    private final JpaWhereRecorder whereRecorder = new JpaWhereRecorder();
    private JpaOrderRecorder       orderRecorder;
    private final Class<E>         clazz;
    private final EntityManager    entityManager;
    private final JpaSqlCommand    command;

    public JpaWhereImpl(Class<E> clazz, EntityManager entityManager, JpaSqlCommand command) {
        this.clazz = clazz;
        this.entityManager = entityManager;
        this.command = command;
    }

    /**
     * Delegates to andAttribute method
     */
    @Override
    public Filter<E> atr() {
        return andAttribute();
    }

    @Override
    public Filter<E> andAttribute() {
        JpaWhereFilterRecorder stmt = new JpaWhereFilterRecorder("e");
        stmt.setOperator("and");
        E e = EntityProxyGenerator.getFilterEntityProxyClass(clazz, stmt);
        JpaFilterImpl<E> filter = new JpaFilterImpl<E>(e, stmt);
        getWhereRecorder().recordStatement(stmt);
        return filter;
    }

    @Override
    public Filter<E> orAttribute() {
        JpaWhereFilterRecorder stmt = new JpaWhereFilterRecorder("e");
        stmt.setOperator("or");
        E e = EntityProxyGenerator.getFilterEntityProxyClass(clazz, stmt);
        JpaFilterImpl<E> filter = new JpaFilterImpl<E>(e, stmt);
        getWhereRecorder().recordStatement(stmt);
        return filter;
    }

    @Override
    public Get<E> get() {
        JpaGetImpl<E> get = new JpaGetImpl<E>(entityManager, this, clazz);
        return get;
    }

    @Override
    public E orderByAsc() {
        if (orderRecorder == null) {
            orderRecorder = new JpaOrderRecorder("e");
        }
        orderRecorder.setOrderType("asc");
        E e = EntityProxyGenerator.getOrderEntityProxyClass(clazz, orderRecorder);
        return e;
    }

    @Override
    public E orderByDesc() {
        if (orderRecorder == null) {
            orderRecorder = new JpaOrderRecorder("e");
        }
        orderRecorder.setOrderType("desc");
        E e = EntityProxyGenerator.getOrderEntityProxyClass(clazz, orderRecorder);
        return e;
    }

    JpaWhereRecorder getStatementRecorder() {
        return getWhereRecorder();
    }

    public JpaWhereRecorder getWhereRecorder() {
        return whereRecorder;
    }

    public JpaOrderRecorder getOrderRecorder() {
        return orderRecorder;
    }

    public JpaSqlCommand getCommand() {
        return command;
    }

    @Override
    public Then then() {
        return new JpaThenImpl<E>(entityManager, this, clazz);
    }

}
