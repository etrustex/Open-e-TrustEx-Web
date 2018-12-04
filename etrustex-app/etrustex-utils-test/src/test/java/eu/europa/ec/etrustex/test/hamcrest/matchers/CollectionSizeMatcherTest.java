package eu.europa.ec.etrustex.test.hamcrest.matchers;

import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionSizeMatcherTest extends EtxCommonMatchers{
	private CollectionSizeMatcher<Collection<String>> collectionMatcher;
	
	@Test
	public void testMatchesOk(){
		List<String> testList = Arrays.asList("toto", "titi");
		collectionMatcher = new CollectionSizeMatcher<>(2);
		
		boolean result = collectionMatcher.matches(testList);
		
		assertThat(result, is(equalTo(true)));
	}
	
	
	@Test
	public void testMatchesFail(){
		List<String> testList = Arrays.asList("toto", "titi");
		collectionMatcher = new CollectionSizeMatcher<>(3);
		
		boolean result = collectionMatcher.matches(testList);
		
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testSetMatchesOk(){
		List<String> testList = Arrays.asList("toto", "titi");
		collectionMatcher = new CollectionSizeMatcher<>(3);
		collectionMatcher.setExpectedSize(2);
		boolean result = collectionMatcher.matches(testList);
		
		assertThat(result, is(equalTo(true)));
	}
	
	@Test
	public void testSetDescribeTo(){
		Description description = new StringDescription();
		collectionMatcher = new CollectionSizeMatcher<>(10);
		collectionMatcher.describeTo(description);
		assertThat(description.toString(), is(equalTo("<Collection size matcher>expectedSize = 10")));
	}
}
