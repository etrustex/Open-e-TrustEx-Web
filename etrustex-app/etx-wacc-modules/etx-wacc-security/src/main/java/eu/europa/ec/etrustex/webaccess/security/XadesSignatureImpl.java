package eu.europa.ec.etrustex.webaccess.security;

import eu.europa.ec.etrustex.webaccess.utils.XMLHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import xades4j.XAdES4jException;
import xades4j.production.*;
import xades4j.properties.DataObjectDesc;
import xades4j.providers.AlgorithmsProviderEx;
import xades4j.providers.CertificateValidationProvider;
import xades4j.providers.KeyingDataProvider;
import xades4j.providers.impl.DefaultAlgorithmsProviderEx;
import xades4j.providers.impl.DirectKeyingDataProvider;
import xades4j.verification.SignatureSpecificVerificationOptions;
import xades4j.verification.XAdESVerificationResult;
import xades4j.verification.XadesVerificationProfile;
import xades4j.verification.XadesVerifier;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * @author: micleva
 * @date: 7/18/12 1:45 PM
 * @project: ETX
 */
class XadesSignatureImpl implements SignatureService {

    XadesSignatureImpl() {
        org.apache.xml.security.Init.init();
    }

    @Override
    public Element signData(String dataToSign, PrivateKey privateKey, X509Certificate x509Certificate) throws Exception {

        KeyingDataProvider kp = new DirectKeyingDataProvider(x509Certificate, privateKey);
        AlgorithmsProviderEx algorithmsProviderEx = new DefaultAlgorithmsProviderEx();
        XadesSigner signer = new XadesBesSigningProfile(kp).withAlgorithmsProviderEx(algorithmsProviderEx).newSigner();

        Document doc = XMLHelper.getEmptyDocument();

        DataObjectDesc dataObj = new AnonymousDataObjectReference(dataToSign.getBytes());

        SignedDataObjects signedDataObj = new SignedDataObjects(dataObj);
        XadesSignatureResult xadesSignatureResult = signer.sign(signedDataObj, doc);

        return xadesSignatureResult.getSignature().getElement();
    }

    @Override
    public boolean verifySignature(X509Certificate certificate, String signedData, Element signature) throws Exception {

        CertificateValidationProvider certValidator = new DirectCertificateValidationProvider(certificate);
        XadesVerificationProfile profile = new XadesVerificationProfile(certValidator);

        SignatureSpecificVerificationOptions options = new SignatureSpecificVerificationOptions().useDataForAnonymousReference(signedData.getBytes());

        XadesVerifier verifier = profile.newVerifier();
        XAdESVerificationResult verificationResult = null;
        try {
            verificationResult = verifier.verify(signature, options);
        } catch (XAdES4jException e) {
            //the verification might fail
        }
        return verificationResult != null;
    }

}
