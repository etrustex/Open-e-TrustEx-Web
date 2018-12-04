package eu.europa.ec.etrustex.test.hamcrest.matchers;

import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import java.util.Date;

public class IsCloseToTest extends EtxCommonMatchers{
	private IsCloseTo<Date> isCloseToMatcher;
	
	@Test
	public void testBasic(){
		Date now = new Date();
		isCloseToMatcher = new IsCloseTo<>(now);
		boolean result = isCloseToMatcher.matchesSafely(now);
		
		assertThat(result, is (equalTo(true)));
	}
	
	@Test
	public void testDefaultToleranceLow(){
		Date now = new Date();
		Date actualValue = new Date(now.getTime() - 1000L);
		isCloseToMatcher = new IsCloseTo<>(now);
		boolean result = isCloseToMatcher.matchesSafely(actualValue);
		
		assertThat(result, is (equalTo(true)));
	}
	
	@Test
	public void testDefaultToleranceHigh(){
		Date now = new Date();
		Date actualValue = new Date(now.getTime() + 1000L);
		isCloseToMatcher = new IsCloseTo<>(now);
		boolean result = isCloseToMatcher.matchesSafely(actualValue);
		
		assertThat(result, is (equalTo(true)));
	}
	
	@Test
	public void testDefaultToleranceLow2(){
		Date now = new Date();
		Date actualValue = new Date(now.getTime() - 1001L);
		isCloseToMatcher = new IsCloseTo<>(now);
		boolean result = isCloseToMatcher.matchesSafely(actualValue);
		
		assertThat(result, is (equalTo(false)));
	}
	
	@Test
	public void testDefaultToleranceHigh2(){
		Date now = new Date();
		Date actualValue = new Date(now.getTime() + 1001L);
		isCloseToMatcher = new IsCloseTo<>(now);
		boolean result = isCloseToMatcher.matchesSafely(actualValue);
		
		assertThat(result, is (equalTo(false)));
	}
	
	@Test
	public void testToleranceLowOK(){
		Date now = new Date();
		Date actualValue = new Date(now.getTime() - 100L);
		isCloseToMatcher = new IsCloseTo<>(now, 100L);
		boolean result = isCloseToMatcher.matchesSafely(actualValue);
		
		assertThat(result, is (equalTo(true)));
	}

	@Test
	public void testToleranceLowFail(){
		Date now = new Date();
		Date actualValue = new Date(now.getTime() - 101L);
		isCloseToMatcher = new IsCloseTo<>(now, 100L);
		boolean result = isCloseToMatcher.matchesSafely(actualValue);
		
		assertThat(result, is (equalTo(false)));
	}

	@Test
	public void testToleranceHighOK(){
		Date now = new Date();
		Date actualValue = new Date(now.getTime() + 100L);
		isCloseToMatcher = new IsCloseTo<>(now, 100L);
		boolean result = isCloseToMatcher.matchesSafely(actualValue);
		
		assertThat(result, is (equalTo(true)));
	}

	@Test
	public void testToleranceHighFail(){
		Date now = new Date();
		Date actualValue = new Date(now.getTime() + 101L);
		isCloseToMatcher = new IsCloseTo<>(now, 100L);
		boolean result = isCloseToMatcher.matchesSafely(actualValue);
		
		assertThat(result, is (equalTo(false)));
	}

	@Test
	public void testDescribeTo() {
		Date now = new Date();
		isCloseToMatcher = new IsCloseTo<>(now, 100L);

		Description description = new StringDescription();
		isCloseToMatcher.describeTo(description);
		assertThat(description.toString(), is(equalTo("A date within 100 msec of "+now)));
    }
}
