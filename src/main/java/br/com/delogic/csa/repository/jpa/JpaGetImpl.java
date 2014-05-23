package br.com.delogic.csa.repository.jpa;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delogic.csa.repository.filter.Get;

public class JpaGetImpl<E> implements Get<E> {

    private final EntityManager   entityManager;
    private final JpaWhereImpl<E> where;
    private final Class<E>        clazz;

    private static final Logger   logger = LoggerFactory.getLogger(JpaGetImpl.class);

    public JpaGetImpl(EntityManager em, JpaWhereImpl<E> where, Class<E> clazz) {
        this.entityManager = em;
        this.where = where;
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> list() {
        String jpqlQuery = getJpqlQuery();
        logger.debug("Prepared query:" + jpqlQuery);
        Query query = entityManager.createQuery(jpqlQuery, clazz);

        addParameters(query, where.getStatementRecorder().getParameters());

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E first() {
        String jpqlQuery = getJpqlQuery();
        logger.debug("Prepared query:" + jpqlQuery);
        Query query = entityManager.createQuery(jpqlQuery, clazz);

        addParameters(query, where.getStatementRecorder().getParameters());

        query.setFirstResult(0);
        query.setMaxResults(1);
        List<E> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        if (logger.isDebugEnabled()) {
            logger.debug(list.toString());
        }
        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> first(int rows) {
        String jpqlQuery = getJpqlQuery();
        logger.debug("Prepared query:" + jpqlQuery);
        Query query = entityManager.createQuery(jpqlQuery, clazz);

        addParameters(query, where.getStatementRecorder().getParameters());

        query.setFirstResult(0);
        query.setMaxResults(rows);
        List<E> list = query.getResultList();

        if (logger.isDebugEnabled()) {
            logger.debug(list.toString());
        }
        return list;
    }

    void addParameters(Query query, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            for (Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    String getJpqlQuery() {
        String select = "select e from " + clazz.getSimpleName() + " as e ";

        String whereClause = "";
        if (where != null && !where.getWhereRecorder().getStatements().isEmpty()) {
            whereClause = "where " + where.getWhereRecorder().getWhereClause();
        }

        String orderClause = "";
        if (where != null && where.getOrderRecorder() != null) {
            orderClause = "order by " + where.getOrderRecorder().getOrderClause();
        }

        String jpqlQuery = select + whereClause + orderClause;
        return jpqlQuery;
    }

    String getCountJpqlQuery() {
        String selectCount = "select count(e) from " + clazz.getSimpleName() + " as e ";

        String whereClause = "";
        if (where != null && !where.getWhereRecorder().getStatements().isEmpty()) {
            whereClause = "where " + where.getWhereRecorder().getWhereClause();
        }

        String jpqlQuery = selectCount + whereClause;
        return jpqlQuery;
    }

    @Override
    public long count() {
        String jpqlQuery = getCountJpqlQuery();
        logger.debug("Prepared query:" + jpqlQuery);
        Query query = entityManager.createQuery(jpqlQuery, clazz);

        addParameters(query, where.getStatementRecorder().getParameters());

        Number count = (Number) query.getSingleResult();
        return count.longValue();
    }

    @Override
    public boolean exists() {
        return count() > 0;
    }

}
