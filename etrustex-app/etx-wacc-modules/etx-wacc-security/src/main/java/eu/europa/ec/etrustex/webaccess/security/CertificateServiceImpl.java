package eu.europa.ec.etrustex.webaccess.security;


import org.apache.log4j.Logger;
import org.apache.xml.security.utils.Constants;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author: tenapju
 * @project: ETX
 */
class CertificateServiceImpl implements CertificateService {
    private static final Logger logger = Logger.getLogger(CertificateServiceImpl.class.getName());

    public static boolean verify(X509Certificate cert, PublicKey key)
            throws CertificateException, InvalidKeyException,
            NoSuchAlgorithmException, NoSuchProviderException {

        String sigAlg = cert.getSigAlgName();
        String keyAlg = key.getAlgorithm();
        sigAlg = sigAlg != null ? sigAlg.trim().toUpperCase() : "";
        keyAlg = keyAlg != null ? keyAlg.trim().toUpperCase() : "";
        if (keyAlg.length() >= 2 && sigAlg.endsWith(keyAlg)) {
            try {
                cert.verify(key);
                return true;
            } catch (SignatureException se) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public X509Certificate extractSignatureCertificateFromRootNode(Node rootNode) throws Exception {

        X509Certificate result = null;
        if (rootNode != null && rootNode instanceof Element) {
            Element rootElement = (Element) rootNode;
            NodeList certificateNodeList = rootElement.getElementsByTagNameNS(Constants.SignatureSpecNS, Constants._TAG_X509CERTIFICATE);

            if (certificateNodeList != null && certificateNodeList.getLength() == 1) {
                //get the certificate element content
                String certAsText = certificateNodeList.item(0).getTextContent();

                if (certAsText != null) {
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes = decoder.decodeBuffer(certAsText);
                    result = getCertificate(decodedBytes);
                }
            }
        }
        return result;
    }

    @Override
    public X509Certificate getCertificate(byte[] x509Certificate) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        return (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(x509Certificate));
    }

    @Override
    public <T extends Key> List<KeyStoreEntry<T>> getKeyStoreEntries(InputStream keyStoreStream, String keyStoreType, char[] password, Class<T> keyType) throws Exception {
        //take advantage of automatic tree-Sorting mechanism
        Map<String, KeyStoreEntry<T>> sortedEntries = new TreeMap<>();

        KeyStore keyStore = KeyStore.getInstance(keyStoreType, EtxSecurityProvider.getInstance().getSunJSSEProvider());
        keyStore.load(keyStoreStream, password);

        for (Enumeration<String> aliases = keyStore.aliases(); aliases.hasMoreElements(); ) {
            String alias = aliases.nextElement();

            X509Certificate certificate = (X509Certificate) keyStore.getCertificate(alias);

            Key key = keyStore.getKey(alias, password);
            PublicKey publicCertificateKey = certificate.getPublicKey();

            if (key != null && keyType.isAssignableFrom(key.getClass())) {
                KeyStoreEntry<T> entry = buildCertificateEntry(certificate, alias, (T) key);
                sortedEntries.put(entry.getAlias(), entry);
            } else if (keyType.isAssignableFrom(publicCertificateKey.getClass())) {
                KeyStoreEntry<T> entry = buildCertificateEntry(certificate, alias, (T) publicCertificateKey);
                sortedEntries.put(entry.getAlias(), entry);
            }
        }

        return new ArrayList<>(sortedEntries.values());
    }

    private <T extends Key> KeyStoreEntry<T> buildCertificateEntry(X509Certificate certificate, String alias, T key) throws KeyStoreException {
        KeyStoreEntry<T> entry = new KeyStoreEntry<>();
        entry.setAlias(alias);
        entry.setX509Certificate(certificate);
        entry.setKeyEntry(key);
        return entry;
    }
}
