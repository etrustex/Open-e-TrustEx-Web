package eu.europa.ec.etrustex.test.hamcrest.matchers;

import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapMatcherTest extends EtxCommonMatchers{

	@Test
	public void testMatchesSafelyOk(){
		Map<Integer, String> expectedMap = new HashMap<>();
		expectedMap.put(1, "One");
		expectedMap.put(2, "Two");
		expectedMap.put(3, "Three");
		MapMatcher<Integer, String> mapMatcher = new MapMatcher<>(expectedMap);
		
		Map<Integer, String> actualMap = new HashMap<>();
		actualMap.put(1, "One");
		actualMap.put(2, "Two");
		actualMap.put(3, "Three");
		boolean result = mapMatcher.matchesSafely(actualMap);
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testMatchesSafelyOk2(){
		Map<Integer, String> expectedMap = new HashMap<>();
		expectedMap.put(1, "One");
		expectedMap.put(2, "Two");
		expectedMap.put(3, "Three");
		MapMatcher<Integer, String> mapMatcher = new MapMatcher<>(expectedMap);
		
		Map<Integer, String> actualMap = new HashMap<>();
		actualMap.put(2, "Two");
		actualMap.put(3, "Three");
		actualMap.put(1, "One");
		boolean result = mapMatcher.matchesSafely(actualMap);
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void testMatchesSafelyFail(){
		Map<Integer, String> expectedMap = new HashMap<>();
		expectedMap.put(1, "One");
		expectedMap.put(2, "Two");
		expectedMap.put(3, "Three");
		MapMatcher<Integer, String> mapMatcher = new MapMatcher<>(expectedMap);
		
		Map<Integer, String> actualMap = new HashMap<>();
		actualMap.put(1, "One");
		actualMap.put(2, "Deux");
		actualMap.put(3, "Three");
		boolean result = mapMatcher.matchesSafely(actualMap);
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void testMatchesSafelyFail2(){
		Map<Integer, String> expectedMap = new HashMap<>();
		expectedMap.put(1, "One");
		expectedMap.put(2, "Two");
		expectedMap.put(3, "Three");
		expectedMap.put(4, "Four");
		MapMatcher<Integer, String> mapMatcher = new MapMatcher<>(expectedMap);
		
		Map<Integer, String> actualMap = new HashMap<>();
		actualMap.put(1, "One");
		actualMap.put(2, "Two");
		actualMap.put(3, "Three");
		boolean result = mapMatcher.matchesSafely(actualMap);
		assertThat(result, is(equalTo(false)));
	}
	
	@Test
	public void testMatchesSafelyFail3(){
		Map<Integer, String> expectedMap = new HashMap<>();
		expectedMap.put(1, "One");
		expectedMap.put(2, "Two");
		expectedMap.put(3, "Three");
		MapMatcher<Integer, String> mapMatcher = new MapMatcher<>(expectedMap);
		
		Map<Integer, String> actualMap = new HashMap<>();
		actualMap.put(1, "One");
		actualMap.put(2, "Two");
		actualMap.put(3, "Three");
		actualMap.put(4, "Four");
		boolean result = mapMatcher.matchesSafely(actualMap);
		assertThat(result, is(equalTo(false)));
	}
	
	@Test
	public void testSetDescribeTo() throws Exception{
		Map<Integer, String> expectedMap = new LinkedHashMap<>();
		expectedMap.put(1, "One");
		expectedMap.put(2, "Two");
		expectedMap.put(3, "Three");
		Description description = new StringDescription();
		MapMatcher<Integer, String> mapMatcher = new MapMatcher<>(expectedMap);

		mapMatcher.describeTo(description);
		assertThat(description.toString(), is(equalTo("{[1, One], \n[2, Two], \n[3, Three]}")));
	}


}
