package br.com.delogic.csa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Converter {

    public final <IN extends Object, OUT, IND extends IN> List<OUT> list(Collection<IND> data, Convertable<IN, OUT> converter) {
        List<OUT> conversions = new ArrayList<OUT>();
        if (data != null && !data.isEmpty()) {
            for (IN in : data) {
                if (in != null) {
                    conversions.add(converter.convert(in));
                }
            }
        }
        return conversions;
    }

    public final <IN extends Object, OUT, IND extends IN> Set<OUT> set(Collection<IND> data, Convertable<IN, OUT> converter) {
        Set<OUT> conversions = new HashSet<OUT>();
        if (data != null && !data.isEmpty()) {
            for (IN in : data) {
                if (in != null) {
                    conversions.add(converter.convert(in));
                }
            }
        }
        return conversions;
    }

}
