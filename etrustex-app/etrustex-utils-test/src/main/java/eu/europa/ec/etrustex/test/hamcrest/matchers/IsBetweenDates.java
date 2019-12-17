package eu.europa.ec.etrustex.test.hamcrest.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

/**
 * Matcher checking if a date is between two given dates.
 * -------------------------------------------------
 *
 * @author: keschma
 * @created: 28-juil.-2009
 * @project: argus
 * -------------------------------------------------
 */
public class IsBetweenDates extends TypeSafeMatcher<Date> {

    private Date date1;

    private Date date2;

    public IsBetweenDates(Date date1, Date date2) {
        super(Date.class);
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The given dates must not be null.");
        }
        this.date1 = date1;
        this.date2 = date2;
    }

    public boolean matchesSafely(Date date) {
        if (date == null) {
            return false;
        }

        long date1Millis = date1.getTime();
        long date2Millis = date2.getTime();
        long dateMillis = date.getTime();

        return (date1Millis < date2Millis) ?
            date1Millis <= dateMillis && dateMillis <= date2Millis :
            date2Millis <= dateMillis && dateMillis <= date1Millis;
    }

    public void describeTo(Description description) {
        description.appendText("A date between " + date1 + " and " + date2);
    }

}
