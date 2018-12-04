package eu.europa.ec.etrustex.webaccess.web.i18n;

import eu.europa.ec.etrustex.webaccess.business.api.EtxUtilFacade;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.LabelTranslation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.*;

public class DataSourceMessageSource extends AbstractMessageSource {

    private EtxUtilFacade etxUtilFacade;

    private static final Logger logger = Logger.getLogger(DataSourceMessageSource.class);

    private Map<Locale, CacheEntry<Properties>> localesMap;

    private CacheEntry<Configuration> config;

    private Locale defaultLocale = Locale.ENGLISH;

    private int timeToLiveSeconds = 900; // standard: 15 min

    @Autowired
    private ConfigurationService configurationService;

    @PostConstruct
    public void init() {
        config = new CacheEntry<>(configurationService.getConfiguration());
    }

    public DataSourceMessageSource() {
        localesMap = Collections.synchronizedMap(new HashMap<Locale, CacheEntry<Properties>>());
    }

    @Autowired
    public void setEtxUtilFacade(EtxUtilFacade etxUtilFacade) {
        this.etxUtilFacade = etxUtilFacade;
    }

    /**
     * Sets the locale to be used by standard.
     *
     * @param defaultLocale
     */
    public void setDefaultLocale(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    /**
     * Sets the time to live in seconds for every set of localised
     * messages that is loaded. Use negative values for an infinite
     * time to live.
     *
     * @param timeToLiveSeconds
     */
    public void setTimeToLiveSeconds(int timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        final String message = getPropertyMessage(code, locale);
        return (message == null) ?
                null :
                new MessageFormat(message, locale);
    }

    /**
     * Resolve a message without involving a MessageFormat
     */
    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return getPropertyMessage(code, locale);
    }

    /**
     * Returns a localized message corresponding to the received code.
     * If code is not found among the properties, special formatted message is returned
     *
     * @param code
     * @param locale
     * @return String
     */
    private String getPropertyMessage(String code, Locale locale) {
        Properties properties = getPropertiesForLocale(locale);
        String message = null;

        if (properties.containsKey(code)) {
            message = properties.getProperty(code);
        }

        if (message == null && defaultLocale != null && !defaultLocale.equals(locale)) {
            reloadConfigIfExpired();
            if (config.getItem().isLookupFailsDefaultEnabled()) {
                properties = getPropertiesForLocale(defaultLocale);
                if (properties.containsKey(code)) {
                    message = properties.getProperty(code);
                }
            }
        }
        return message;
    }

    private void reloadConfigIfExpired() {
        if (timeToLiveSeconds > -1 && config.hasExceeded(timeToLiveSeconds)) {
            config = new CacheEntry<>(configurationService.getConfiguration());
        }
    }

    /**
     * Checks if translations are available in the required locale.
     * If they are not available, they are retrieved from data source and stored locally
     *
     * @param locale
     */
    private Properties getPropertiesForLocale(Locale locale) {
        CacheEntry<Properties> cachedProperties = localesMap.get(locale);

        if (cachedProperties == null ||
                timeToLiveSeconds > -1 && cachedProperties.hasExceeded(timeToLiveSeconds)) {
            logger.debug("Loading label translations for locale: " + locale);
            List<LabelTranslation> labelTranslationEntities = null;
            try {
                labelTranslationEntities = etxUtilFacade.findLabelTranslationsByLocale(locale);
            } catch (Exception e) {
                logger.warn("Cannot load the label translations for locale: " + locale);
                throw new RuntimeException(e);
            }
            Properties props = new Properties();
            if (labelTranslationEntities != null) {
                if (labelTranslationEntities.isEmpty()) {
                    cachedProperties = new CacheEntry<>(props);
                    localesMap.put(locale, cachedProperties);
                } else {
                    for (LabelTranslation translation : labelTranslationEntities) {
                        if (translation != null) {
                            if (translation.getMessage() != null) {
                                props.put(translation.getKey(), translation.getMessage());
                            }
                        }
                    }
                    cachedProperties = new CacheEntry<>(props);
                    localesMap.put(locale, cachedProperties);
                }
            }
        }

        return cachedProperties.getItem();
    }
}
