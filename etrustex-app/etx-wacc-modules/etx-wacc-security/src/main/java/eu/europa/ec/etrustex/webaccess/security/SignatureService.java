package eu.europa.ec.etrustex.webaccess.security;

import org.w3c.dom.Element;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * @author: micleva
 * @date: 7/18/12 1:22 PM
 * @project: ETX
 */
public interface SignatureService {

    /**
     * Signs the data and return the signature element ONLY.
     * The content signed is not part of the element returned!
     *
     * @param dataToSign      the string containing all the data to be signed
     * @param privateKey      the key to be used to retrieve build the signature
     * @param x509Certificate the certificate that can be used to decrypt the signature data
     * @return the element containing the signature details
     */
    public Element signData(String dataToSign, PrivateKey privateKey, X509Certificate x509Certificate) throws Exception;

    /**
     * Verifies the signature and returns true if the verification passes, false otherwise
     *
     * @param certificate the certificate used for signature
     * @return true if the signature validates, false otherwise
     */
    public boolean verifySignature(X509Certificate certificate, String signedData, Element signature) throws Exception;
}
