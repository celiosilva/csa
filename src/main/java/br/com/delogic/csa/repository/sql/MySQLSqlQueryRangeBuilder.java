package br.com.delogic.csa.repository.sql;

import br.com.delogic.csa.repository.Criteria;
import br.com.delogic.jfunk.Has;

/**
 * Will add the start row and end end row filters to the query for MySQL
 * database.
 *
 * @author celio@delogic.com.br
 *
 */
public class MySQLSqlQueryRangeBuilder implements SqlQueryRangeBuilder {

    public String buildRangeQuery(String query, Criteria criteria) {

        if (Has.content(criteria.getOffset(), criteria.getLimit())) {

            long offset = criteria.getOffset() != null ? criteria.getOffset() : 0;
            long limit = criteria.getLimit() != null ? criteria.getLimit() : Long.MAX_VALUE;

            query += (" LIMIT " + limit + " OFFSET " + offset);

        }

        return query;
    }

}
