/**
 *
 */
package eu.europa.ec.etrustex.webaccess.model.converters.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author apladap
 */
public class FileSizeFormatter {

    private static final BigDecimal UNIT_DIVISOR = new BigDecimal(1024);

    public final static int MiB = 1 * 1024 * 1024;

    private static final Map<Integer, String> levelSizeString;

    static {
        levelSizeString = new HashMap<>();
        levelSizeString.put(1, "B");
        levelSizeString.put(2, "KiB");
        levelSizeString.put(3, "MiB");
        levelSizeString.put(4, "GiB");
        levelSizeString.put(5, "TiB");
    }

    public static String formatFileSize(long sizeInBytes) {

        int sizeLevel = 1;

        BigDecimal sizeToDisplay = new BigDecimal(sizeInBytes);

        while (sizeToDisplay.doubleValue() >= 1000 && sizeLevel < 5) {

            //devide the size to display with 1024
            sizeToDisplay = sizeToDisplay.divide(UNIT_DIVISOR, 4, RoundingMode.HALF_UP);

            sizeLevel++;
        }

        //the display size in the end should have 2 decimals
        sizeToDisplay = sizeToDisplay.setScale(2, RoundingMode.HALF_UP);

        StringBuilder formattedSize = new StringBuilder();
        formattedSize.append(sizeToDisplay.toPlainString()).append(' ').append(levelSizeString.get(sizeLevel));

        return formattedSize.toString();
    }

}
