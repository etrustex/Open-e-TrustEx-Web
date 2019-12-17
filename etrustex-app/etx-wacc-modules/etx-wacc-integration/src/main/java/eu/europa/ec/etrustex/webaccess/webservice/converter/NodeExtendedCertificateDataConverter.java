package eu.europa.ec.etrustex.webaccess.webservice.converter;


import eu.europa.ec.etrustex.webaccess.model.vo.ExtendedCertificateData;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import org.apache.log4j.Logger;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class NodeExtendedCertificateDataConverter {

    private static final Logger logger = Logger.getLogger(NodeExtendedCertificateDataConverter.class.getName());

    public static ExtendedCertificateData toExtendedCertificateData(byte[] encodedCertificate, String x509SubjectName) {
        X509Certificate x509Certificate = null;
        try {
            x509Certificate = EtxSecurityProvider.getInstance().getCertificateService().getCertificate(encodedCertificate);
        } catch (Exception e) {
            logger.error("Unable to read certificate where subjectName is: " + x509SubjectName +
                    " and the encoded certificate is: " + Arrays.toString(encodedCertificate), e);
        }
        ExtendedCertificateData extendedCertificateData = null;
        if (x509Certificate != null) {
            PublicKey publicKey = x509Certificate.getPublicKey();

            extendedCertificateData = new ExtendedCertificateData(publicKey, x509Certificate.getSubjectDN().toString(),
                    x509Certificate.getIssuerDN().toString(),
                    x509Certificate.getNotBefore(),
                    x509Certificate.getNotAfter(),
                    x509Certificate.getSerialNumber().toString(),
                    x509Certificate.getSigAlgName(), String.valueOf(x509Certificate.getVersion()));
        }

        return extendedCertificateData;
    }
}
