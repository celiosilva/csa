package br.com.delogic.csa.util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Do {

    @SuppressWarnings("unchecked")
    public static final <E extends Object> void forEach(Collection<E> col,
        Each<E> each) {
        if (col != null && !col.isEmpty()) {
            Object[] es = col.toArray();
            for (int i = 0; i < es.length; i++) {
                each.each((E) es[i], i);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static final <E extends Object> void forEach(E[] col, Each<E> each) {
        if (col != null && col.length > 0) {
            Object[] es = col;
            for (int i = 0; i < es.length; i++) {
                each.each((E) es[i], i);
            }
        }
    }

    public static final <IN extends Object, OUT, IND extends IN> List<OUT> convertList(
        Collection<IND> data, Converter<IN, OUT> converter) {
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

    public static final <IN extends Object, OUT, IND extends IN> Set<OUT> convertSet(
        Collection<IND> data, Converter<IN, OUT> converter) {
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

    @SuppressWarnings("unchecked")
    public static final <C extends Comparable<C>> int compare(C c1, C c2) {
        if (notNull(c1, c2)) {
            return c1.compareTo(c2);
        } else if (isNull(c1) && notNull(c2)) {
            return -1;
        } else if (notNull(c1) && isNull(c2)) {
            return 1;
        } else {
            return 0;
        }
    }

    static final String mask(String mask, String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        char[] maskinline = mask.replace("#", "_").toCharArray();
        char[] formattedValue = mask.replace("#", "_").toCharArray();

        char[] curval = value.toCharArray();
        int im = 0;
        for (int iv = 0; iv < curval.length && iv < maskinline.length;) {
            if (im >= maskinline.length || iv >= curval.length) {
                break;
            }
            if (maskinline[im] == curval[iv]) {
                formattedValue[im] = curval[iv];
                im++;
                iv++;
            } else if (maskinline[im] != '_') {
                formattedValue[im] = maskinline[im];
                im++;
            } else if (Character.isLetterOrDigit(curval[iv])) {
                formattedValue[im] = curval[iv];
                iv++;
                im++;
            } else {
                formattedValue[im] = maskinline[im];
                iv++;
                im++;
            }
        }
        for (; im < maskinline.length; im++) {
            formattedValue[im] = maskinline[im];
        }
        return String.valueOf(formattedValue);
    }

    /**
     * Removes characters accents like á à â ã ç replacing them by a a a a c
     *
     * @param s
     * @return
     */
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static final <E> E find(Collection<E> collection, Find<E> find) {
        if (is.empty(collection)) {
            return null;
        }
        for (E e : collection) {
            if (find.found(e)) {
                return e;
            }
        }
        return null;
    }

    public static final <E> long count(Collection<E> collection, Count<E> counter) {
        long count = 0;
        if (is.empty(collection)) {
            return count;
        }
        for (E e : collection) {
            count += counter.count(e);
        }
        return count;
    }

    public static final <E> void removeNull(Collection<E> data) {
        if (is.empty(data)) {
            return;
        }
        while (data.contains(null)) {
            data.remove(null);
        }
    }

    private static final <E> boolean notNull(E... es) {
        for (E e : es) {
            if (e == null) {
                return false;
            }
        }
        return true;
    }

    private static final <E> boolean isNull(E... es) {
        for (E e : es) {
            if (e == null) {
                return true;
            }
        }
        return false;
    }

}
