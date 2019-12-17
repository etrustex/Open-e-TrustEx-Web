package eu.europa.ec.etrustex.webaccess.security;

import org.w3c.dom.Node;

import java.io.InputStream;
import java.security.Key;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * @author: tenapju
 * @project: ETX
 */
public interface CertificateService {
    String P2P_CRYPTO_KEYSTORE_ALIAS = "p2p";

    X509Certificate extractSignatureCertificateFromRootNode(Node rootNode) throws Exception;

    X509Certificate getCertificate(byte[] x509Certificate) throws Exception;

    <T extends Key> List<KeyStoreEntry<T>> getKeyStoreEntries(InputStream keyStoreStream, String keyStoreType, char[] password, Class<T> keyType) throws Exception;
}
