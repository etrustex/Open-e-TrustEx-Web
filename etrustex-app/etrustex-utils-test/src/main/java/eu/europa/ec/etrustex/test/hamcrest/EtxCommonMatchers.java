package eu.europa.ec.etrustex.test.hamcrest;

import eu.europa.ec.etrustex.test.hamcrest.matchers.*;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.TypeSafeMatcher;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.And;
import org.mockito.internal.matchers.Or;


import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class EtxCommonMatchers extends org.hamcrest.Matchers {

    public static <T> Matcher<T> and(Matcher<T>... arguments) {
        return new And(Arrays.asList((Matcher[]) arguments));
    }

    public static <T> Matcher<T> or(Matcher<T>... arguments) {
        return new Or(Arrays.asList((Matcher[]) arguments));
    }

    /**
     * Returns a matcher matching all dates that are between two given ones.
     *
     * @param date1 a first date, cannot be null
     * @param date2 a second date, cannot be null
     * @return a matcher matching all dates between date1 and date2
     */
    public static Matcher<Date> between(Date date1, Date date2) {
        return new IsBetweenDates(date1, date2);
    }

    /**
     * Returns a matcher matching all dates that are between the given and the
     * current date.
     *
     * @param date a date, cannot be null
     * @return a matcher matching all dates between date and now
     */
    public static Matcher<Date> betweenDateAndNow(Date date) {
        return new IsBetweenDates(date, new Date());
    }

    /**
     * Checks whether a date is close to a given date - where "close"
     * means being less than 1 second apart.
     *
     * @param date
     * @return
     */
    public static <T extends Date> Matcher<T> closeTo(Date date) {
        return new IsCloseTo<>(date, 1000L);
    }

    /**
     * Checks that the date is equal to a given date with the precision of the second.
     * This matcher is useful when working with DB object that does not store the second
     * This matcher removes the millisecond to the date passed as parameter.
     *
     * @param date to be compared
     */
    public static <T extends Date> Matcher<T> equalToTheNearestSecond(Date date) {
        return closeTo(new Date(date.getTime() / 1000 * 1000));
    }

    /**
     * Matches all dates that are close to now.
     *
     * @return
     */
    public static <T extends Date> Matcher<T> closeToNow() {
        return closeTo(new Date());
    }

    /**
     * Checks that an iterable is equal to another iterable.
     *
     * @param <T>
     * @param <U>
     * @param expectedIterable
     * @return a matcher
     * @see IterableMatcher IterableMatcher
     */
    public static <T, U extends Iterable<T>> Matcher<U> equalTo(U expectedIterable) {
        return new IterableMatcher<>(expectedIterable, new GenericItemMatcher<T>());
    }

    /**
     * Checks that an iterable is equal to another iterable. Every item of the iterable
     * will be matched with the given matcher.
     *
     * @param <T>
     * @param <U>
     * @param expectedIterable
     * @param matcher
     * @return a matcher
     */
    public static <T, U extends Iterable<T>> Matcher<U> equalTo(U expectedIterable, ItemMatcher<T> matcher) {
        return new IterableMatcher<>(expectedIterable, matcher);
    }

    /**
     * Checks that a given Collection object has the expected size.
     *
     * @param <T>
     * @param size
     * @return
     */

    public static <T extends Collection<?>> ArgumentMatcher<T> hasSizeEqualTo(int size) {
        return new CollectionSizeMatcher<>(size);
    }

    /**
     * Checks that a map has a given size.
     *
     * @param expectedSize the expected size
     * @return true if the map has the given size
     */
    public static <K, V> Matcher<Map<K, V>> isMapWithSize(final int expectedSize) {
        return new TypeSafeMatcher<Map<K, V>>() {
            public boolean matchesSafely(Map<K, V> map) {
                return (map != null) && map.entrySet().size() == expectedSize;
            }

            public void describeTo(Description description) {
                description.appendText("map with size " + expectedSize);
            }
        };
    }

    public static <K, V> TypeSafeMatcher<Map<K, V>> hasSameContent(Map<K, V> expectedMap) {
        return new MapMatcher<>(expectedMap);
    }

    /**
     * Checks that an InputStream is equal to another InputStream.
     *
     * @param expectedStream
     * @return
     * @see InputStreamMatcher
     */
    public static Matcher<InputStream> equalTo(InputStream expectedStream) {
        return new InputStreamMatcher(expectedStream);
    }


    // just wrap the assertThat to avoid the import
    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat(actual, matcher);
    }

    // just wrap the assertThat to avoid the import
    public static <T> void assertThat(String str, T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat(str, actual, matcher);
    }
}
