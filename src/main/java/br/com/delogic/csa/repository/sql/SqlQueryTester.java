package br.com.delogic.csa.repository.sql;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.Assert;

import br.com.delogic.csa.repository.Criteria;
import br.com.delogic.csa.repository.sql.SqlQuery.PermittedParameterType;
import br.com.delogic.jfunk.Has;

public class SqlQueryTester {

    @SuppressWarnings("rawtypes")
    public void testAllQueries(Map<String, SqlQuery> beans) {
        Assert.isTrue(!beans.isEmpty());

        for (Entry<String, SqlQuery> entry : beans.entrySet()) {
            SqlQuery<?> sqlQuery = entry.getValue();
            Criteria config = new Criteria();
            if (sqlQuery.getMandatoryParameters() != null) {
                for (Entry<String, PermittedParameterType> mp : sqlQuery.getMandatoryParameters().entrySet()) {
                    config.addParameter(mp.getKey(), mp.getValue().getExample());
                }
            }
            System.out.println("Running get List complete");
            int size = sqlQuery.getList(config).size();
            System.out.println("Running get Count complete");
            Assert.isTrue(size == sqlQuery.count(config), "Incorrect result for query:" + entry.getKey());

            if (Has.content(sqlQuery.getRegisteredParameters())) {
                for (Entry<String, PermittedParameterType> pentry : sqlQuery.getRegisteredParameters().entrySet()) {

                    System.out.println(entry.getKey() + ":Running get List with individual parameter:" + pentry.getKey());
                    Criteria configIndividualParam = new Criteria();
                    configIndividualParam.setOffset(0L);
                    configIndividualParam.setLimit(1L);
                    configIndividualParam.addParameter(pentry.getKey(), pentry.getValue().getExample());
                    if (config.getParameters() != null) {
                        for (Entry<String, Object> mandatoryParams : config.getParameters().entrySet()) {
                            configIndividualParam.addParameter(mandatoryParams.getKey(), mandatoryParams.getValue());
                        }
                    }
                    sqlQuery.getList(configIndividualParam);

                    config.addParameter(pentry.getKey(), pentry.getValue().getExample());
                }
            }
            if (Has.content(sqlQuery.getParameterizedOrderBy())) {
                config.setParameterizedOrderBy(sqlQuery.getParameterizedOrderBy().keySet().toArray(new String[sqlQuery.getParameterizedOrderBy().size()]));
            }
            System.out.println("Running get List with parameters");
            int psize = sqlQuery.getList(config).size();
            System.out.println("Running get Count with parameters");
            Assert.isTrue(psize == sqlQuery.count(config), "Incorrect result for query:" + entry.getKey());

            config.setOffset(1L);
            config.setLimit(2L);
            System.out.println("Running get List with parameters and row number");
            sqlQuery.getList(config);

        }

        System.out.println("Executed " + beans.size() + " queries ######################################################");
    }
}
