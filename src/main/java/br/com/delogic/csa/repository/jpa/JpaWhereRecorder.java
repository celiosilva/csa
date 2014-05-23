package br.com.delogic.csa.repository.jpa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JpaWhereRecorder {

    private List<JpaWhereFilterRecorder> statements = new LinkedList<JpaWhereFilterRecorder>();
    private static final String          SPACE      = " ";
    private Map<String, Object>          parameters = new HashMap<String, Object>();

    public void recordStatement(JpaWhereFilterRecorder stmt) {
        statements.add(stmt);
    }

    public List<JpaWhereFilterRecorder> getStatements() {
        return statements;
    }

    public String getWhereClause() {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < statements.size(); i++) {
            JpaWhereFilterRecorder f = statements.get(i);
            if (i != 0) {
                sb.append(f.getOperator());
                sb.append(SPACE);
            }
            sb.append(f.getJpqlFilter(index, parameters));
            sb.append(SPACE);
            index++;
        }
        return sb.toString();
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

}
