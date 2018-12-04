package eu.europa.ec.etrustex.test.hamcrest.matchers;

import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;

import java.util.Collection;

public class CollectionSizeMatcher<T extends Collection <?>> extends ArgumentMatcher<T> {

	int expectedSize;
	
	
	public CollectionSizeMatcher(int expectedSize) {
		this.expectedSize = expectedSize;
	}
	
	@Override
	public boolean matches(Object argument) {
		Collection<?> collection  = (Collection<?>) argument;
		if(collection.size() == expectedSize) return true;
		return false;
	}

	public void setExpectedSize(int expectedSize) {
		this.expectedSize = expectedSize;
	}
 
    @Override
    public void describeTo(Description description) {
        super.describeTo(description);
        description.appendText("expectedSize = " + expectedSize);
    }
}
