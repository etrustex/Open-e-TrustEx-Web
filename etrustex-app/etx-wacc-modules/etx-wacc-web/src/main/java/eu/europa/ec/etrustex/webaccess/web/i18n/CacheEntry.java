package eu.europa.ec.etrustex.webaccess.web.i18n;

public class CacheEntry<T> {

	private T item;

	private final long cacheTimestamp;

	public CacheEntry(T item) {
		this.item = item;
		this.cacheTimestamp = System.currentTimeMillis();
	}

	/**
	 * Returns the actual item.
	 * 
	 * @return
	 */
	public T getItem() {
		return item;
	}

	/**
	 * Returns the timestamp of creating this cache entry.
	 * 
	 * @return
	 */
	public long getCacheTimestamp() {
		return cacheTimestamp;
	}

	/**
	 * Checks whether this item has exceeded the given time to live.
	 * 
	 * @param timeToLiveSeconds
	 *            time to live in seconds
	 * @return
	 */
	public boolean hasExceeded(int timeToLiveSeconds) {
		return System.currentTimeMillis() > cacheTimestamp + timeToLiveSeconds * 1000L;
	}

}
