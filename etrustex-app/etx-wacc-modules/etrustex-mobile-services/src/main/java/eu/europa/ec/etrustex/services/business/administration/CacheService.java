package eu.europa.ec.etrustex.services.business.administration;

import eu.europa.ec.etrustex.services.business.facade.administration.CacheResetListener;

public interface CacheService {
    /**
     * Performs cleanup for all available cache regions. Reloads node configuration.
     */
    void resetCache();

    void registerCacheResetListener(CacheResetListener listener);
}
