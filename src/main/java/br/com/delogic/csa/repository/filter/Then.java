package br.com.delogic.csa.repository.filter;

/**
 * Performs modifications into the repository once a filter or no filter is
 * created.
 *
 * @author celio@delogic.com.br
 *
 */
public interface Then {

    /**
     * Will delete all entities found in the statement. Will work only if the
     * With statement has at least one filter. If you are sure to delete all
     * entities you must call {@code deleteAll} method.
     *
     * A command to delete will be issued directly into the repository.
     *
     * @return The number of entities deleted.
     */
    int deleteFound();

    /**
     * Will delete all entities on repository. Will work only if the With
     * statement has no filter. If you want to delete based on a filter you must
     * call {@code deleteFound} method instead.
     *
     * A command to delete will be issued directly into the repository.
     *
     * @return The number of entities deleted.
     */
    int deleteAll();

}
