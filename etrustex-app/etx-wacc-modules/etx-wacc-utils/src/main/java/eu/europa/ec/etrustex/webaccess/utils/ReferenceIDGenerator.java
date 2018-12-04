/**
 *
 */
package eu.europa.ec.etrustex.webaccess.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author apladap
 */
public class ReferenceIDGenerator {

    private static final Random randomLong = new Random();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static String generateAttachmentReferenceId() {
        String timeStamp = dateFormatter.format(new Date());
        return timeStamp + "-" + randomLong.nextLong();
    }
}
