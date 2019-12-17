package eu.europa.ec.etrustex.webaccess.security;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;


public class EtxSecurityProvider {

    private static final Logger logger = Logger.getLogger(EtxSecurityProvider.class.getName());

    public static final String SECURITY_PROVIDER = BouncyCastleProvider.PROVIDER_NAME;
    private static final String SUN_JSEE_SECURITY_PROVIDER = "SunJSSE";

    private static EtxSecurityProvider instance;
    private static Provider provider;

    private String defaultSecurityProvider;

    public static synchronized void init() {
        if (instance == null) {
            instance = new EtxSecurityProvider();
        }
    }

    private EtxSecurityProvider() {
        addProvider();
        defaultSecurityProvider = SECURITY_PROVIDER;
    }

    /**
     * Unregister security provider.
     */
    public static synchronized void destroy() {
        instance = null;
        if (provider != null) {
            for (Provider installedProvider : Security.getProviders()) {
                if (installedProvider == provider) {
                    Security.removeProvider(installedProvider.getName());
                    provider = null;
                    logger.info("etx existing security provider [" + installedProvider.getName() + "] was removed");
                    return;
                }
            }
        }
        logger.warn("no existing etx security provider [" + EtxSecurityProvider.SECURITY_PROVIDER + "] to remove");
    }

    /**
     * Add the Bouncy castle security provider
     */
    private static void addProvider() {
        Provider newProvider = new BouncyCastleProvider();
        if (Security.addProvider(newProvider) != -1) {
            provider = newProvider;
            logger.info("etx security provider [" + provider.getName() + "] was added");
        } else {
            boolean external = true;
            for (Provider provider : Security.getProviders()) {
                if (provider == EtxSecurityProvider.provider) {
                    external = false;
                    break;
                }
            }
            if (!external) {
                logger.warn("reusing already installed by etx security provider [" + provider.getName() + "]");
            } else {
                logger.warn("reusing externally installed security provider [" + EtxSecurityProvider.SECURITY_PROVIDER + "]");
            }
        }
    }

    public CryptoService getCryptoService() {
        return CryptoServiceLazyHolder.INSTANCE;
    }

    public SignatureService getSignatureService() {
        return SignatureServiceLazyHolder.INSTANCE;
    }

    public CertificateService getCertificateService() {
        return CertificateServiceLazyHolder.INSTANCE;
    }

    private static class CryptoServiceLazyHolder {
        private static CryptoService INSTANCE = new BouncyCryptoImpl();
    }

    private static class SignatureServiceLazyHolder {
        private static SignatureService INSTANCE = new XadesSignatureImpl();
    }

    private static class CertificateServiceLazyHolder {
        private static CertificateService INSTANCE = new CertificateServiceImpl();
    }

    public String getDefaultSecurityProvider() {
        return defaultSecurityProvider;
    }

    public String getSunJSSEProvider() {
        return SUN_JSEE_SECURITY_PROVIDER;
    }

    public static synchronized EtxSecurityProvider getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Please call init before trying to obtain the instance!");
        }
        return instance;
    }
}
