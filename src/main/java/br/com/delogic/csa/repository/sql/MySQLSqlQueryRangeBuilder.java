package br.com.delogic.csa.repository.sql;

import br.com.delogic.csa.repository.Criteria;


/**
 * Will add the start row and end end row filters to the query for MySQL
 * database.
 *
 * @author celio@delogic.com.br
 *
 */
public class MySQLSqlQueryRangeBuilder implements SqlQueryRangeBuilder {

    public String buildRangeQuery(String query, Criteria configuration) {
        long startRow = configuration.getStartRow() != null ? configuration.getStartRow() : 0;
        long endRow = configuration.getEndRow() != null ? configuration.getEndRow() : Long.MAX_VALUE;

        query += (" LIMIT " + startRow + ", " + endRow);
        return query;
    }

}
