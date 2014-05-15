package br.com.delogic.csa.repository.sql;

import br.com.delogic.csa.repository.Criteria;


/**
 * Will return the query with start row and end row for Oracle database.
 *
 * @author celio@delogic.com.br
 *
 */
public class OracleQueryRangeBuilder implements QueryRangeBuilder {

    private static final String RANGE_QUERY =
                                                "SELECT * " +
                                                    "FROM  (" +
                                                    "SELECT /*+ FIRST_ROWS */ a.*, ROWNUM rnum  " +
                                                    "FROM  ( %s ) a " +
                                                    "WHERE ROWNUM <= %s)  " +
                                                    "WHERE rnum > %s";

    public String buildRangeQuery(String query, Criteria configuration) {
        long startRow = configuration.getStartRow() != null ? configuration.getStartRow() : 0;
        long endRow = configuration.getEndRow() != null ? configuration.getEndRow() : Long.MAX_VALUE;
        String newQuery = String.format(RANGE_QUERY, query, endRow, startRow);
        return newQuery;
    }

}
