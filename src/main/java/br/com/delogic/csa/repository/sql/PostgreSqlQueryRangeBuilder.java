package br.com.delogic.csa.repository.sql;

import br.com.delogic.csa.repository.Criteria;

public class PostgreSqlQueryRangeBuilder implements SqlQueryRangeBuilder {

    public String buildRangeQuery(String query, Criteria configuration) {

        long startRow = configuration.getStartRow() != null ? configuration.getStartRow() : 0;
        long endRow = configuration.getEndRow() != null ? configuration.getEndRow() : Long.MAX_VALUE;

        query += (" Limit " + endRow + " offset " + startRow);

        return query;
    }

}
