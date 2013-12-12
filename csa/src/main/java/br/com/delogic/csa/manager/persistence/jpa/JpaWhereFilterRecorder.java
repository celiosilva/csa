package br.com.delogic.csa.manager.persistence.jpa;

import java.util.Map;

public class JpaWhereFilterRecorder {

    private final String entityReference;
    private String       operator;
    private String       condition;
    private String       method;
    private String       parameterKey;
    private Object       parameterValue;
    private boolean      useUpperCase;

    public void clean() {
        this.operator = null;
        this.condition = null;
        this.method = null;
        this.parameterKey = null;
        this.parameterValue = null;
    }

    public JpaWhereFilterRecorder(String entityReference) {
        this.entityReference = entityReference;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        if (this.method == null || this.method.isEmpty()) {
            this.method = entityReference + "." + method;
        } else {
            this.method = this.method + "." + method;
        }
        this.parameterKey = method;
    }

    public String getEntityReference() {
        return entityReference;
    }

    public String getParameterKey() {
        return parameterKey;
    }

    public Object getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(Object[] parameterValue) {
        if (parameterValue != null && parameterValue.length > 0) {
            this.parameterValue = parameterValue[0];
            // throw new IllegalArgumentException("Parameter for method " +
            // parameterKey + " cannot be null");
        }
        // if (parameterValue.length > 1) {
        // throw new IllegalArgumentException("Parameter for method " +
        // parameterKey + " cannot be more than one");
        // }
    }

    public String getJpqlFilter(int index, Map<String, Object> parameters) {
        StringBuilder sb = new StringBuilder();
        if (useUpperCase) {
            sb.append("upper(" + method + ")");
        } else {
            sb.append(method);
        }
        sb.append(" ");
        sb.append(condition);
        sb.append(" ");
        if (parameterValue != null) {
            if (useUpperCase) {
                sb.append("upper(:" + parameterKey + index + ")");
            } else {
                sb.append(":" + parameterKey + index);
            }
            parameters.put(parameterKey + index, parameterValue);
        }
        return sb.toString();
    }

    public boolean isUseUpperCase() {
        return useUpperCase;
    }

    public void setUseUpperCase(boolean useUpperCase) {
        this.useUpperCase = useUpperCase;
    }

}
