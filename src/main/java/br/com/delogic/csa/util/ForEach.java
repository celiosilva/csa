package br.com.delogic.csa.util;

import java.util.Collection;

public class ForEach<E extends Object> {

    @SuppressWarnings("unchecked")
    public ForEach(Collection<E> col, Each<E> each) {
        if (col != null && !col.isEmpty()) {
            Object[] es = col.toArray();
            for (int i = 0; i < es.length; i++) {
                each.each((E) es[i], i);
            }
        }
    }

    public ForEach(E[] col, Each<E> each) {
        if (col != null && col.length > 0) {
            E[] es = col;
            for (int i = 0; i < es.length; i++) {
                each.each(es[i], i);
            }
        }
    }

}
