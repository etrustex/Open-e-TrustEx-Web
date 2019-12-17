package eu.europa.ec.etrustex.test.hamcrest.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;

import java.util.Iterator;


/**
 * Matcher for comparing iterables to a given prototype.
 * Comparison must pass on all elements of the iterables.
 * Comparison between objects is done by an ItemMatcher.
 * If the iterables are not of the same length, the comparison
 * will fail.
 *
 * <p>
 * This matcher should not inherit from {@link org.hamcrest.TypeSafeMatcher},
 * since TypeSafeMacther does not match in comparing against null values, and
 * we need to match successfully when both objects are null.
 * </p>
 * -------------------------------------------------
 * 
 * @author: lesouju
 * @created: 27-august-08 14:09:08
 * @project: argus 
 * -------------------------------------------------
 *  @param <T>
 */
public class IterableMatcher<T, U extends Iterable<T>> extends BaseMatcher<U> implements SelfDescribing {
    
    private U expectedIterable;
    
    private ItemMatcher<T> matcher;
    
    public IterableMatcher(U expectedIterable, ItemMatcher<T> matcher) {
        this.expectedIterable = expectedIterable;
        this.matcher = matcher;
    }

    public IterableMatcher(U expectedIterable) {
        this.expectedIterable = expectedIterable;
        this.matcher = new GenericItemMatcher<>();
    }
    
    /**
     * Matches an actual item against the expected one.
     * @param item the actual item
     * @return true if the actual item matches the expected one
     */
    public boolean matches(Object item) {
        if (this.expectedIterable == null) {
            return item == null;
        }
        else if (item == null) {
            return false;
        }

        if (! (item instanceof Iterable)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Iterable<T> actualIterable = (Iterable<T>) item;
        return matchesSafely(actualIterable);
    }

    /**
     * Matches an actual iterable in a typesafe way against the expected one.
     * @param actualIterable the actual iterable
     * @return true, if the actual iterable matches the expected one
     */
    public boolean matchesSafely(Iterable<T> actualIterable) {
        Iterator<T> iterator1 = this.expectedIterable.iterator();
        Iterator<T> iterator2 = actualIterable.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            T expectedObject = iterator1.next();
            T actualObject = iterator2.next();
            this.matcher.setExpectedItem(expectedObject);
            if (!this.matcher.matches(actualObject)) {
                return false;
            }
        }
        
        if (iterator1.hasNext()) {
            // Expected collection is bigger than the actual collection
            return false;
        }
                
        if (iterator2.hasNext()) {
            // Expected collection is smaller than the actual collection
            return false;
        }
                
        return true;
    }

    /* (non-Javadoc)
     * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
     */
    public void describeTo(Description description) {
        if (this.expectedIterable == null) {
            description.appendValue(this.expectedIterable);
        }
        else {
            description.appendValueList("Iterable with\n", "\n", "");
            Iterator<T> iterator = this.expectedIterable.iterator();
            while (iterator.hasNext()) {
                description.appendValue(iterator.next());
            }
        }
    }

}
