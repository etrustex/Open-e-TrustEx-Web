package eu.europa.ec.etrustex.webaccess.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConcurrentHelper {

    private ConcurrentHashMap<String, Boolean> bundleMap = new ConcurrentHashMap<>();;

    /**
     * Add the key in the concurrent map if it is not already there.
     * return 'false' if it has no mapping for the key, 'true' otherwise
     *
     * @param key
     * @return
     */
    public Boolean addKey(String key) {
        Boolean result = bundleMap.putIfAbsent(key, true);
        return result == null ? Boolean.FALSE : result;
    }

    /**
     * Remove the bundle id key from the map
     *
     * @param key
     */
    public void removeKey(String key) {
        bundleMap.remove(key);
    }
}
