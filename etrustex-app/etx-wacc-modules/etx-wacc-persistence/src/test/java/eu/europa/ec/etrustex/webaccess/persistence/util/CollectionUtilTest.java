package eu.europa.ec.etrustex.webaccess.persistence.util;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cabalen on 18/11/2016.
 */

public class CollectionUtilTest extends AbstractTest {

    @Test
    public void test_breakCollectionInSubLists_oneChunk() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<List<Integer>> chunksList = CollectionUtil.breakCollectionInSubLists(list, list.size());

        assertThat(chunksList, hasSizeEqualTo(1));
        assertThat(chunksList.get(0), hasSizeEqualTo(list.size()));
        assertThat(chunksList.get(0), equalTo(list));
    }

    @Test
    public void test_breakCollectionInSubLists_moreThanOneChunk() {
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list.addAll(list1);
        List<Integer> list2 = new ArrayList<>(Arrays.asList(6, 7, 8, 9));
        list.addAll(list2);

        List<List<Integer>> chunksList = CollectionUtil.breakCollectionInSubLists(list, list1.size());

        assertThat(chunksList, hasSizeEqualTo(2));
        assertThat(chunksList.get(0), hasSizeEqualTo(list1.size()));
        assertThat(chunksList.get(1), hasSizeEqualTo(list2.size()));
        assertThat(chunksList.get(0), equalTo(list1));
        assertThat(chunksList.get(1), equalTo(list2));
    }

}
