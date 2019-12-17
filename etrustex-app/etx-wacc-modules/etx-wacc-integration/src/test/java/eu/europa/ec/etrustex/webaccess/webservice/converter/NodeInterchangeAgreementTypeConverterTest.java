package eu.europa.ec.etrustex.webaccess.webservice.converter;

import ec.schema.xsd.commonaggregatecomponents_2.CertificateType;
import ec.schema.xsd.commonaggregatecomponents_2.InterchangeAgreementType;
import ec.schema.xsd.commonaggregatecomponents_2.SecurityInformationType;
import ec.schema.xsd.commonbasiccomponents_1.ConfidentialityLevelCodeType;
import ec.schema.xsd.commonbasiccomponents_1.IntegrityLevelCodeType;
import ec.schema.xsd.commonbasiccomponents_1.KeyUsageCodeType;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.ConfidentialityCode;
import eu.europa.ec.etrustex.webaccess.model.IntegrityCode;
import eu.europa.ec.etrustex.webaccess.webservice.model.CertificateCode;
import eu.europa.ec.etrustex.webaccess.webservice.model.RawNodeIcaDetails;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.EndpointIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TextType;
import org.junit.Test;
import un.unece.uncefact.data.specification.unqualifieddatatypesschemamodule._2.BinaryObjectType;

/**
 * @author: micleva
 * @date: 10/30/13 9:25 AM
 * @project: ETX
 */
public class NodeInterchangeAgreementTypeConverterTest extends AbstractTest {

