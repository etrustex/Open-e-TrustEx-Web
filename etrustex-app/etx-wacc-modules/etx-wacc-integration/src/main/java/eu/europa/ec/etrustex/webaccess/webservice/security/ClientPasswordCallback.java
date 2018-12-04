package eu.europa.ec.etrustex.webaccess.webservice.security;

import org.apache.log4j.Logger;
import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientPasswordCallback implements CallbackHandler {
    private static final Logger logger = Logger.getLogger(ClientPasswordCallback.class);
    public static final String SIGNATURE_USERNAME_KEY = "signature.username";
    public static final String SIGNATURE_PASSWORD_KEY = "signature.password";
    private Properties properties;


    public ClientPasswordCallback(Properties properties) {
        logger.debug("initializing ClientPasswordCallback with properties");
        this.properties = properties;
    }

    public ClientPasswordCallback(String cryptoFile) {
        logger.debug("initializing ClientPasswordCallback");

        properties = new Properties();
        try (InputStream is = getClass().getResourceAsStream(cryptoFile)) {
            if (is != null) {
                properties.load(is);
                logger.debug("initializing ClientPasswordCallback: " + properties.getProperty(SIGNATURE_USERNAME_KEY) + ", " + properties.getProperty(SIGNATURE_PASSWORD_KEY));
            } else {
                logger.debug("callback is is null");
            }
        } catch (IOException e) {
            logger.warn("error loading crypto file " + cryptoFile + ": " + e.getMessage());
        }
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        logger.debug("handle callback with ClientPasswordCallback with: " + properties.getProperty(SIGNATURE_USERNAME_KEY) + ", " + properties.getProperty(SIGNATURE_PASSWORD_KEY));
        for (Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback) {
                WSPasswordCallback passwordCallback = (WSPasswordCallback) callback;
                if (passwordCallback.getIdentifier().equals(properties.getProperty(SIGNATURE_USERNAME_KEY))) {
                    passwordCallback.setPassword(properties.getProperty(SIGNATURE_PASSWORD_KEY));
                    return;
                }
            }
        }
    }
}
