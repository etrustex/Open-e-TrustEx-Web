package eu.europa.ec.etrustex.webaccess.webservice.converter;

import ec.schema.xsd.commonaggregatecomponents_2.CertificateType;
import ec.schema.xsd.commonaggregatecomponents_2.InterchangeAgreementType;
import eu.europa.ec.etrustex.webaccess.model.ConfidentialityCode;
import eu.europa.ec.etrustex.webaccess.model.IntegrityCode;
import eu.europa.ec.etrustex.webaccess.webservice.model.CertificateCode;
import eu.europa.ec.etrustex.webaccess.webservice.model.RawNodeIcaDetails;

import java.util.List;

public class NodeInterchangeAgreementTypeConverter {

    public static RawNodeIcaDetails toRawNodeIcaDetailsVo(InterchangeAgreementType interchangeAgreementType) {

        RawNodeIcaDetails rawNodeIcaDetails = null;

        if (interchangeAgreementType != null) {

            CertificateType recEncipheringCertificate = fetchEncipheringCertificate(interchangeAgreementType);
            ConfidentialityCode confidentialityCode = fetchConfidentialityCode(interchangeAgreementType);
            IntegrityCode integrityCode = fetchIntegrityCode(interchangeAgreementType);

            String x509SubjectName = null;
            byte[] encodedCertificate = null;
            if (recEncipheringCertificate != null) {
                if (recEncipheringCertificate.getX509SubjectName() != null) {
                    x509SubjectName = recEncipheringCertificate.getX509SubjectName().getValue();
                }
                if (recEncipheringCertificate.getX509Certificate() != null) {
                    encodedCertificate = recEncipheringCertificate.getX509Certificate().getValue();
                }
            }
            rawNodeIcaDetails = new RawNodeIcaDetails(encodedCertificate, x509SubjectName, confidentialityCode, integrityCode);
        }
        return rawNodeIcaDetails;
    }

    private static IntegrityCode fetchIntegrityCode(InterchangeAgreementType matchingInterchangeAgreement) {
        if (matchingInterchangeAgreement.getSecurityInformation() != null) {
            return IntegrityCode.forCode(matchingInterchangeAgreement.getSecurityInformation().getIntegrityLevelCode().getValue());
        }
        return null;
    }

    private static ConfidentialityCode fetchConfidentialityCode(InterchangeAgreementType matchingInterchangeAgreement) {
        if (matchingInterchangeAgreement.getSecurityInformation() != null) {
            return ConfidentialityCode.forCode(matchingInterchangeAgreement.getSecurityInformation().getConfidentialityLevelCode().getValue());
        }
        return null;
    }

    private static CertificateType fetchEncipheringCertificate(InterchangeAgreementType matchingInterchangeAgreement) {
        if (matchingInterchangeAgreement.getSecurityInformation() != null) {
            List<CertificateType> listOfCertificateTypes = matchingInterchangeAgreement.getSecurityInformation().getReceiverPartyCertificate();
            if (listOfCertificateTypes != null && !listOfCertificateTypes.isEmpty()) {
                for (CertificateType certificate : listOfCertificateTypes) {
                    if (certificate.getKeyUsageCode().getValue().equals(CertificateCode.KEY_ENCIPHERMENT.name())) {
                        return certificate;
                    }
                }
            }
        }
        return null;
    }
}
