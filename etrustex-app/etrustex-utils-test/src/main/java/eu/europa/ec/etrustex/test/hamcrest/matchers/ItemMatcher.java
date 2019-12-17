package eu.europa.ec.etrustex.test.hamcrest.matchers;

import org.hamcrest.Matcher;


/**
 * Interface for a matcher between objects of type U, which
 * could be part of an iterable collection.
 * 
 * -------------------------------------------------
 * 
 * @author: lesouju
 * @created: 27-august-08 14:10:31
 * @project: argus 
 * -------------------------------------------------
 *  @param <T>
 */
public interface ItemMatcher<T> extends Matcher<T> {
    
    /**
     * Set the item expected by the matcher.
     *
     * @param expectedItem
     */
    void setExpectedItem(T expectedItem);
    
}
