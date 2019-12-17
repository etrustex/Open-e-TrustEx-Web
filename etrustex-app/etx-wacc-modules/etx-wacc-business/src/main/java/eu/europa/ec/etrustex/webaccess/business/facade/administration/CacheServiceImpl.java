package eu.europa.ec.etrustex.webaccess.business.facade.administration;

import eu.europa.ec.etrustex.webaccess.business.administration.CacheService;
import net.sf.ehcache.CacheManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    private static final Logger logger = Logger.getLogger(CacheServiceImpl.class);

    private List<CacheResetListener> cacheResetListener = new ArrayList<>();

    @Override
    @Transactional
    public void resetCache() {
        for (CacheManager cm : CacheManager.ALL_CACHE_MANAGERS) {
            String name = cm.getName();
            logger.info("reset cache " + name);
            cm.clearAll();
        }

        notifyAllCacheResetListener();
    }

    protected synchronized void notifyAllCacheResetListener() {
        for (CacheResetListener resetListener : cacheResetListener) {
            resetListener.onCacheReset();
        }
    }

    public synchronized void registerCacheResetListener(CacheResetListener listener) {
        cacheResetListener.add(listener);
    }
}
