package eu.europa.ec.etrustex.test.hamcrest.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.collection.IsMapContaining;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapMatcher<K,V> extends TypeSafeMatcher<Map<K,V>> {
	Map<K, V> expectedMap;

	public MapMatcher(Map<K, V> expectedMap){ 
		this.expectedMap = expectedMap;
	}

	@Override
	public boolean matchesSafely(Map<K, V> actualMap) {
		if (this.expectedMap == null) {
			return actualMap == null;
		}
		else if (actualMap == null) {
			return false;
		} 

		if(expectedMap.size() != actualMap.size()) {
			return false;
		}
		
		Set<Map.Entry<K, V>> expectedEntrySet = expectedMap.entrySet();

		Iterator<Map.Entry<K, V>> expectedIterator = expectedEntrySet.iterator();
		while (expectedIterator.hasNext()){
			Map.Entry<K, V> entry = expectedIterator.next();
			GenericItemMatcher<K> keyMatcher = new GenericItemMatcher<>(entry.getKey());
			GenericItemMatcher<V> valueMatcher = new GenericItemMatcher<>(entry.getValue());
			IsMapContaining<K, V> isMapContainingMatcher = new IsMapContaining<>(keyMatcher, valueMatcher);
			if (!isMapContainingMatcher.matchesSafely(actualMap)) {
				return false;
			} 
		}
		return true;
	}

	public void describeTo(Description description) {
		Set<Map.Entry<K, V>> expectedEntrySet = expectedMap.entrySet();
		
		Iterator<Map.Entry<K, V>> expectedIterator = expectedEntrySet.iterator();

		description.appendText("{");

		while (expectedIterator.hasNext()){
			Map.Entry<K, V> entry = expectedIterator.next();
			description.appendText("[");
			description.appendText(entry.getKey().toString());
			description.appendText(", ");
			description.appendText(entry.getValue().toString());
			description.appendText("]");
			if(expectedIterator.hasNext()){
				description.appendText(", \n");
			}
		}
		description.appendText("}");

	}



}
