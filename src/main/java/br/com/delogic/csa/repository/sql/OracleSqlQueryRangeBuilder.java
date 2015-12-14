package br.com.delogic.csa.repository.sql;

import br.com.delogic.csa.repository.Criteria;
import br.com.delogic.jfunk.Has;

/**
 * Will return the query with start row and end row for Oracle database.
 *
 * @author celio@delogic.com.br
 *
 */
public class OracleSqlQueryRangeBuilder implements SqlQueryRangeBuilder {

    private static final String RANGE_QUERY =
                                                "SELECT * " +
                                                    "FROM  (" +
                                                    "SELECT /*+ FIRST_ROWS */ a.*, ROWNUM rnum  " +
                                                    "FROM  ( %s ) a " +
                                                    "WHERE ROWNUM <= %s)  " +
                                                    "WHERE rnum > %s";

    public String buildRangeQuery(String query, Criteria criteria) {

        if (Has.content(criteria.getOffset()) || Has.content(criteria.getLimit())) {
            long offset = criteria.getOffset() != null ? criteria.getOffset() : 0;
            long endRow = criteria.getLimit() != null ? offset + criteria.getLimit() : Long.MAX_VALUE;
            query = String.format(RANGE_QUERY, query, endRow, offset);
        }

        return query;
    }

}
