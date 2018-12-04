package eu.europa.ec.etrustex.webaccess.persistence.util;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cabalen on 17/11/2016.
 */

public class CollectionUtil {

    /**
     * Breaks the original list in chunks of the parametrized maximum size.
     *
     * @param inputList Original list.
     * @param maxSize   Maximum size.
     * @return Chunks of the original list.
     */

    public static <T> List<List<T>> breakCollectionInSubLists(List<T> inputList, int maxSize) {
        List<List<T>> resultList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i += maxSize) {
            resultList.add(inputList.subList(i, Math.min(i + maxSize, inputList.size())));
        }

        return resultList;
    }

}
