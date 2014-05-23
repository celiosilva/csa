package br.com.delogic.csa.repository;

import java.util.Collection;

import br.com.delogic.csa.repository.filter.With;

/**
 * Manages the entity objects in the application. Main implementation relies on
 * JPA and databases but it can also be implemented for XML data or web
 * services. This should not be extended nor used as Repository pattern, but
 * should be used inside the repositories or business services to have more
 * control over the entities. This repository follows the CRUD pattern.
 *
 * @author celio@delogic.com.br
 *
 * @since 13/12/2013
 */
public interface EntityRepository {

    /**
     * Creates a new entity
     *
     * @param entity
     *            to be created on database or any other service
     */
    void create(Object entity);

    /**
     * Creates a collection of entities at once.
     *
     * @param entities
     *            to be created on database or any other service
     */
    void create(Collection<? extends Object> entities);

    /**
     * Reads the entity
     *
     * @param id
     *            EntityId to read for
     * @param clazz
     *            of the entity to read for
     * @return Entity found
     */
    <E> E read(Object id, Class<E> clazz);

    /**
     * Updates the entity
     *
     * @param entity
     *            to be updated on database or any other service
     */
    void update(Object entity);

    /**
     * Updates a collection of entities at once
     *
     * @param entities
     *            to be update on database or any other service
     */
    void update(Collection<? extends Object> entities);

    /**
     * Deletes the entity.
     *
     * @param entity
     *            to be deleted on database or any other service
     */
    void delete(Object entity);

    /**
     * Deletes a collection of entities.
     *
     * @param entities
     *            to be deleted on database or any other service
     */
    void delete(Collection<? extends Object> entities);

    /**
     * Finds entities based on the entity type and query filters applied to the
     * {@code With} interface.
     *
     * @param type
     *            of the entity for finding
     * @return an interface capable of finding, counting, deleting, etc.
     *
     * @see {@link With} Interface for more details on finding.
     */
    <E> With<E> find(Class<E> type);

}