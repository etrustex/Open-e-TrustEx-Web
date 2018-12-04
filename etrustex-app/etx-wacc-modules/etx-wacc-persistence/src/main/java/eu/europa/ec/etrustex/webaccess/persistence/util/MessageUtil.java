package eu.europa.ec.etrustex.webaccess.persistence.util;


import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.stripAccents;

/**
 * Created by lubiesz on 24/05/2016.
 */
public class MessageUtil {

    /**
     * Remove all whitespace, control and punctuation characters and convert to lower case.
     * @param inputString
     * @return
     */
    public static String normalizeString(String inputString) {
        return isEmpty(inputString) ? inputString : stripAccents(inputString).replaceAll("[\\p{Space}\\p{Cntrl}\\p{Punct}]", "").toLowerCase();
    }
}
