package eu.europa.ec.etrustex.test.hamcrest.matchers;

import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import org.hamcrest.Description;
import org.junit.Test;


/**
 * Test for the BaseItemMatcher
 * 
 * -------------------------------------------------
 * 
 * @author: toulyol
 * @created: 04-dec-09 14:12:34
 * @project: argus-commons
 * -------------------------------------------------
 */
public class BaseItemMatcherTest extends EtxCommonMatchers {

	private class NonAbstractBaseItemMatcher<T> extends BaseItemMatcher<T>{
		public NonAbstractBaseItemMatcher(T obj) {
			super(obj);
		}

		public void describeTo(Description description) {
			description.appendText("TEST");

		}

		@Override
		public boolean matchesSafely(T item) {
			return true;
		}
	}

	@Test
	public void testConstructor(){
		String obj = "TEST";
		NonAbstractBaseItemMatcher<String> baseItemMatcher = new NonAbstractBaseItemMatcher<>(obj);
		assertThat(baseItemMatcher.getExpectedItem(), is(sameInstance(obj)));
	}

	@Test
	public void testSetExpectedItem(){
		String obj = "TEST";
		String obj2 = "TEST2";
		NonAbstractBaseItemMatcher<String> baseItemMatcher = new NonAbstractBaseItemMatcher<>(obj);
		baseItemMatcher.setExpectedItem(obj2);
		assertThat(baseItemMatcher.getExpectedItem(), is(sameInstance(obj2)));
	}

}
