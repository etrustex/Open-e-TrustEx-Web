package eu.europa.ec.etrustex.test.hamcrest.matchers;

import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputStreamMatcherTest extends EtxCommonMatchers{
	private InputStreamMatcher inputStreamMatcher;
	
	@Test
	public void testMatchesSafelyNullFalse(){
		InputStream expectedStream = null;
		InputStream actualStream = new ByteArrayInputStream("Lorem Ipsum".getBytes());
		inputStreamMatcher = new InputStreamMatcher(expectedStream);
		boolean result = inputStreamMatcher.matchesSafely(actualStream);
		assertThat(result, is(equalTo(false)));
	}
	
	
	@Test
	public void testMatchesSafelyNullFalse2(){
		InputStream expectedStream = new ByteArrayInputStream("Lorem Ipsum".getBytes());
		InputStream actualStream = null;
		inputStreamMatcher = new InputStreamMatcher(expectedStream);
		boolean result = inputStreamMatcher.matchesSafely(actualStream);
		assertThat(result, is(equalTo(false)));
	}
	
	@Test
	public void testMatchesSafelyNullTrue(){
		InputStream expectedStream = null;
		InputStream actualStream = null;
		inputStreamMatcher = new InputStreamMatcher(expectedStream);
		boolean result = inputStreamMatcher.matchesSafely(actualStream);
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testStreamWithSameContent() throws Exception{
		InputStream expectedStream = new ByteArrayInputStream("Lorem Ipsum".getBytes());
		InputStream actualStream = new ByteArrayInputStream("Lorem Ipsum".getBytes());
		inputStreamMatcher = new InputStreamMatcher(expectedStream);
		boolean result = inputStreamMatcher.matchesSafely(actualStream);
		assertThat(result, is(equalTo(true)));
	}
	
	@Test
	public void testBlobWithSlightDifferentContent() throws Exception{
		InputStream expectedStream = new ByteArrayInputStream("Lorem Ipsum".getBytes());
		InputStream actualStream = new ByteArrayInputStream("Lorem Ipsum ".getBytes());
		inputStreamMatcher = new InputStreamMatcher(expectedStream);
		boolean result = inputStreamMatcher.matchesSafely(actualStream);
		assertThat(result, is(equalTo(false)));
	}
	
	@Test
	public void testBlobWithHighDifferentContent() throws Exception{
		InputStream expectedStream = new ByteArrayInputStream("Lorem Ipsum".getBytes());
		InputStream actualStream = new ByteArrayInputStream("Rorem Ipsum".getBytes());
		inputStreamMatcher = new InputStreamMatcher(expectedStream);
		boolean result = inputStreamMatcher.matchesSafely(actualStream);
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testSetDescribeTo() throws Exception{
		InputStream expectedStream = new ByteArrayInputStream("Lorem Ipsum".getBytes());
		inputStreamMatcher = new InputStreamMatcher(expectedStream);

		Description description = new StringDescription();
		inputStreamMatcher.describeTo(description);
		assertThat(description.toString(), is(equalTo("InputStream with\n\"lenght=11" +
				"\"")));
	}

}
