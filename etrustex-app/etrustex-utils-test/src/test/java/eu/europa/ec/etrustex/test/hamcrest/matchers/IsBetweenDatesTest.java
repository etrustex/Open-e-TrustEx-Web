package eu.europa.ec.etrustex.test.hamcrest.matchers;

import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import java.util.Date;

public class IsBetweenDatesTest extends EtxCommonMatchers{
	private IsBetweenDates isBetweenDateMatcher;
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullValues(){
		isBetweenDateMatcher = new IsBetweenDates(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullValueStartDate(){
		isBetweenDateMatcher = new IsBetweenDates(null, new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullValueEndDate(){
		isBetweenDateMatcher = new IsBetweenDates(new Date(), null);
	}

	@Test
	public void testBasic(){
		Date now = new Date();
		// A hour ago
		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		isBetweenDateMatcher = new IsBetweenDates(startDate, endDate);
		boolean result = isBetweenDateMatcher.matchesSafely(now);
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testBasic2(){
		Date now = new Date();
		// A hour ago
		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		isBetweenDateMatcher = new IsBetweenDates(endDate, startDate);
		boolean result = isBetweenDateMatcher.matchesSafely(now);
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testLimitLow(){
		Date now = new Date();
		// A hour ago
		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		isBetweenDateMatcher = new IsBetweenDates(startDate, endDate);
		boolean result = isBetweenDateMatcher.matchesSafely(startDate);
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testLimitLow2(){
		Date now = new Date();
		// A hour ago
		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		isBetweenDateMatcher = new IsBetweenDates(endDate, startDate);
		boolean result = isBetweenDateMatcher.matchesSafely(startDate);
		assertThat(result, is(equalTo(true)));
	}


	@Test
	public void testLimitHigh(){
		Date now = new Date();
		// A hour ago
		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		isBetweenDateMatcher = new IsBetweenDates(startDate, endDate);
		boolean result = isBetweenDateMatcher.matchesSafely(endDate);
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testLimitHigh2(){
		Date now = new Date();
		// A hour ago
		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		isBetweenDateMatcher = new IsBetweenDates(endDate, startDate);
		boolean result = isBetweenDateMatcher.matchesSafely(endDate);
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testOutLower(){
		Date now = new Date();
		// A hour ago
		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		Date outDate = new Date(startDate.getTime() - 1);
		isBetweenDateMatcher = new IsBetweenDates(startDate, endDate);
		boolean result = isBetweenDateMatcher.matchesSafely(outDate);
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testOutLower2(){
		Date now = new Date();
		// A hour ago
		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		Date outDate = new Date(startDate.getTime() - 1);
		isBetweenDateMatcher = new IsBetweenDates(endDate, startDate);
		boolean result = isBetweenDateMatcher.matchesSafely(outDate);
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testOutHigher(){
		Date now = new Date();
		// A hour ago
		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		Date outDate = new Date(endDate.getTime() + 1);
		isBetweenDateMatcher = new IsBetweenDates(startDate, endDate);
		boolean result = isBetweenDateMatcher.matchesSafely(outDate);
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testOutHigher2(){
		Date now = new Date();
		// A hour ago
		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		Date outDate = new Date(endDate.getTime() + 1);
		isBetweenDateMatcher = new IsBetweenDates(endDate, startDate);
		boolean result = isBetweenDateMatcher.matchesSafely(outDate);
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testDescribeTo() {
		Date now = new Date();

		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		Description description = new StringDescription();
		isBetweenDateMatcher = new IsBetweenDates(startDate, endDate);

		isBetweenDateMatcher.describeTo(description);
		assertThat(description.toString(), is(equalTo("A date between " + startDate + " and " + endDate)));
    }


	@Test
	public void testDescribeTo2() {
		Date now = new Date();

		Date startDate = new Date(now.getTime()- 60 * 60 * 1000);
		// in one hour
		Date endDate = new Date(now.getTime() + 60 * 60 * 1000);
		Description description = new StringDescription();
		isBetweenDateMatcher = new IsBetweenDates(endDate, startDate);

		isBetweenDateMatcher.describeTo(description);
		assertThat(description.toString(), is(equalTo("A date between " + endDate + " and " + startDate)));
    }
}
