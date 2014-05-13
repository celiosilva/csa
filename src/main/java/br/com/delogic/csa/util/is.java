package br.com.delogic.csa.util;

import java.util.Collection;
import java.util.Map;

public class is {

    /**
     * If both objects are null it'll return false
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static synchronized final <E> boolean equal(E obj1, E obj2) {
        if (obj1 == null && obj2 == null) {
            return false;
        } else if ((obj1 != null && obj2 == null)
            || (obj1 == null && obj2 != null)) {
            return false;
        } else {
            return obj1.equals(obj2);
        }
    }

    public static synchronized final <E> boolean empty(E... es) {
        if (es == null) {
            return true;
        }
        for (E e : es) {
            if (e == null || e.toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static synchronized final boolean empty(String value) {
        return value == null || value.isEmpty() || value.trim().isEmpty();
    }

    public static synchronized final <E> boolean empty(Collection<E> col) {
        return col == null || col.isEmpty();
    }

    public static final <E> boolean empty(Map<?, ?> col) {
        return col == null || col.isEmpty();
    }

    public static synchronized final <E> boolean notEmpty(E... es) {
        if (es == null) {
            return false;
        }
        for (E e : es) {
            if (e == null || String.valueOf(e).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static synchronized final boolean notEmpty(String value) {
        return value != null && (!value.isEmpty() && !value.trim().isEmpty());
    }

    public static synchronized final <E> boolean notEmpty(Collection<E> col) {
        return col != null && !col.isEmpty();
    }

    public static synchronized final <E> boolean notEmpty(Map<?, ?> col) {
        return col != null && !col.isEmpty();
    }

    public static synchronized final boolean number(String value) {
        if (empty(value)) {
            return false;
        }
        return !value.matches(".*\\D+.*");
    }

}
