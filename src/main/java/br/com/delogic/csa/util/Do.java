package br.com.delogic.csa.util;

import java.text.Normalizer;
import java.util.Collection;
import java.util.regex.Pattern;

public class Do {

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
    public static synchronized String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static synchronized final <E> void removeNull(Collection<E> data) {
        if (is.empty(data)) {
            return;
        }
        while (data.contains(null)) {
            data.remove(null);
        }
    }

}
