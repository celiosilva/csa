package br.com.delogic.cbsa.util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class f {

    /**
     * If both objects are null it'll return false
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static final <E> boolean equals(E obj1, E obj2) {
        if (obj1 == null && obj2 == null) {
            return false;
        } else if ((obj1 != null && obj2 == null)
            || (obj1 == null && obj2 != null)) {
            return false;
        } else {
            return obj1.equals(obj2);
        }
    }

    public static final <E> boolean notNull(E... es) {
        for (E e : es) {
            if (e == null) {
                return false;
            }
        }
        return true;
    }

    public static final <E> boolean notEmpty(E... es) {
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

    public static final <E> boolean isEmpty(E... es) {
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

    public static final boolean hasValue(String value) {
        return value != null && (!value.isEmpty() && !value.trim().isEmpty());
    }

    public static final boolean isEmpty(String value) {
        return value == null || value.isEmpty() || value.trim().isEmpty();
    }

    public static final <E> boolean notEmpty(Collection<E> col) {
        return col != null && !col.isEmpty();
    }

    public static final <E> boolean notEmpty(Map<?, ?> col) {
        return col != null && !col.isEmpty();
    }

    public static final <E> boolean isEmpty(Collection<E> col) {
        return col == null || col.isEmpty();
    }

    public static final <E> boolean isEmpty(Map<?, ?> col) {
        return col == null || col.isEmpty();
    }

    public static final <E> List<E> arrayList(E... es) {
        ArrayList<E> list = new ArrayList<E>();
        for (E e : es) {
            list.add(e);
        }
        return list;
    }

    public static final <E> E[] array(E... e) {
        return e;
    }

    public static final <E> boolean isNull(E... es) {
        for (E e : es) {
            if (e == null) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isTrue(Boolean... es) {
        for (Boolean e : es) {
            if (e == null || e == Boolean.FALSE) {
                return false;
            }
        }
        return true;
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

    public static final <K, V> MapBuilder<K, V> map() {
        return new MapBuilder<K, V>();
    }

    public static class MapBuilder<K, V> {

        private final Map<K, V> map = new LinkedHashMap<K, V>();

        public MapBuilder<K, V> put(K key, V value) {
            map.put(key, value);
            return this;
        }

        public Map<K, V> get() {
            return map;
        }

    }

    public static final boolean or(boolean... bol) {
        for (boolean b : bol) {
            if (b == true) {
                return true;
            }
        }
        return false;
    }

    public static final boolean or(Object subject, Object... possibilities) {
        for (Object poss : possibilities) {
            if (equals(subject, poss)) {
                return true;
            }
        }
        return false;
    }

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

    public static final <IN> String toStringList(String separador,
        Collection<? extends IN> col, Converter<IN, String> converter) {
        if (f.isEmpty(col)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (IN in : col) {
            String val = converter.convert(in);
            if (f.notEmpty(val)) {
                sb.append(val);
                sb.append(", ");
            }
        }
        String list = sb.toString();
        if (list.endsWith(", ")) {
            list += "tbr@#";
            list = list.replace(", tbr@#", "");
        }
        return list;

    }

    public static final boolean isNumber(String value) {
        if (isEmpty(value)) {
            return false;
        }
        return !value.matches(".*\\D+.*");
    }

    public static final Object firstNotNull(Object... objs) {
        if (objs == null) {
            throw new IllegalArgumentException(
                "At least one of the values must be a non null value");
        }
        for (Object s : objs) {
            if (s != null) {
                return s;
            }
        }
        throw new IllegalArgumentException(
            "At least one of the values must be a non null value");
    }

    public static final String format(String string, Object argument) {
        return String.format(string, argument);
    }

    public static final <T extends Comparable<T>> List<T> sort(Collection<T> collection) {
        List<T> list = new ArrayList<T>(collection);
        Collections.sort(list);
        return list;
    }

    public static final String mask(String mask, String value) {
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

    public static <E> List<E> asList(Collection<E> col) {
        List<E> lista = new LinkedList<E>();
        if (col == null) {
            return lista;
        }
        for (E e : col) {
            lista.add(e);
        }
        return lista;
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

    public static final <E> E get(Collection<E> collection, Find<E> find) {
        if (isEmpty(collection)) {
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
        if (isEmpty(collection)) {
            return count;
        }
        for (E e : collection) {
            count += counter.count(e);
        }
        return count;
    }

    public static final <E> void removeNull(Collection<E> data) {
        if (isEmpty(data)) {
            return;
        }
        while (data.contains(null)) {
            data.remove(null);
        }
    }

    public static final void assertNotNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static final Date todayPlus(double days) {
        return new Date((long) (System.currentTimeMillis() + (days * 24 * 60 * 60 * 1000)));
    }

    public static final void assertNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
    }

    public static final String concat(String... strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String st : strings) {
            sb.append(st);
        }
        return sb.toString();
    }

    public static final String concatNonEmpty(String separator, String... strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String st : strings) {
            if (hasValue(st)) {
                sb.append(st);
                sb.append(separator);
            }
        }
        sb.replace(sb.length() - separator.length(), sb.length(), "");
        return sb.toString();
    }

}
