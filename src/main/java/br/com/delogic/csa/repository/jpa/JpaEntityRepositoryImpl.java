package br.com.delogic.csa.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.delogic.csa.repository.EntityRepository;
import br.com.delogic.csa.repository.filter.With;

public class JpaEntityRepositoryImpl implements EntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Object entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

    @Override
    public void create(Collection<? extends Object> entity) {
        for (Object o : entity) {
            entityManager.persist(o);
        }
        entityManager.flush();
    }

    @Override
    public void delete(Object entity) {
        // se for uma Entidade base com código comum então pegamos o ID e
        // refazemos a consulta para poder remover
        entityManager.remove(entity);
        // apos o delete é executado devemos mandar o comando para o banco para
        // ter certeza que próximas ações utilizarão resultados corretos
        entityManager.flush();
    }

    @Override
    public void delete(Collection<? extends Object> entidade) {
        for (Object o : entidade) {
            entityManager.remove(o);
        }
        entityManager.flush();
    }

    @Override
    public <E> E read(Object id, Class<E> clazz) {
        E entidade = entityManager.find(clazz, id);
        return entidade;
    }

    @Override
    public void update(Collection<? extends Object> entidade) {
        for (Object o : entidade) {
            update(o);
        }
    }

    @Override
    public void update(Object entidade) {
        getEntityManager().merge(entidade);
        // após o update é necessário enviar os comandos para o banco
        // para ter certeza que os dados utilizados estarão corretos
        // para próximas ações como consultas por exemplos
        getEntityManager().flush();
        // em casos de relacionamentos com cascade all, após os objetos
        // serem inseridos a PK não é populada, então este refresh
        // irá repopular todos os objetos renovando os dados e pks/fks/etc
        refresh(entidade);
    }

    private void refresh(Object entidade) {
        entityManager.refresh(entidade);
    }

    @Override
    public <E> With<E> find(Class<E> type) {
        JpaFromImpl from = new JpaFromImpl(getEntityManager(), JpaSqlCommand.SELECT);
        return from.from(type);
    }

    /**
     * Gets the entity manager
     *
     * @return
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Sets the entity manager to be used with this query
     *
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
