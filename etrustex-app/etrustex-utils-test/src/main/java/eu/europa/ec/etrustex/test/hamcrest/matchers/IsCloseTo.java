package eu.europa.ec.etrustex.test.hamcrest.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

/**
 * Matcher for being close to a given date.
 *
 * -------------------------------------------------
 * @author:  keschma
 * @created: 14:49:42
 * @project: argus-ama
 * -------------------------------------------------
 *
 */
public class IsCloseTo<T extends Date> extends TypeSafeMatcher<T> {
    
    private final static long DEFAULT_TOLERANCE_INTERVAL_MSEC = 1000L;
    
    private Date date;
    
    private long toleranceMsec;
    
    /**
     * Constructs a matcher for being close to the given date, using
     * a default tolerance.
     * @param date
     */
    public IsCloseTo(Date date) {
        this(date, DEFAULT_TOLERANCE_INTERVAL_MSEC);
    }
    
    /**
     * Constructs a matcher for being close to the given date, using
     * a custom tolerance.
     * @param date
     * @param toleranceMsec
     */
    public IsCloseTo(Date date, long toleranceMsec) {    
        this.date = date;
        this.toleranceMsec = toleranceMsec;
    }
    
    /* (non-Javadoc)
     * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
     */
    public void describeTo(Description desc) {
        desc.appendText("A date within "+toleranceMsec+" msec of "+date);
    }
    
    /* (non-Javadoc)
     * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
     */
    @Override
    public boolean matchesSafely(T item) {
        if (item == null) {
            return false;
        }
        
        return Math.abs(this.date.getTime() - item.getTime()) <= toleranceMsec;
    }

}
