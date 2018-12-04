package eu.europa.ec.etrustex.test.hamcrest.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;


/**
 * Basic implementation of a ItemMatcher. This matcher can be used
 * to compare iterable collection of basic type like Integer, String, ...
 * (actually, all types that implement the equals method in a complete way).
 * 
 * -------------------------------------------------
 * 
 * @author: lesouju
 * @created: 27-august-08 14:15:21
 * @project: argus 
 * -------------------------------------------------
 *  @param <T>
 */
public class GenericItemMatcher<T> extends BaseItemMatcher<T> {
    
    /**
     * Constructor with an expected item.
     * 
     * @param expectedItem
     */
    public GenericItemMatcher(T expectedItem) {
        super(expectedItem);
    }

    /**
     * Default constructor.
     */
    public GenericItemMatcher() {
        super();
    }

    /* (non-Javadoc)
     * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
     */
    public boolean matchesSafely(T actualItem) {
        Matcher<T> matcher = Matchers.equalTo(this.getExpectedItem());
        return matcher.matches(actualItem);
    }

    /* (non-Javadoc)
     * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
     */
    public void describeTo(Description description) {
        description.appendValue(this.getExpectedItem());
    }

}
