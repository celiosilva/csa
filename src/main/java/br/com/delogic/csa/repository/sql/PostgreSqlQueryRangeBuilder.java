package br.com.delogic.csa.repository.sql;

import br.com.delogic.csa.repository.Criteria;
import br.com.delogic.jfunk.Has;

public class PostgreSqlQueryRangeBuilder implements SqlQueryRangeBuilder {

    public String buildRangeQuery(String query, Criteria criteria) {

        if (Has.content(criteria.getOffset()) || Has.content(criteria.getLimit())) {
            long offset = criteria.getOffset() != null ? criteria.getOffset() : 0;
            long limit = criteria.getLimit() != null ? criteria.getLimit() : Long.MAX_VALUE;
            query += (" Limit " + limit + " offset " + offset);
        }

        return query;
    }

}