    @Test
    public void test_toNodeIca_should_parseConfidentialityAndCertificateOk() throws Exception {
        String sender = "aaa";
        String receiver = "bbb";
        byte[] certificate = new byte[]{11, 22, 33};
        String subjectName = "subject";


        InterchangeAgreementType interchangeAgreementType = new InterchangeAgreementType();
        interchangeAgreementType.setSenderParty(getParty(sender));
        interchangeAgreementType.setReceiverParty(getParty(receiver));
        //add the security information details
        SecurityInformationType securityInformationType = new SecurityInformationType();
        securityInformationType.setConfidentialityLevelCode(buildConfidentialityLevelCode(String.valueOf(ConfidentialityCode.LIMITED_HIGH.getCode())));
        securityInformationType.setIntegrityLevelCode(buildIntegrityLevelCode("6666"));
        securityInformationType.getReceiverPartyCertificate().add(buildCertificate(CertificateCode.KEY_ENCIPHERMENT.name(), certificate, subjectName));
        interchangeAgreementType.setSecurityInformation(securityInformationType);

        //DO THE ACTUAL CALL
        RawNodeIcaDetails rawNodeIcaDetails = NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(interchangeAgreementType);

        assertThat(rawNodeIcaDetails, is(not(nullValue())));
        assertThat(rawNodeIcaDetails.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_HIGH));
        assertThat(rawNodeIcaDetails.getIntegrityCode(), is(nullValue()));
        assertThat(rawNodeIcaDetails.getX509SubjectName(), is(subjectName));
        assertThat(rawNodeIcaDetails.getEncodedCertificate(), is(certificate));
    }

    @Test
    public void test_toNodeIca_should_parseConfidentialityAndCertificateOk_when_encipheringCertificateOK_ConfidentialityOK() throws Exception {
        String sender = "aaa";
        String receiver = "bbb";
        byte[] certificate = new byte[]{11, 22, 33};
        String subjectName = "subject";

        InterchangeAgreementType interchangeAgreementType = new InterchangeAgreementType();
        interchangeAgreementType.setSenderParty(getParty(sender));
        interchangeAgreementType.setReceiverParty(getParty(receiver));
        //add the security information details
        SecurityInformationType securityInformationType = new SecurityInformationType();
        securityInformationType.setConfidentialityLevelCode(buildConfidentialityLevelCode(String.valueOf(ConfidentialityCode.LIMITED_HIGH.getCode())));
        securityInformationType.setIntegrityLevelCode(buildIntegrityLevelCode("6666"));
        securityInformationType.getReceiverPartyCertificate().add(buildCertificate(CertificateCode.KEY_ENCIPHERMENT.name(), certificate, subjectName));
        interchangeAgreementType.setSecurityInformation(securityInformationType);

        RawNodeIcaDetails rawNodeIcaDetails = NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(interchangeAgreementType);

        assertThat(rawNodeIcaDetails, is(not(nullValue())));
        assertThat(rawNodeIcaDetails.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_HIGH));
        assertThat(rawNodeIcaDetails.getIntegrityCode(), is(nullValue()));
        assertThat(rawNodeIcaDetails.getX509SubjectName(), is(subjectName));
        assertThat(rawNodeIcaDetails.getEncodedCertificate(), is(certificate));
    }

    @Test
    public void test_toNodeIca_should_parseConfidentialityAndCertificateOk_when_confidentialityOK_noEncryption() throws Exception {
        String sender = "aaa";
        String receiver = "bbb";

        InterchangeAgreementType interchangeAgreementType = new InterchangeAgreementType();
        interchangeAgreementType.setSenderParty(getParty(sender));
        interchangeAgreementType.setReceiverParty(getParty(receiver));
        //add the security information details
        SecurityInformationType securityInformationType = new SecurityInformationType();
        securityInformationType.setConfidentialityLevelCode(buildConfidentialityLevelCode(String.valueOf(ConfidentialityCode.LIMITED_BASIC.getCode())));
        securityInformationType.setIntegrityLevelCode(buildIntegrityLevelCode(String.valueOf(IntegrityCode.MODERATE.getCode())));
        interchangeAgreementType.setSecurityInformation(securityInformationType);

        RawNodeIcaDetails rawNodeIcaDetails = NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(interchangeAgreementType);

        assertThat(rawNodeIcaDetails, is(not(nullValue())));
        assertThat(rawNodeIcaDetails.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_BASIC));
        assertThat(rawNodeIcaDetails.getIntegrityCode(), is(IntegrityCode.MODERATE));
        assertThat(rawNodeIcaDetails.getX509SubjectName(), is(nullValue()));
        assertThat(rawNodeIcaDetails.getEncodedCertificate(), is(nullValue()));
    }

    @Test
    public void test_toNodeIca_should_parseConfidentialityAndCertificateOk_when_encipheringCertificateOK_confidentialityNOT() throws Exception {
        String sender = "aaa";
        String receiver = "bbb";
        byte[] certificate = new byte[]{11, 22, 33};

        InterchangeAgreementType interchangeAgreementType = new InterchangeAgreementType();
        interchangeAgreementType.setSenderParty(getParty(sender));
        interchangeAgreementType.setReceiverParty(getParty(receiver));
        //add the security information details
        SecurityInformationType securityInformationType = new SecurityInformationType();
        securityInformationType.setConfidentialityLevelCode(buildConfidentialityLevelCode("bla bla bla"));
        securityInformationType.setIntegrityLevelCode(buildIntegrityLevelCode(String.valueOf(IntegrityCode.MODERATE.getCode())));
        securityInformationType.getReceiverPartyCertificate().add(buildCertificate(CertificateCode.KEY_ENCIPHERMENT.name(), certificate, null));
        interchangeAgreementType.setSecurityInformation(securityInformationType);

        RawNodeIcaDetails rawNodeIcaDetails = NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(interchangeAgreementType);

        assertThat(rawNodeIcaDetails, is(not(nullValue())));
        assertThat(rawNodeIcaDetails.getConfidentialityCode(), is(nullValue()));
        assertThat(rawNodeIcaDetails.getIntegrityCode(), is(IntegrityCode.MODERATE));
        assertThat(rawNodeIcaDetails.getX509SubjectName(), is(nullValue()));
        assertThat(rawNodeIcaDetails.getEncodedCertificate(), is(certificate));
    }

    @Test
    public void test_toNodeIca_should_parseConfidentialityAndCertificateOk_when_noEncipheringCertificate_confidentialityOK() throws Exception {
        String sender = "aaa";
        String receiver = "bbb";

        InterchangeAgreementType interchangeAgreementType = new InterchangeAgreementType();
        interchangeAgreementType.setSenderParty(getParty(sender));
        interchangeAgreementType.setReceiverParty(getParty(receiver));
        //add the security information details
        SecurityInformationType securityInformationType = new SecurityInformationType();
        securityInformationType.setConfidentialityLevelCode(buildConfidentialityLevelCode(String.valueOf(ConfidentialityCode.LIMITED_HIGH.getCode())));
        securityInformationType.setIntegrityLevelCode(new IntegrityLevelCodeType());
        securityInformationType.getReceiverPartyCertificate().add(buildCertificate("la la la", null, null));
        interchangeAgreementType.setSecurityInformation(securityInformationType);

        RawNodeIcaDetails rawNodeIcaDetails = NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(interchangeAgreementType);

        assertThat(rawNodeIcaDetails, is(not(nullValue())));
        assertThat(rawNodeIcaDetails.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_HIGH));
        assertThat(rawNodeIcaDetails.getIntegrityCode(), is(nullValue()));
        assertThat(rawNodeIcaDetails.getX509SubjectName(), is(nullValue()));
        assertThat(rawNodeIcaDetails.getEncodedCertificate(), is(nullValue()));
    }

    @Test
    public void test_toNodeIca_should_parseOk_when_noSecurityInformation() throws Exception {
        String sender = "aaa";
        String receiver = "bbb";

        InterchangeAgreementType interchangeAgreementType = new InterchangeAgreementType();
        interchangeAgreementType.setSenderParty(getParty(sender));
        interchangeAgreementType.setReceiverParty(getParty(receiver));

        RawNodeIcaDetails rawNodeIcaDetails = NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(interchangeAgreementType);

        assertThat(rawNodeIcaDetails, is(not(nullValue())));
        assertThat(rawNodeIcaDetails.getConfidentialityCode(), is(nullValue()));
        assertThat(rawNodeIcaDetails.getIntegrityCode(), is(nullValue()));
        assertThat(rawNodeIcaDetails.getX509SubjectName(), is(nullValue()));
        assertThat(rawNodeIcaDetails.getEncodedCertificate(), is(nullValue()));
    }

    private IntegrityLevelCodeType buildIntegrityLevelCode(String code) {
        IntegrityLevelCodeType integrityLevelCodeType = new IntegrityLevelCodeType();
        integrityLevelCodeType.setValue(code);
        return integrityLevelCodeType;
    }

    private ConfidentialityLevelCodeType buildConfidentialityLevelCode(String confidentialityCode) {
        ConfidentialityLevelCodeType confidentialityLevelCodeType = new ConfidentialityLevelCodeType();
        confidentialityLevelCodeType.setValue(confidentialityCode);
        return confidentialityLevelCodeType;
    }

    private CertificateType buildCertificate(String keyUsageCode, byte[] certificate, String subjectName) {
        CertificateType certificateType = new CertificateType();
        KeyUsageCodeType keyUsageCodeType = new KeyUsageCodeType();
        keyUsageCodeType.setValue(keyUsageCode);
        certificateType.setKeyUsageCode(keyUsageCodeType);

        BinaryObjectType X509Certificate = new BinaryObjectType();
        X509Certificate.setValue(certificate);
        certificateType.setX509Certificate(X509Certificate);

        TextType X509SubjectName = new TextType();
        X509SubjectName.setValue(subjectName);
        certificateType.setX509SubjectName(X509SubjectName);

        return certificateType;
    }

    private PartyType getParty(String sender) {
        PartyType party = new PartyType();
        party.setEndpointID(new EndpointIDType());
        party.getEndpointID().setValue(sender);
        return party;
    }
}
