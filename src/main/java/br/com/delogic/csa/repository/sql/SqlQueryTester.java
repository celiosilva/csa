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
            SqlQuery<?> q = entry.getValue();
            Criteria config = new Criteria();
            if (q.getMandatoryParameters() != null) {
                for (Entry<String, PermittedParameterType> mp : q.getMandatoryParameters().entrySet()) {
                    config.addParameter(mp.getKey(), mp.getValue().getExample());
                }
            }
            System.out.println("Running get List complete");
            int size = q.getList(config).size();
            System.out.println("Running get Count complete");
            Assert.isTrue(size == q.count(config), "Incorrect result for query:" + entry.getKey());

            if (Has.content(q.getRegisteredParameters())) {
                for (Entry<String, PermittedParameterType> pentry : q.getRegisteredParameters().entrySet()) {

                    System.out.println(entry.getKey() + ":Running get List with individual parameter:" + pentry.getKey());
                    Criteria configIndividualParam = new Criteria();
                    configIndividualParam.setStartRow(0L);
                    configIndividualParam.setEndRow(1L);
                    configIndividualParam.addParameter(pentry.getKey(), pentry.getValue().getExample());
                    if (config.getParameters() != null) {
                        for (Entry<String, Object> mandatoryParams : config.getParameters().entrySet()) {
                            configIndividualParam.addParameter(mandatoryParams.getKey(), mandatoryParams.getValue());
                        }
                    }
                    q.getList(configIndividualParam);

                    config.addParameter(pentry.getKey(), pentry.getValue().getExample());
                }
            }
            if (Has.content(q.getOrders())) {
                config.setOrderByKey(q.getOrders().keySet().toArray(new String[q.getOrders().size()]));
            }
            System.out.println("Running get List with parameters");
            int psize = q.getList(config).size();
            System.out.println("Running get Count with parameters");
            Assert.isTrue(psize == q.count(config), "Incorrect result for query:" + entry.getKey());

            config.setStartRow(1L);
            config.setEndRow(2L);
            System.out.println("Running get List with parameters and row number");
            q.getList(config);

        }

        System.out.println("Executed " + beans.size() + " queries ######################################################");
    }
}
