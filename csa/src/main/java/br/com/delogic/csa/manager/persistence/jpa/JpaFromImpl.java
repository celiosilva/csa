package br.com.delogic.csa.manager.persistence.jpa;

import javax.persistence.EntityManager;

import br.com.delogic.csa.manager.persistence.From;
import br.com.delogic.csa.manager.persistence.With;

public class JpaFromImpl implements From {

    private final EntityManager entityManager;
    private final JpaSqlCommand    command;

    public JpaFromImpl(EntityManager em, JpaSqlCommand command) {
        this.entityManager = em;
        this.command = command;
    }

    @Override
    public <E> With<E> from(Class<E> clazz) {
        JpaWhereImpl<E> where = new JpaWhereImpl<E>(clazz, entityManager, command);
        return where;
    }

}
