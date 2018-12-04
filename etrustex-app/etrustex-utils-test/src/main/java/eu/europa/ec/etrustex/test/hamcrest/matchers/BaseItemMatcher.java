package eu.europa.ec.etrustex.test.hamcrest.matchers;

import org.hamcrest.TypeSafeMatcher;


/**
 * Abstract implementation of the ItemMatcher interface.
 * 
 * -------------------------------------------------
 * 
 * @author: lesouju
 * @created: 27-august-08 14:12:34
 * @project: argus 
 * -------------------------------------------------
 *  @param <T>
 */
public abstract class BaseItemMatcher<T> extends TypeSafeMatcher<T> implements ItemMatcher<T> {
    
    private T expectedItem;
    
    /**
     * Constructor with an expected item.
     * 
     * @param expectedItem
     */
    public BaseItemMatcher(T expectedItem) {
        this.expectedItem = expectedItem;
    }

    /**
     * Default constructor.
     */
    protected BaseItemMatcher() {
        this.expectedItem = null;
    }

    /* (non-Javadoc)
     * @see eu.cec.argus.test.mockito.matchers.ItemMatcher#setExpectedItem(java.lang.Object)
     */
    public void setExpectedItem(T expectedItem) {
        this.expectedItem = expectedItem;
    }
    
    /**
     * Gets the item expected by the matcher.
     *
     * @return
     */
    protected T getExpectedItem() {
        return this.expectedItem;
    }
    
}
