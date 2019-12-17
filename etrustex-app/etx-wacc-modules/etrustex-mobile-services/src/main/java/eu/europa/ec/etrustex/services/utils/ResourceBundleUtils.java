package eu.europa.ec.etrustex.services.utils;

import org.apache.log4j.Logger;

import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class for getting translations for given key.
 */
public class ResourceBundleUtils {
    private static final Logger logger = Logger.getLogger(ResourceBundleUtils.class);
    private static final String BUNDLE_BASE_NAME = "i18n/messages";
    private static final ResourceBundle.Control control = new UTF8Control();
    private static boolean lookupFailsDefaultEnabled = false;

    public static void init(final String localeCode, boolean defaultEnabled) {
        lookupFailsDefaultEnabled = defaultEnabled;

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                try {
                    Locale.setDefault(new Locale(localeCode));
                    Locale.setDefault(Locale.Category.DISPLAY, new Locale(localeCode));
                    Locale.setDefault(Locale.Category.FORMAT, Locale.US);
                } catch (AccessControlException ex) {
                    logger.error(ex);
                }
                return null;
            }
        });
    }

    /**
     * Gets the associated translation message for given key.
     *
     * @param key The key.
     * @return The message if exists, the input key otherwise.
     */
    public static String message(String key) {
        try {
            return ResourceBundle.getBundle(BUNDLE_BASE_NAME, control).getString(key);
        } catch (Exception ex) {
            logger.warn("Missing message for key: " + key + ", language: " + Locale.getDefault().getDisplayLanguage());
            logger.warn("Exception: " + ex);
            if (lookupFailsDefaultEnabled && !Locale.getDefault().equals(Locale.ENGLISH)) {
                return messageEn(key);
            } else {
                return key;
            }
        }
    }

    /**
     * Gets english translation.
     *
     * @param key The key.
     * @return The message if exists, the input key otherwise.
     */
    protected static String messageEn(String key) {
        try {
            return ResourceBundle.getBundle(BUNDLE_BASE_NAME, Locale.ENGLISH, control).getString(key);
        } catch (Exception ex) {
            logger.warn("Missing message for key: " + key + ", language: " + Locale.ENGLISH);
            logger.warn("Exception: " + ex);
            return key;
        }
    }

    /**
     * Gets the associated formatted message for given key and arguments.
     *
     * @param key  The key.
     * @param args The format arguments.
     * @return The formatted message if exists, the input key otherwise.
     */
    public static String message(String key, Object... args) {
        String message = message(key);
        if (message != null && !message.equals(key)) {
            try {
                return MessageFormat.format(message, args);
            } catch (Exception ex) {
                logger.warn("Missing message or incompatible format for key: " + key);
                logger.warn("Exception: " + ex);
                return key;
            }
        }
        return message;
    }
}
