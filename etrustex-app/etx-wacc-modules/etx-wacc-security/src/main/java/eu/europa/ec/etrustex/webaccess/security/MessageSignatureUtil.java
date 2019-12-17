package eu.europa.ec.etrustex.webaccess.security;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.MessageSignature;
import eu.europa.ec.etrustex.webaccess.model.converters.CertificateDataConverter;
import eu.europa.ec.etrustex.webaccess.model.vo.CertificateData;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import static eu.europa.ec.etrustex.webaccess.utils.BuildCertificateData.retrieveCertificateData;
import static eu.europa.ec.etrustex.webaccess.utils.XMLHelper.stringToDocument;

public class MessageSignatureUtil {

    private final static Logger logger = Logger.getLogger(MessageSignatureUtil.class);

    static {
        EtxSecurityProvider.init();
    }

    public static MessageSignature extractSignatureData(String msgSignature, String msgSignedData, Long signatureId) {
        Element signature = null;
        X509Certificate certificate = null;
        try {
            signature = stringToDocument(msgSignature).getDocumentElement();
            certificate = EtxSecurityProvider.getInstance().getCertificateService().extractSignatureCertificateFromRootNode(signature);
        } catch (Exception e) {
            logger.warn("Loading of certificate failed", e);
        }

        boolean isSignatureValid = false;
        boolean isExpired = false;
        CertificateData certificateData = null;

        if (certificate != null) {
            certificateData = retrieveCertificateData(certificate);

            try {
                certificate.checkValidity();
            } catch (CertificateExpiredException | CertificateNotYetValidException e) {
                isExpired = true;
            }

            if (!isExpired && signature != null) {
                try {
                    isSignatureValid = EtxSecurityProvider.getInstance().getSignatureService().verifySignature(certificate, msgSignedData, signature);
                } catch (Exception e) {
                    logger.warn("Signature verification failed", e);
                    isSignatureValid = false;
                }
            }
        }

        MessageSignature messageSignature = new MessageSignature();
        messageSignature.setSignature(msgSignature);
        messageSignature.setSignedData(msgSignedData);
        messageSignature.setCertificate(CertificateDataConverter.getInstance().convertToDatabaseColumn(certificateData));
        messageSignature.setSignatureValid(isSignatureValid);
        messageSignature.setId(signatureId);

        return messageSignature;
    }

    public static void populateMessageSignature(Message message, String signature, String signedData, Long signatureId) {
        if (message != null && signature != null && signedData != null) {
            MessageSignature messageSignature = MessageSignatureUtil.extractSignatureData(signature, signedData, signatureId);
            if (messageSignature != null) {
                List<MessageSignature> signatureList = new ArrayList<>();
                signatureList.add(messageSignature);
                message.setSignatures(signatureList);
            }
        }
    }
}
