package eu.europa.ec.etrustex.test.hamcrest.matchers;


import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

public class GenericItemMatcherTest extends EtxCommonMatchers{
	private class TestObject{
		int value;
		
		TestObject(int value){
			this.value = value;
		}
		
		@Override
		public int hashCode() {
			return value;
		}

		@Override
		public String toString() {
			return "TestObject [value=" + value	+ "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TestObject other = (TestObject) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (value != other.value)
				return false;
			return true;
		}

		private GenericItemMatcherTest getOuterType() {
			return GenericItemMatcherTest.this;
		}

	}
	private GenericItemMatcher<TestObject> genericItemMatcher;
	
	
	@Test
	public void testMatchesSafelyTestOk(){
		TestObject expectedObject = new TestObject(1);
		TestObject actualObject =  new TestObject(1);

		genericItemMatcher = new GenericItemMatcher<>(expectedObject);

		boolean result = genericItemMatcher.matchesSafely(actualObject);
		
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testMatchesSafelyTestFail(){
		TestObject expectedObject = new TestObject(1);
		TestObject actualObject = new TestObject(2);

		genericItemMatcher = new GenericItemMatcher<>(expectedObject);

		boolean result = genericItemMatcher.matchesSafely(actualObject);
		
		assertThat(result, is(equalTo(false)));
	}
	
	@Test
	public void testDescribeTo(){
		TestObject expectedObject = new TestObject(1);
		Description description = new StringDescription();
		genericItemMatcher = new GenericItemMatcher<>(expectedObject);
		genericItemMatcher.describeTo(description);
		assertThat(description.toString(), is(equalTo("<TestObject [value=1]>")));
	}
}
