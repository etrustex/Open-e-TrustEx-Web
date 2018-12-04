package eu.europa.ec.etrustex.webaccess.security;

import eu.europa.ec.etrustex.signature.v1.DocumentType;
import eu.europa.ec.etrustex.signature.v1.SignedBundle;
import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.utils.XMLHelper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.NamespaceContext;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @author: micleva
 * @date: 7/23/12 1:50 PM
 * @project: ETX
 */
public class XadesSignatureImplTest extends AbstractTest {

    private XadesSignatureImpl xadesSignature;

    @Override
    protected void onSetUp() {
        EtxSecurityProvider.init();
        xadesSignature = new XadesSignatureImpl();
    }

    //TODO Signing certificate only valid between SAT JUL 07 09:24:22 CEST 2012 and SUN JUL 07 09:24:22 CEST 2013
    @Test
    public void testSignData() throws Exception {
        URI fileURI = this.getClass().getResource("/testStore.p12").toURI();

        List<KeyStoreEntry<PrivateKey>> entries;
        try (InputStream inputStream = Files.newInputStream(Paths.get(fileURI))) {
            entries = EtxSecurityProvider.getInstance().getCertificateService().getKeyStoreEntries(inputStream, "PKCS12", "test123".toCharArray(), PrivateKey.class);
        }

        KeyStoreEntry<PrivateKey> signatureEntry = null;
        for (KeyStoreEntry<PrivateKey> entry : entries) {
            if (entry.getAlias().equals("key3")) {
                signatureEntry = entry;
            }
        }

        String signData = buildSignDataDocument(10);

        Element signature = xadesSignature.signData(signData, signatureEntry.getKeyEntry(), signatureEntry.getX509Certificate());

        EtxCommonMatchers.assertThat(signature, Matchers.is(Matchers.notNullValue()));

        NamespaceContext context = new NamespaceContext() {
            @Override
            public String getNamespaceURI(String prefix) {
                if ("ds".equals(prefix)) {
                    return "http://www.w3.org/2000/09/xmldsig#";
                } else if ("xades".equals(prefix)) {
                    return "http://uri.etsi.org/01903/v1.3.2#";
                }
                return null;
            }

            @Override
            public String getPrefix(String namespaceURI) {
                if ("http://www.w3.org/2000/09/xmldsig#".equals(namespaceURI)) {
                    return "ds";
                } else if ("http://uri.etsi.org/01903/v1.3.2#".equals(namespaceURI)) {
                    return "xades";
                }
                return null;
            }

            @Override
            public Iterator<String> getPrefixes(String namespaceURI) {
                HashSet<String> prefixes = new HashSet<>();
                String prefix = getPrefix(namespaceURI);
                if (prefix != null) {
                    prefixes.add(prefix);
                }
                return prefixes.iterator();
            }
        };

        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:SignedInfo", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:SignedInfo/ds:CanonicalizationMethod", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:SignedInfo/ds:SignatureMethod", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:SignedInfo/ds:Reference", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:SignedInfo/ds:Reference/ds:DigestMethod", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:SignedInfo/ds:Reference/ds:DigestValue", context));

        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:SignatureValue", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:KeyInfo", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:KeyInfo/ds:X509Data", context));

        String expectedCertificate = "\nMIIDIjCCAgqgAwIBAgIEUe+PwjANBgkqhkiG9w0BAQ0FADBTMQswCQYDVQQGEwJCRTELMAkGA1UE\r\nCAwCYmUxCzAJBgNVBAcMAkJlMQ4wDAYDVQQKDAVEaWdpdDELMAkGA1UECwwCQjExDTALBgNVBAMM\r\nBGtleTEwHhcNMTMwNzI0MDgyNzIyWhcNMjMwNzI0MDgyNzIyWjBTMQswCQYDVQQGEwJCRTELMAkG\r\nA1UECAwCYmUxCzAJBgNVBAcMAkJlMQ4wDAYDVQQKDAVEaWdpdDELMAkGA1UECwwCQjExDTALBgNV\r\nBAMMBGtleTEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDc3ejWqNefXOX+wSeSnUdf\r\nCkx68WiCxL8TQK8CAVQ/TLRsafO79qRcG5SIQXoGp+NVN+Ete9mbpy58t4thD4vB93MohmqmC+nG\r\njGmvKVsDSOWwn+TQYe5sSGrCwFBA4YWpKiDlqe9+tqOitqJESIPpGaDfmRiZ0y5gYjqsv5ck7zgL\r\n1TF83WVfxmiDWJFuy35PLWRufBLU+2u13qj3QnOFYSxfQEgqozafuWmpop1eSMiIovIh86P++oOz\r\ncvvLEwh+Sz43E515ttpef8L7aQFsfv+N1ethag5gKD+K+xLW7ml4N2dzRYjaKYW5ln+3oF3ez9jv\r\nCaSRzF944u1gG5OzAgMBAAEwDQYJKoZIhvcNAQENBQADggEBAMdivavBS+09e0gImR67+2WAH8mB\r\n7TqP9v23iIPb+gA/DF5Y6zqA/RBgp277/1dnchUMP2IOhFCNWkstnZUxHMjlQUdkuWX+nucZdrA1\r\nHenrO4lmc0uG1eZqjgQe1VoklYr+DtMNQCVW1mcnI1BAi/ucyNPa+JLlMhrLGdOFXZSioRWbyijW\r\nlx4q1mO/NVc6G7lO1oGRhF7eePWOnEMEyOGTonea5SjcchyzFzpkzDqWuwuaeQ5fwi9P486e9JFe\r\n86cZLxBl9wZxoaHnFJf7oa7i6EDrk5daBqsAS/QEcjNXagEuLnXlc+rifP1+Pd/CTpeQ+6gltwCP\r\ndDLd5ezTZB8=\n";
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:KeyInfo/ds:X509Data/ds:X509Certificate", context, Matchers.is(expectedCertificate)));

        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties/xades:SigningTime", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties/xades:SigningCertificate", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties/xades:SigningCertificate/xades:Cert", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties/xades:SigningCertificate/xades:Cert/xades:CertDigest", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties/xades:SigningCertificate/xades:Cert/xades:CertDigest/ds:DigestMethod", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties/xades:SigningCertificate/xades:Cert/xades:CertDigest/ds:DigestMethod[@Algorithm='http://www.w3.org/2001/04/xmlenc#sha256']", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties/xades:SigningCertificate/xades:Cert/xades:CertDigest/ds:DigestValue", context, Matchers.is("9Hvm4wJUSTcZe6Y1C1OULz7lYMsGYetZeYOcM5fxig8=")));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties/xades:SigningCertificate/xades:Cert/xades:IssuerSerial", context));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties/xades:SigningCertificate/xades:Cert/xades:IssuerSerial/ds:X509IssuerName", context, Matchers.is("CN=key1,OU=B1,O=Digit,L=Be,ST=be,C=BE")));
        EtxCommonMatchers.assertThat(signature, Matchers.hasXPath("/ds:Signature/ds:Object/xades:QualifyingProperties/xades:SignedProperties/xades:SignedSignatureProperties/xades:SigningCertificate/xades:Cert/xades:IssuerSerial/ds:X509SerialNumber", context, Matchers.is("1374654402")));
    }

    @Test
    public void testValidate() throws Exception {

        String signatureData = "<signedBundle xmlns=\"urn:eu:europa:ec:etrustex:signature:v1.0\"><document><id>23450</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456780</digestValue></document><document><id>23451</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456781</digestValue></document><document><id>23452</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456782</digestValue></document><document><id>23453</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456783</digestValue></document><document><id>23454</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456784</digestValue></document><document><id>23455</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456785</digestValue></document><document><id>23456</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456786</digestValue></document><document><id>23457</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456787</digestValue></document><document><id>23458</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456788</digestValue></document><document><id>23459</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456789</digestValue></document></signedBundle>";
        String signature = "<ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Id=\"xmldsig-dde0f13d-b44c-4441-be73-17e5a9ff4a92\">\n" +
                "<ds:SignedInfo>\n" +
                "<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
                "<ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/>\n" +
                "<ds:Reference Id=\"xmldsig-dde0f13d-b44c-4441-be73-17e5a9ff4a92-ref0\">\n" +
                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
                "<ds:DigestValue>BatS7/Yr3xI71qDEbwvYorZniUckNwbbkWAS1SV8jWg=</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "<ds:Reference Type=\"http://uri.etsi.org/01903#SignedProperties\" URI=\"#xmldsig-dde0f13d-b44c-4441-be73-17e5a9ff4a92-signedprops\">\n" +
                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
                "<ds:DigestValue>DKbF6Z6JnI7pPse+wq7LKmkQR7xHDWK1n4G7X2t9tUs=</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "</ds:SignedInfo>\n" +
                "<ds:SignatureValue Id=\"xmldsig-dde0f13d-b44c-4441-be73-17e5a9ff4a92-sigvalue\">\n" +
                "UZtpKCZc6JPxb2Z3ddnOB4Xc7Iv8MVZ2Wf/+TMPRRv2QLP90vc75PtncWuvavQvLhzXKKO8xNovc\n" +
                "H0nKGd/uj339rOntxQYMaD+S0N5S5l9m+ArZpIMGCa2L6BFmilyAMEQEyPbOkzX6TC1zvzJ6dBCZ\n" +
                "9lcxLdcU9PllTTn/ov5+WED8hNgSHpxdO1rIlqlzgnC/CgaeomJnVljmHF0yAcD1KSOxLPsU/aW9\n" +
                "nsPhGicaiVl3qVE3mEzxPhLX8oXdp6hZitsgOM8qFG9FKQ72dEKlU8Vwt/SKipA+siZ3ES4xen5E\n" +
                "ex5DHKqTDVnsgE3ymz2I45Sgtu+Htvygx06NuQ==\n" +
                "</ds:SignatureValue>\n" +
                "<ds:KeyInfo>\n" +
                "<ds:X509Data>\n" +
                "<ds:X509Certificate>\n" +
                "MIIDIjCCAgqgAwIBAgIET/fj8TANBgkqhkiG9w0BAQ0FADBTMQswCQYDVQQGEwJCRTELMAkGA1UE\n" +
                "CAwCYmUxCzAJBgNVBAcMAkJlMQ4wDAYDVQQKDAVEaWdpdDELMAkGA1UECwwCQjExDTALBgNVBAMM\n" +
                "BGtleTEwHhcNMTIwNzA3MDcyNDIyWhcNMTMwNzA3MDcyNDIyWjBTMQswCQYDVQQGEwJCRTELMAkG\n" +
                "A1UECAwCYmUxCzAJBgNVBAcMAkJlMQ4wDAYDVQQKDAVEaWdpdDELMAkGA1UECwwCQjExDTALBgNV\n" +
                "BAMMBGtleTEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCalRX1Gciyi5rsZD/MdBRd\n" +
                "dVfggd2csJcOkxS/GwSVEdFM4CaUp5WXVddF0Peah9w6bFQCM1rN+5pPPe9yc1IVVu2NvOjdDprw\n" +
                "y6nw7uSypRYO1Ez7jtTIZZlYZ/HV9zKLjgn1MdecMiimu016GWfjJC30f6x0zhqAVFCQt4gZ7q1a\n" +
                "4LJmVvQxPXuLBep0uAQCxPm8DWgjypjDnzft6JUDyMbEdVOq6NhihGJuvOiJg2XMR+u3Iq8UT7GH\n" +
                "cpLlRduHjUfukHxazDN1shOKFd35PmThKKVX69QLEu/gZcmOBkbRX0y8BC3Y3Zao33VfTpocSWyf\n" +
                "81FLi8sGIHJ2/NupAgMBAAEwDQYJKoZIhvcNAQENBQADggEBAGtGDU5TzBbdNpn4R6HxaNU7zPFo\n" +
                "JN09vNpgwEiqSvL0ec5zcSI279iEHhkzhgFdL2rkTIxAFDfj7zuZPkJ7yoRXJnHR8S0+Tkw8WTcL\n" +
                "6IDcF9jnSFtn4w4SVYAnRavoss8IfRLmyGa8h7cdo2qlFSmlKBUms9A4qjZnB5bWUCUuBDPJSakM\n" +
                "/YM4/xn3tqpYqoq9KTMnr8r/eeQZjfXxB42v/MnRvrF3XP6DBJ5fxnC5jjkTljWCGMtL3jXvD2qN\n" +
                "tgXN7xs1AkVZS9etBrRd/+8SEdHvY5hVX/434gzfcWpKw4vylHeVVcQpVsKQANZ9RjzNDFS4nNo6\n" +
                "Xo5+YQNxpwM=\n" +
                "</ds:X509Certificate>\n" +
                "</ds:X509Data>\n" +
                "</ds:KeyInfo>\n" +
                "<ds:Object><xades:QualifyingProperties xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:xades141=\"http://uri.etsi.org/01903/v1.4.1#\" Target=\"#xmldsig-dde0f13d-b44c-4441-be73-17e5a9ff4a92\"><xades:SignedProperties Id=\"xmldsig-dde0f13d-b44c-4441-be73-17e5a9ff4a92-signedprops\"><xades:SignedSignatureProperties><xades:SigningTime>2012-08-14T09:59:58.159+02:00</xades:SigningTime><xades:SigningCertificate><xades:Cert><xades:CertDigest><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><ds:DigestValue>jcw2Y/ZCEC333tOf7cTNPgQJqWNdel6sqfn74wtisBI=</ds:DigestValue></xades:CertDigest><xades:IssuerSerial><ds:X509IssuerName>CN=key1,OU=B1,O=Digit,L=Be,ST=be,C=BE</ds:X509IssuerName><ds:X509SerialNumber>1341645809</ds:X509SerialNumber></xades:IssuerSerial></xades:Cert></xades:SigningCertificate></xades:SignedSignatureProperties></xades:SignedProperties></xades:QualifyingProperties></ds:Object>\n" +
                "</ds:Signature>";

        URI fileURI = this.getClass().getResource("/testStore.p12").toURI();

        List<KeyStoreEntry<PrivateKey>> entries;
        try (InputStream inputStream = Files.newInputStream(Paths.get(fileURI))) {
            entries = EtxSecurityProvider.getInstance().getCertificateService().getKeyStoreEntries(inputStream, "PKCS12", "test123".toCharArray(), PrivateKey.class);
        }

        KeyStoreEntry<PrivateKey> signatureEntry = null;
        for (KeyStoreEntry<PrivateKey> entry : entries) {
            if (entry.getAlias().equals("key1")) {
                signatureEntry = entry;
            }
        }

        Element signatureElement = XMLHelper.stringToDocument(signature).getDocumentElement();

        boolean result = xadesSignature.verifySignature(signatureEntry.getX509Certificate(), signatureData, signatureElement);

        EtxCommonMatchers.assertThat(result, Matchers.is(true));
    }

    @Test
    public void testValidateWithOtherData() throws Exception {

        String signatureData = buildSignDataDocument(3);
        String signature = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Id=\"xmldsig-53676359-adef-4254-8d60-ac728204becc\">\n" +
                "<ds:SignedInfo>\n" +
                "<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
                "<ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/>\n" +
                "<ds:Reference Id=\"xmldsig-53676359-adef-4254-8d60-ac728204becc-ref0\">\n" +
                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
                "<ds:DigestValue>Aqku7C7u6sC8DSg33OgNJibSUq5ljXW/6XGu1W1a+yg=</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "<ds:Reference Type=\"http://uri.etsi.org/01903#SignedProperties\" URI=\"#xmldsig-53676359-adef-4254-8d60-ac728204becc-signedprops\">\n" +
                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
                "<ds:DigestValue>YOoAdnTOL1DSrKjS53InifDyQgC6bzyDJZLgMm0AKF8=</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "</ds:SignedInfo>\n" +
                "<ds:SignatureValue Id=\"xmldsig-53676359-adef-4254-8d60-ac728204becc-sigvalue\">\n" +
                "ZsXH87ry7EmHkD+Z4lSOGBCbTkbsjh3F+8HMyl5SX9KGRBFlRXKUTQokd2OfZLYtjEhznsoGatN9\n" +
                "Znp7Z1Jr2vvuSQglGTdkTxB7FlK0ZWMOr+hzUaN+mU6/tJLlGVgvZ/Krj4rt9QmtlKEwfQDLhyRG\n" +
                "mt2a5FiS7QM2Wn8J1ks7PdEG7GfHbrTerIpBR91xwfDBJGuptJCPRAvIW520HhaAjMo+zf3jMa8I\n" +
                "/lFEr2+r1ZurdtsAzYdVgLgnq/zBfdafN0SCnDMEuYPP00gQBZ2Cnoha0cGFguHPZkqX4i2EmNgN\n" +
                "3rrgVlGkvLltqgWRfrO8H4K1cFsl0Mmyu6pscQ==\n" +
                "</ds:SignatureValue>\n" +
                "<ds:KeyInfo>\n" +
                "<ds:X509Data>\n" +
                "<ds:X509Certificate>\n" +
                "MIIDIjCCAgqgAwIBAgIET/fj8TANBgkqhkiG9w0BAQ0FADBTMQswCQYDVQQGEwJCRTELMAkGA1UE\n" +
                "CAwCYmUxCzAJBgNVBAcMAkJlMQ4wDAYDVQQKDAVEaWdpdDELMAkGA1UECwwCQjExDTALBgNVBAMM\n" +
                "BGtleTEwHhcNMTIwNzA3MDcyNDIyWhcNMTMwNzA3MDcyNDIyWjBTMQswCQYDVQQGEwJCRTELMAkG\n" +
                "A1UECAwCYmUxCzAJBgNVBAcMAkJlMQ4wDAYDVQQKDAVEaWdpdDELMAkGA1UECwwCQjExDTALBgNV\n" +
                "BAMMBGtleTEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCalRX1Gciyi5rsZD/MdBRd\n" +
                "dVfggd2csJcOkxS/GwSVEdFM4CaUp5WXVddF0Peah9w6bFQCM1rN+5pPPe9yc1IVVu2NvOjdDprw\n" +
                "y6nw7uSypRYO1Ez7jtTIZZlYZ/HV9zKLjgn1MdecMiimu016GWfjJC30f6x0zhqAVFCQt4gZ7q1a\n" +
                "4LJmVvQxPXuLBep0uAQCxPm8DWgjypjDnzft6JUDyMbEdVOq6NhihGJuvOiJg2XMR+u3Iq8UT7GH\n" +
                "cpLlRduHjUfukHxazDN1shOKFd35PmThKKVX69QLEu/gZcmOBkbRX0y8BC3Y3Zao33VfTpocSWyf\n" +
                "81FLi8sGIHJ2/NupAgMBAAEwDQYJKoZIhvcNAQENBQADggEBAGtGDU5TzBbdNpn4R6HxaNU7zPFo\n" +
                "JN09vNpgwEiqSvL0ec5zcSI279iEHhkzhgFdL2rkTIxAFDfj7zuZPkJ7yoRXJnHR8S0+Tkw8WTcL\n" +
                "6IDcF9jnSFtn4w4SVYAnRavoss8IfRLmyGa8h7cdo2qlFSmlKBUms9A4qjZnB5bWUCUuBDPJSakM\n" +
                "/YM4/xn3tqpYqoq9KTMnr8r/eeQZjfXxB42v/MnRvrF3XP6DBJ5fxnC5jjkTljWCGMtL3jXvD2qN\n" +
                "tgXN7xs1AkVZS9etBrRd/+8SEdHvY5hVX/434gzfcWpKw4vylHeVVcQpVsKQANZ9RjzNDFS4nNo6\n" +
                "Xo5+YQNxpwM=\n" +
                "</ds:X509Certificate>\n" +
                "</ds:X509Data>\n" +
                "</ds:KeyInfo>\n" +
                "<ds:Object><xades:QualifyingProperties xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:xades141=\"http://uri.etsi.org/01903/v1.4.1#\" Target=\"#xmldsig-53676359-adef-4254-8d60-ac728204becc\"><xades:SignedProperties Id=\"xmldsig-53676359-adef-4254-8d60-ac728204becc-signedprops\"><xades:SignedSignatureProperties><xades:SigningTime>2012-07-31T17:06:17.078+02:00</xades:SigningTime><xades:SigningCertificate><xades:Cert><xades:CertDigest><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><ds:DigestValue>jcw2Y/ZCEC333tOf7cTNPgQJqWNdel6sqfn74wtisBI=</ds:DigestValue></xades:CertDigest><xades:IssuerSerial><ds:X509IssuerName>CN=key1,OU=B1,O=Digit,L=Be,ST=be,C=BE</ds:X509IssuerName><ds:X509SerialNumber>1341645809</ds:X509SerialNumber></xades:IssuerSerial></xades:Cert></xades:SigningCertificate></xades:SignedSignatureProperties></xades:SignedProperties></xades:QualifyingProperties></ds:Object>\n" +
                "</ds:Signature>";

        URI fileURI = this.getClass().getResource("/testStore.p12").toURI();

        List<KeyStoreEntry<PrivateKey>> entries;
        try (InputStream inputStream = Files.newInputStream(Paths.get(fileURI))) {
            entries = EtxSecurityProvider.getInstance().getCertificateService().getKeyStoreEntries(inputStream, "PKCS12", "test123".toCharArray(), PrivateKey.class);
        }

        KeyStoreEntry<PrivateKey> signatureEntry = null;
        for (KeyStoreEntry<PrivateKey> entry : entries) {
            if (entry.getAlias().equals("key1")) {
                signatureEntry = entry;
            }
        }

        Element signatureElement = XMLHelper.stringToDocument(signature).getDocumentElement();

        boolean result = xadesSignature.verifySignature(signatureEntry.getX509Certificate(), signatureData, signatureElement);

        EtxCommonMatchers.assertThat(result, Matchers.is(false));
    }

    private String buildSignDataDocument(int elements) throws JAXBException {
        SignedBundle bundle = new SignedBundle();

        for (int i = 0; i < elements; i++) {
            DocumentType documentType = new DocumentType();
            documentType.setId("2345" + i);
            documentType.setDigestMethod("SHA-512");
            documentType.setDigestValue("3456789764345678" + i);

            bundle.getDocument().add(documentType);
        }


        return XMLHelper.jaxbToXmlString(bundle);
    }
}
