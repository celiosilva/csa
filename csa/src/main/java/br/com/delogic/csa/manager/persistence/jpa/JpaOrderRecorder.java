package br.com.delogic.csa.manager.persistence.jpa;

import java.util.LinkedList;

public class JpaOrderRecorder {

    private final String       entityReference;
    private String             orderType;
    private LinkedList<String> order    = new LinkedList<String>();
    private boolean            finished = true;

    public JpaOrderRecorder(String entityReference) {
        this.entityReference = entityReference;
    }

    public void addMethod(String method) {
        if (!finished) {
            String last = order.removeLast();
            order.addLast(last + "." + method);
        } else {
            order.addLast(entityReference + "." + method);
        }
        finished = false;
    }

    public void addLastMethod(String method) {
        if (finished) {
            order.addLast(entityReference + "." + method + " " + orderType);
        } else {
            String last = order.removeLast();
            order.addLast(last + "." + method + " " + orderType);
        }
        finished = true;
    }

    public String getEntityReference() {
        return entityReference;
    }

    public String getOrderClause() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.size(); i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(order.get(i));
        }
        return sb.toString();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

}
