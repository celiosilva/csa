package br.com.delogic.csa.repository.jpa;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delogic.csa.repository.filter.Then;

public class JpaThenImpl<E> implements Then {

    private final EntityManager   entityManager;
    private final JpaWhereImpl<E> where;
    private final Class<E>        clazz;

    private static final Logger   logger = LoggerFactory.getLogger(JpaThenImpl.class);

    public JpaThenImpl(EntityManager em, JpaWhereImpl<E> where, Class<E> clazz) {
        this.entityManager = em;
        this.where = where;
        this.clazz = clazz;
    }

    void addParameters(Query query, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            for (Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    String getJpqlDelete() {
        String delete = "delete from " + clazz.getSimpleName() + " e ";

        String whereClause = "";
        if (where != null && !where.getWhereRecorder().getStatements().isEmpty()) {
            whereClause = "where " + where.getWhereRecorder().getWhereClause();
        }

        String orderClause = "";
        if (where != null && where.getOrderRecorder() != null) {
            orderClause = "order by " + where.getOrderRecorder().getOrderClause();
        }

        String jpqlDelete = delete + whereClause + orderClause;
        return jpqlDelete;
    }

    @Override
    public int deleteFound() {
        if (where == null || where.getWhereRecorder().getStatements().isEmpty()) {
            throw new IllegalStateException(
                "The method deleteFound will work only if there's a filter." +
                    " If you want to delete all you must call deleteAll method");
        }
        String jpqlDelete = getJpqlDelete();
        logger.debug("Prepared delete:" + jpqlDelete);
        Query query = entityManager.createQuery(jpqlDelete, clazz);
        addParameters(query, where.getStatementRecorder().getParameters());
        return query.executeUpdate();
    }

    @Override
    public int deleteAll() {
        if (where != null && !where.getWhereRecorder().getStatements().isEmpty()) {
            throw new IllegalStateException(
                "The method deleteAll will work only if there are no filters." +
                    " If you want to delete entities found you must call deleteFound method");
        }
        String jpqlDelete = getJpqlDelete();
        logger.debug("Prepared delete:" + jpqlDelete);
        Query query = entityManager.createQuery(jpqlDelete, clazz);
        addParameters(query, where.getStatementRecorder().getParameters());
        return query.executeUpdate();
    }

}
