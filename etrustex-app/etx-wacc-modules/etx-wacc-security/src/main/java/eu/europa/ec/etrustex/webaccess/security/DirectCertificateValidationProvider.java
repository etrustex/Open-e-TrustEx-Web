package eu.europa.ec.etrustex.webaccess.security;

import xades4j.providers.CertificateValidationException;
import xades4j.providers.CertificateValidationProvider;
import xades4j.providers.ValidationData;
import xades4j.verification.UnexpectedJCAException;

import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author: micleva
 * @date: 7/31/12 2:00 PM
 * @project: ETX
 */
class DirectCertificateValidationProvider implements CertificateValidationProvider {
    private List<X509Certificate> certificateList;

    public DirectCertificateValidationProvider(X509Certificate certificate) {
        certificateList = Collections.singletonList(certificate);
    }

    @Override
    public ValidationData validate(X509CertSelector certSelector,
                                   Date validationDate,
                                   Collection<X509Certificate> otherCerts) throws
            CertificateValidationException,
            UnexpectedJCAException {
        return new ValidationData(certificateList);
    }
}
