package eu.europa.ec.etrustex.test.hamcrest.matchers;


import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IterableMatcherTest extends EtxCommonMatchers{

	@Test
	public void testMatchesSafelyOk(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList("toto", "tutu"));
		boolean result = iterableMatcher.matchesSafely(Arrays.asList("toto", "tutu"));
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testMatchesSafelyFail(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList("toto", "tutu"));
		boolean result = iterableMatcher.matchesSafely(Arrays.asList("toto", "tata"));
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testMatchesSafelyFail2(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList("toto", "tutu"));
		boolean result = iterableMatcher.matchesSafely(Arrays.asList("toto", "tutu", "titi"));
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testMatchesSafelyFail3(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList("toto", "tutu"));
		boolean result = iterableMatcher.matchesSafely(Arrays.asList("tutu", "toto"));
		assertThat(result, is(equalTo(false)));
	}
	
	@Test
	public void testMatchesNull(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(null);
		boolean result = iterableMatcher.matches(null);
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testMatchesNullBis(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(null);
		boolean result = iterableMatcher.matches(Arrays.asList("toto", "tutu"));
		assertThat(result, is(equalTo(false)));
	}


	@Test
	public void testMatchesNullTer(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList("toto", "tutu"));
		boolean result = iterableMatcher.matches(null);
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testMatchesOk(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList("toto", "tutu"));
		boolean result = iterableMatcher.matches(Arrays.asList("toto", "tutu"));
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testMatchesFail(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList("toto", "tutu"));
		boolean result = iterableMatcher.matches(Arrays.asList("toto", "tata"));
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testMatchesFail2(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList("toto", "tutu"));
		boolean result = iterableMatcher.matches(Arrays.asList("toto", "tutu", "titi"));
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testMatchesFail3(){
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList("toto", "tutu"));
		boolean result = iterableMatcher.matches(Arrays.asList("tutu", "toto"));
		assertThat(result, is(equalTo(false)));
	}
	
	@Test
	public void testMatchesOkWithMockMatcher(){
		ItemMatcher<String> stringMatcher = mock(ItemMatcher.class);
		String toto = "toto";
		String tutu = "tutu";
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList(toto, tutu),
				stringMatcher);
		when(stringMatcher.matches(toto)).thenReturn(true);
		when(stringMatcher.matches(tutu)).thenReturn(true);
		boolean result = iterableMatcher.matches(Arrays.asList(toto, tutu));
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testMatchesOFailsWithMockMatcher(){
		ItemMatcher<String> stringMatcher = mock(ItemMatcher.class);
		String toto = "toto";
		String tutu = "tutu";
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList(toto, tutu),
				stringMatcher);
		when(stringMatcher.matches(toto)).thenReturn(true);
		when(stringMatcher.matches(tutu)).thenReturn(false);
		boolean result = iterableMatcher.matches(Arrays.asList(toto, tutu));
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testSetDescribeTo() throws Exception{
		Description description = new StringDescription();
		IterableMatcher<String, List<String>> iterableMatcher = new IterableMatcher<>(Arrays.asList("toto", "tutu"));

		iterableMatcher.describeTo(description);
		assertThat(description.toString(), is(equalTo("Iterable with\n\"toto\"\"tutu\"")));
	}
}

