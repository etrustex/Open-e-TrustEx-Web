package eu.europa.ec.etrustex.functionaltest;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.security.*;
import java.security.cert.X509Certificate;

/**
 * @author: micleva
 * @date: 1/3/13 11:42 AM
 * @project: ETX
 */
public class CertificateHelperTest {

    private final Logger logger = Logger.getLogger(CertificateHelperTest.class);

    /**
     * By standard, the certificate is valid for 12 hours
     */
    public static final Integer CERTIFICATE_VALIDITY_TIME = 12 * 60 * 60 * 1000;

    private String keyStorePath;

    private String keyStorePass;

    private String entryAlias;

    private String aliasPass;

    private X509Certificate x509Certificate;
    private PrivateKey privateKey;

    private KeyPairGenerator generator;

    public CertificateHelperTest(String keyStorePath, String keyStorePass, String entryAlias, String aliasPass) {
        this.keyStorePath = keyStorePath;
        this.keyStorePass = keyStorePass;
        this.entryAlias = entryAlias;
        this.aliasPass = aliasPass;

        loadSignatoryCertificate();
    }

    private void loadSignatoryCertificate() {
        Security.addProvider(new BouncyCastleProvider());

        Resource keyStoreResource = new ClassPathResource(keyStorePath);

        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("JKS");

            keyStore.load(keyStoreResource.getInputStream(), keyStorePass.toCharArray());

            x509Certificate = (X509Certificate) keyStore.getCertificate(entryAlias);
            privateKey = (PrivateKey) keyStore.getKey(entryAlias, aliasPass.toCharArray());

        } catch (Exception e) {
            logger.error("Unable to lead the certificate and the private key for certificate file: " + keyStorePath + " and entry alias: " + entryAlias, e);
        }

        try {
            generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Unable to lead the RSA Key generator!", e);
        }
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public X509Certificate getX509Certificate() {
        return x509Certificate;
    }
}
