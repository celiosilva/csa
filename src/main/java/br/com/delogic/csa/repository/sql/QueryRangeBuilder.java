package br.com.delogic.csa.repository.sql;

/**
 * Implement this interface and pass it to the query so you can query the
 * database with pagination using the start row and end row.
 *
 * @author celio@delogic.com.br
 *
 */
public interface QueryRangeBuilder {

    /**
     * Will receive the query composed, ready for execution and the parameters
     * sent. It must return a query which returns exactly the same data but
     * filtered with start row and end row.
     *
     * @param query
     *            - String query ready to be sent to the database without start
     *            row and end row filters.
     * @param queryParameters
     *            - Parameters containg the start row and end row object.
     * @return String query with start row and end row filter specific to the
     *         database queried
     */
    String buildRangeQuery(String query, Criteria queryParameters);

}
