package eu.europa.ec.etrustex.webaccess.security;

import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.utils.XMLHelper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: micleva
 * @date: 8/13/12 12:08 PM
 * @project: ETX
 */
public class CertificateServiceImplTest extends AbstractTest {

    private CertificateServiceImpl certificateService = new CertificateServiceImpl();
    private byte[] x509Certificate;
    private byte[] otherCertificate;

    @Override
    protected void onSetUp() {
        super.onSetUp();
        EtxSecurityProvider.init();
        x509Certificate = getFileContent("/BC_testCertificate.cer");
        otherCertificate = getFileContent("/lotto.be.cer");
    }

    private byte[] getFileContent(String path) {
        byte[] result = null;
        try (InputStream fileInputStreams = this.getClass().getResourceAsStream(path)) {
            result = new byte[fileInputStreams.available()];
            fileInputStreams.read(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test
    public void testExtractCertificateFromSignature() throws Exception {
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
                "<ds:X509Certificate>" +
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
                "Xo5+YQNxpwM=" +
                "</ds:X509Certificate>\n" +
                "</ds:X509Data>\n" +
                "</ds:KeyInfo>\n" +
                "<ds:Object><xades:QualifyingProperties xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:xades141=\"http://uri.etsi.org/01903/v1.4.1#\" Target=\"#xmldsig-53676359-adef-4254-8d60-ac728204becc\"><xades:SignedProperties Id=\"xmldsig-53676359-adef-4254-8d60-ac728204becc-signedprops\"><xades:SignedSignatureProperties><xades:SigningTime>2012-07-31T17:06:17.078+02:00</xades:SigningTime><xades:SigningCertificate><xades:Cert><xades:CertDigest><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><ds:DigestValue>jcw2Y/ZCEC333tOf7cTNPgQJqWNdel6sqfn74wtisBI=</ds:DigestValue></xades:CertDigest><xades:IssuerSerial><ds:X509IssuerName>CN=key1,OU=B1,O=Digit,L=Be,ST=be,C=BE</ds:X509IssuerName><ds:X509SerialNumber>1341645809</ds:X509SerialNumber></xades:IssuerSerial></xades:Cert></xades:SigningCertificate></xades:SignedSignatureProperties></xades:SignedProperties></xades:QualifyingProperties></ds:Object>\n" +
                "</ds:Signature>";

        Element element = XMLHelper.stringToDocument(signature).getDocumentElement();

        X509Certificate certificate = certificateService.extractSignatureCertificateFromRootNode(element);

        EtxCommonMatchers.assertThat(certificate.getSubjectDN().toString(), Matchers.is("CN=key1, OU=B1, O=Digit, L=Be, ST=be, C=BE"));
    }

    @Test
    public void testExtractCertificateFromSignature_noSignature() throws Exception {

        Element signatureElement = null;

        X509Certificate certificate = certificateService.extractSignatureCertificateFromRootNode(signatureElement);

        EtxCommonMatchers.assertThat(certificate, Matchers.is(Matchers.nullValue()));
    }

    @Test
    public void testGetCertificate() throws Exception {

        PublicKey publicKey = certificateService.getCertificate(x509Certificate).getPublicKey();

        byte[] expectedResult = new byte[]{48, -126, 1, 34, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1, 15, 0, 48, -126, 1, 10, 2, -126, 1, 1, 0, -40, -72, 14, -64,
                73, 52, 31, -94, 22, -59, -86, 9, -88, 44, 21, 98, 6, -44, -29, -97, 57, 99, -64, 123, -71, 58, -46, 102, 59, 82, 15, 5, 1, 113, 84, -51, 49, -45,
                8, -16, -22, 17, 123, -26, 101, 38, -7, 33, 98, 114, 28, -13, -66, -5, -118, -88, 25, -57, 117, 12, -105, -2, -45, 70, 62, 18, 42, 13, -75, 52, 62,
                32, 81, -51, -24, -34, 78, -8, -106, 106, 77, 45, 100, -3, 108, 4, -85, 8, -60, -98, -50, 51, 96, -90, 23, 119, -61, 25, 10, 120, -42, -13, -111, -109,
                64, -75, -103, -90, 87, 113, 100, -99, -4, 25, -52, 12, -79, 102, -25, 76, -96, 100, -78, -105, 45, -109, 87, 8, 92, -88, 35, 34, -99, 58, 84, 8, -110,
                96, -110, -64, -74, -100, -97, 18, -63, -72, 117, -19, 108, -75, 122, -54, 31, -117, -5, 106, 6, 78, 111, 100, -86, 30, 91, 25, 52, 29, 114, 64, -46,
                39, 80, -32, -64, -26, -30, -71, 73, -91, -52, -36, -87, 83, -103, 112, -31, 10, -102, -73, 35, 50, 96, -68, 44, -51, 5, 20, -55, 45, -81, -6, -104,
                83, 63, 77, 126, 71, -54, 87, 69, -21, 125, -12, 14, -75, 21, -59, 127, 65, -78, 65, 5, 24, 55, -37, 0, -9, -74, -43, -8, 120, 55, 108, 94, -126, 34,
                -1, -1, 123, -50, -127, 89, 72, 20, 20, 29, -113, -22, 10, -85, -57, -103, -57, 42, -105, -117, -25, 2, 3, 1, 0, 1};

        EtxCommonMatchers.assertThat(publicKey.getAlgorithm(), Matchers.is("RSA"));
        EtxCommonMatchers.assertThat(publicKey.getEncoded(), Matchers.is(expectedResult));
    }

    @Test
    public void testGetKeyStoreEntries_PublicKeys() throws Exception {
        URI fileURI = this.getClass().getResource("/testStore.p12").toURI();

        List<KeyStoreEntry<PublicKey>> entries;
        try (InputStream inputStream = Files.newInputStream(Paths.get(fileURI))) {
            entries = certificateService.getKeyStoreEntries(inputStream, "PKCS12", "test123".toCharArray(), PublicKey.class);
        }

        EtxCommonMatchers.assertThat(entries, Matchers.is(Matchers.notNullValue()));
        EtxCommonMatchers.assertThat(entries.size(), Matchers.is(3));

        byte[] key1Value = new byte[]{48, -126, 1, 34, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1, 15, 0,
                48, -126, 1, 10, 2, -126, 1, 1, 0, -102, -107, 21, -11, 25, -56, -78, -117, -102, -20, 100, 63, -52, 116, 20, 93,
                117, 87, -32, -127, -35, -100, -80, -105, 14, -109, 20, -65, 27, 4, -107, 17, -47, 76, -32, 38, -108, -89, -107,
                -105, 85, -41, 69, -48, -9, -102, -121, -36, 58, 108, 84, 2, 51, 90, -51, -5, -102, 79, 61, -17, 114, 115, 82, 21,
                86, -19, -115, -68, -24, -35, 14, -102, -16, -53, -87, -16, -18, -28, -78, -91, 22, 14, -44, 76, -5, -114, -44, -56,
                101, -103, 88, 103, -15, -43, -9, 50, -117, -114, 9, -11, 49, -41, -100, 50, 40, -90, -69, 77, 122, 25, 103, -29, 36,
                45, -12, 127, -84, 116, -50, 26, -128, 84, 80, -112, -73, -120, 25, -18, -83, 90, -32, -78, 102, 86, -12, 49, 61, 123,
                -117, 5, -22, 116, -72, 4, 2, -60, -7, -68, 13, 104, 35, -54, -104, -61, -97, 55, -19, -24, -107, 3, -56, -58, -60, 117,
                83, -86, -24, -40, 98, -124, 98, 110, -68, -24, -119, -125, 101, -52, 71, -21, -73, 34, -81, 20, 79, -79, -121,
                114, -110, -27, 69, -37, -121, -115, 71, -18, -112, 124, 90, -52, 51, 117, -78, 19, -118, 21, -35, -7, 62, 100, -31, 40,
                -91, 87, -21, -44, 11, 18, -17, -32, 101, -55, -114, 6, 70, -47, 95, 76, -68, 4, 45, -40, -35, -106, -88, -33, 117, 95,
                78, -102, 28, 73, 108, -97, -13, 81, 75, -117, -53, 6, 32, 114, 118, -4, -37, -87, 2, 3, 1, 0, 1};
        EtxCommonMatchers.assertThat(entries.get(0).getAlias(), Matchers.is("key1"));
        EtxCommonMatchers.assertThat(entries.get(0).getX509Certificate(), Matchers.is(Matchers.notNullValue()));
        EtxCommonMatchers.assertThat(dnNamesToEntrySet(entries.get(0).getX509Certificate().getSubjectDN().toString()), everyItem(isIn(dnNamesToEntrySet("C=BE,ST=be,L=Be,O=Digit,OU=B1,CN=key1"))));
        EtxCommonMatchers.assertThat(entries.get(0).getKeyEntry(), Matchers.is(Matchers.instanceOf(PublicKey.class)));
        EtxCommonMatchers.assertThat(entries.get(0).getKeyEntry().getAlgorithm(), Matchers.is("RSA"));
        EtxCommonMatchers.assertThat(entries.get(0).getKeyEntry().getEncoded(), Matchers.is(Matchers.equalTo(key1Value)));

        byte[] key2Value = new byte[]{48, -126, 1, 34, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1,
                15, 0, 48, -126, 1, 10, 2, -126, 1, 1, 0, -76, -23, -112, 53, 68, 92, 48, -74, -124, -46, 49, -74, -48, 96, -51,
                -98, 58, -122, -17, 98, -63, -19, 107, 102, -38, 83, 16, -16, -121, -66, 104, 64, -90, -10, 34, 50,
                -42, 107, -96, 85, -44, 33, -40, 12, 69, -25, 101, -69, 96, 53, 37, 68, -2, 15, 9, 99, -83, 102, -35, -63, 66,
                -26, -97, -9, -128, -9, -31, 74, -101, -95, 106, -110, -73, 19, 49, -50, -19, 107, -61, 113, 10, 8, -26, 110,
                41, -4, -101, -57, -46, 30, 12, -41, 104, 56, 18, 79, -43, -96, -120, 93, 19, 85, 56, 83, 52, -10, -48, 43, 36, 126, 3,
                -122, 15, -120, 122, 8, -115, 71, -47, -81, -7, 123, -59, 44, 65, -40, 7, -126, -34, 92, -3, -13, 31, -98, -66, 12, 10, 126, -23, -36,
                -48, -41, 84, -59, -47, 6, -119, -1, -101, 67, -56, -61, 104, 5, 30, -78, -96, -9, 38, 8, -23, -67, -53, -87, 72,
                66, -6, 104, 72, -104, -32, -122, -8, 85, -61, -101, 32, -35, -126, -72, 59, -85, 101, -61, 12, -6, 69, 73, -53,
                -25, 50, -46, -51, -20, 35, -71, 106, 33, 41, -120, -19, -6, -96, 100, 84, 117, -19, -92, 61, -112, -10, 67,
                51, -109, 99, 71, 30, 94, -23, 92, -95, 65, 118, -3, -75, -2, -32, -26, 109, -34, 100, -15, 98, 29, -112,
                -120, -19, 19, -7, 9, 90, -50, 14, -86, 82, -66, 49, 121, 66, -62, -46, -33, -119, -55, 8, -77, 2, 3, 1, 0, 1};
        EtxCommonMatchers.assertThat(entries.get(1).getAlias(), Matchers.is("key2"));
        EtxCommonMatchers.assertThat(entries.get(1).getX509Certificate(), Matchers.is(Matchers.notNullValue()));
        EtxCommonMatchers.assertThat(dnNamesToEntrySet(entries.get(1).getX509Certificate().getSubjectDN().toString()), everyItem(isIn(dnNamesToEntrySet("C=ro,ST=valer,L=valer,O=valer,OU=B1,CN=second"))));
        EtxCommonMatchers.assertThat(entries.get(1).getKeyEntry(), Matchers.is(Matchers.instanceOf(PublicKey.class)));
        EtxCommonMatchers.assertThat(entries.get(1).getKeyEntry().getAlgorithm(), Matchers.is("RSA"));
        EtxCommonMatchers.assertThat(entries.get(1).getKeyEntry().getEncoded(), Matchers.is(Matchers.equalTo(key2Value)));
    }

    @Test
    public void testGetKeyStoreEntries_PrivateKeys() throws Exception {
        URI fileURI = this.getClass().getResource("/testStore-LongPass.p12").toURI();

        List<KeyStoreEntry<PrivateKey>> entries;
        try (InputStream inputStream = Files.newInputStream(Paths.get(fileURI))) {
            entries = certificateService.getKeyStoreEntries(inputStream, "PKCS12", "Welcome2016".toCharArray(), PrivateKey.class);
        }

        EtxCommonMatchers.assertThat(entries, Matchers.is(Matchers.notNullValue()));
        EtxCommonMatchers.assertThat(entries.size(), Matchers.is(3));

        byte[] key1Value = new byte[]{48, -126, 4, -67, 2, 1, 0, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 4,
                -126, 4, -89, 48, -126, 4, -93, 2, 1, 0, 2, -126, 1, 1, 0, -102, -107, 21, -11, 25, -56, -78, -117, -102, -20,
                100, 63, -52, 116, 20, 93, 117, 87, -32, -127, -35, -100, -80, -105, 14, -109, 20, -65, 27, 4, -107, 17, -47,
                76, -32, 38, -108, -89, -107, -105, 85, -41, 69, -48, -9, -102, -121, -36, 58, 108, 84, 2, 51, 90, -51, -5,
                -102, 79, 61, -17, 114, 115, 82, 21, 86, -19, -115, -68, -24, -35, 14, -102, -16, -53, -87, -16, -18, -28,
                -78, -91, 22, 14, -44, 76, -5, -114, -44, -56, 101, -103, 88, 103, -15, -43, -9, 50, -117, -114, 9, -11, 49,
                -41, -100, 50, 40, -90, -69, 77, 122, 25, 103, -29, 36, 45, -12, 127, -84, 116, -50, 26, -128, 84, 80,
                -112, -73, -120, 25, -18, -83, 90, -32, -78, 102, 86, -12, 49, 61, 123, -117, 5, -22, 116, -72, 4, 2,
                -60, -7, -68, 13, 104, 35, -54, -104, -61, -97, 55, -19, -24, -107, 3, -56, -58, -60, 117, 83, -86, -24,
                -40, 98, -124, 98, 110, -68, -24, -119, -125, 101, -52, 71, -21, -73, 34, -81, 20, 79, -79, -121, 114, -110,
                -27, 69, -37, -121, -115, 71, -18, -112, 124, 90, -52, 51, 117, -78, 19, -118, 21, -35, -7, 62, 100, -31, 40,
                -91, 87, -21, -44, 11, 18, -17, -32, 101, -55, -114, 6, 70, -47, 95, 76, -68, 4, 45, -40, -35, -106, -88, -33,
                117, 95, 78, -102, 28, 73, 108, -97, -13, 81, 75, -117, -53, 6, 32, 114, 118, -4, -37, -87, 2, 3, 1, 0,
                1, 2, -126, 1, 0, 22, 101, -13, -64, -121, -13, -28, -85, 5, -49, -86, -42, -13, -80, -2, 34, 108, -106,
                71, 25, -118, 26, -30, 27, -127, 41, 30, -96, 114, 49, -65, -57, -51, 35, -18, 118, -88, -93, 127, -79,
                101, -26, -61, 94, -4, 1, -112, 30, 0, -7, 33, 83, 16, 77, -33, -6, -52, -23, 30, 120, 75, -123, 26, 39,
                -83, 104, -50, 88, -55, 4, -2, -48, -60, -125, 62, 36, -126, -8, -16, 73, -85, 96, -118, -78, 124, -32, -67,
                68, 44, 52, -125, -14, -53, -65, -51, 9, -83, -34, -18, -30, 112, 78, 101, 123, 127, 24, 105, -101, 88,
                77, -58, 23, -53, 110, 61, 55, 34, -83, -22, 15, 60, -8, -6, -109, 125, -124, 98, -3, 79, -122, -22, 12,
                -122, 88, 48, 94, -114, 62, 93, -95, 100, -34, -18, -110, -128, -81, -62, 26, -2, -57, -111, 99, 37, 65,
                37, -14, -110, 86, -83, -87, 91, -24, 106, 96, -37, -124, -57, 125, -99, 25, -44, 43, 80, 38, 121, -35,
                43, -60, -108, 48, 13, -25, 65, -48, -117, 57, -91, 65, -60, -37, -109, 49, -11, 107, 88, 8, 27, 115,
                -112, 28, -2, 96, -123, -35, -26, -62, 110, -124, 4, 25, -45, -28, -5, -68, -4, 18, -81, -37, -68, 48,
                -96, 108, -101, 80, 1, 107, 108, 46, -29, 52, 69, 122, -61, 48, 97, -14, 115, 6, 20, 125, 59, 1, -62, 12,
                120, -13, -107, -97, 51, -55, 119, 66, 55, 56, 40, 1, 2, -127, -127, 0, -11, 54, -69, -99, -48, -78, -92,
                92, -64, -35, -8, 124, 41, -65, 79, 21, -71, 75, 83, 104, -77, 121, 52, 127, 57, -58, 29, -32, 76, 78,
                -107, -97, -23, -39, -27, 83, 92, -46, 16, -25, 31, 12, -9, -123, -59, 40, -68, -95, -78, 124, 37, -109,
                39, 69, 71, -20, -103, -60, -87, 109, 23, -81, -30, -26, 66, -28, -29, 16, 107, -84, 104, -31, -23, 81, 22,
                18, -16, 126, 103, 113, 40, 82, -12, -53, 111, 79, -53, 99, 71, 60, 22, 9, -118, -94, 5, 58, 16, -43, 63, 21,
                -31, 87, 41, 31, -75, -120, -38, -13, -33, -110, 103, -84, 98, 81, -96, -123, -2, -110, 116, -93, -32, -53,
                -14, -125, -111, 65, 70, -55, 2, -127, -127, 0, -95, 97, -56, -82, 88, -78, 38, 61, 86, -12, -48, 113, 25,
                87, 17, -62, -11, 22, 93, -55, 76, 126, 34, -116, -8, -91, -92, 46, 55, 6, -23, 44, -43, 44, 27, -22, -49,
                58, 62, -64, -99, 51, 59, 82, -11, -7, -62, -36, -51, -53, 17, 46, -31, -97, 76, 51, -90, -4, 42, -75, 49,
                -13, -46, 79, 102, -50, 107, -83, 23, -59, -19, -106, 56, 23, -4, 114, -2, -14, -116, -110, 94, 41, 110,
                113, -20, -35, -36, 76, 53, -8, -102, 25, 95, -90, 118, -114, -98, 15, -103, -54, 44, 84, 92, 24, 118, -25,
                126, 11, -51, 54, -105, -25, 124, 80, 77, 30, -98, -79, 19, 55, -29, -12, 111, -100, -5, -31, -3, -31, 2, -127,
                -128, 92, -39, 23, -101, 62, -3, -107, 108, -91, 126, -90, -22, -28, -89, -46, -101, 40, 41, 20, 113, 13,
                -101, -12, 59, -60, 53, 118, -12, -118, 16, 43, 105, 124, -109, -117, -40, -49, -55, -98, -86, -44, -64, 5,
                45, 58, -91, 29, 31, -96, -60, -113, 57, -77, -31, -16, -60, -18, -56, 95, 90, -74, 96, 91, -22, -46, 35, 118,
                -78, -82, 2, 127, 72, 35, 31, 23, -37, -98, 2, 78, -54, -78, -7, 14, 69, -59, 101, 18, 95, 10, 6, -1, 85, 39,
                -112, 7, -88, -97, -35, 14, -31, 11, 13, 4, -66, 67, -17, -120, -16, 84, -108, 75, -5, 34, -45, 67, -109, -40,
                -5, -67, 124, -101, -50, -67, -118, 1, -61, 20, -71, 2, -127, -128, 86, -50, 118, 87, -15, -75, -62, 3, -12,
                -90, 51, 8, -29, 12, -122, -83, 75, -112, 0, 90, -85, 30, -98, 15, 30, 32, 91, 51, -48, -67, -110, -25, 94,
                -77, 31, 65, 42, -66, -75, -54, 125, -60, 117, 0, -90, -10, -11, -18, 55, 116, 39, -122, 4, 46, -33, -102,
                89, 20, 3, 20, -4, 13, -98, -18, -66, -22, 1, -39, 85, -108, 77, -68, 35, -24, 35, 14, -24, 60, 39, -119,
                3, -59, 93, 119, 66, 102, 4, 125, -87, -6, 83, 73, -102, -67, -69, -65, -13, 29, -119, 39, -54, -72, -123,
                -31, 82, -12, 42, 68, -58, -39, -70, -45, -11, 118, 108, 69, 48, 47, 39, -101, 68, -82, -46, -22, -86, 66,
                97, -127, 2, -127, -127, 0, -109, 6, 89, 114, -23, 59, 2, -93, -127, 80, 74, 81, -91, 73, 22, -85, -70, 17,
                -93, -95, -29, -47, 13, 116, 26, 35, -113, 78, 12, -42, -27, -26, -23, 18, 66, 47, 19, -47, 8, -120, -59, -2,
                -17, -106, 53, 119, 15, 89, -21, 8, 104, -42, 16, -89, -6, -109, -20, 63, -109, -27, -116, -32, -82, -12, 83,
                -78, 99, -110, -24, -97, 124, 85, 126, 2, 93, 64, -96, -90, -9, -106, -20, -86, 23, -54, 65, 15, -64, 89, -83,
                -92, -124, -14, 82, 55, 77, -28, 24, 108, -43, -12, -93, -103, 5, 95, 66, -39, -4, 78, -79, 97, -123, 76, 62, 108,
                30, -9, -58, -86, -114, -33, -73, 68, -92, -19, 76, 101, -58, 97};
        EtxCommonMatchers.assertThat(entries.get(0).getAlias(), Matchers.is("key1"));
        EtxCommonMatchers.assertThat(entries.get(0).getX509Certificate(), Matchers.is(Matchers.notNullValue()));
        EtxCommonMatchers.assertThat(dnNamesToEntrySet("CN=key1, OU=B1, O=Digit, L=Be, ST=be, C=BE"), everyItem(isIn(dnNamesToEntrySet(entries.get(0).getX509Certificate().getSubjectDN().toString()))));
        EtxCommonMatchers.assertThat(entries.get(0).getKeyEntry(), Matchers.is(Matchers.instanceOf(PrivateKey.class)));
        EtxCommonMatchers.assertThat(entries.get(0).getKeyEntry().getAlgorithm(), Matchers.is("RSA"));
        EtxCommonMatchers.assertThat(entries.get(0).getKeyEntry().getEncoded(), Matchers.is(Matchers.equalTo(key1Value)));

        byte[] key2Value = new byte[]{48, -126, 4, -67, 2, 1, 0, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0,
                4, -126, 4, -89, 48, -126, 4, -93, 2, 1, 0, 2, -126, 1, 1, 0, -76, -23, -112, 53, 68, 92, 48, -74, -124, -46,
                49, -74, -48, 96, -51, -98, 58, -122, -17, 98, -63, -19, 107, 102, -38, 83, 16, -16, -121, -66, 104, 64, -90,
                -10, 34, 50, -42, 107, -96, 85, -44, 33, -40, 12, 69, -25, 101, -69, 96, 53, 37, 68, -2, 15, 9, 99, -83,
                102, -35, -63, 66, -26, -97, -9, -128, -9, -31, 74, -101, -95, 106, -110, -73, 19, 49, -50, -19, 107, -61,
                113, 10, 8, -26, 110, 41, -4, -101, -57, -46, 30, 12, -41, 104, 56, 18, 79, -43, -96, -120, 93, 19, 85,
                56, 83, 52, -10, -48, 43, 36, 126, 3, -122, 15, -120, 122, 8, -115, 71, -47, -81, -7, 123, -59, 44, 65,
                -40, 7, -126, -34, 92, -3, -13, 31, -98, -66, 12, 10, 126, -23, -36, -48, -41, 84, -59, -47, 6, -119, -1,
                -101, 67, -56, -61, 104, 5, 30, -78, -96, -9, 38, 8, -23, -67, -53, -87, 72, 66, -6, 104, 72, -104, -32,
                -122, -8, 85, -61, -101, 32, -35, -126, -72, 59, -85, 101, -61, 12, -6, 69, 73, -53, -25, 50, -46, -51,
                -20, 35, -71, 106, 33, 41, -120, -19, -6, -96, 100, 84, 117, -19, -92, 61, -112, -10, 67, 51, -109, 99, 71,
                30, 94, -23, 92, -95, 65, 118, -3, -75, -2, -32, -26, 109, -34, 100, -15, 98, 29, -112, -120, -19, 19,
                -7, 9, 90, -50, 14, -86, 82, -66, 49, 121, 66, -62, -46, -33, -119, -55, 8, -77, 2, 3, 1, 0, 1, 2, -126,
                1, 0, 12, 120, 16, -91, 107, 57, -61, 109, -63, 7, -49, -65, 94, -21, 93, 32, -85, -66, 3, 31, -42, 103,
                -21, -64, 34, 10, 25, -97, -54, 107, 88, 58, 36, -121, -48, 97, -102, -97, -66, 100, 76, 3, -83, 37, 99,
                104, 8, 125, -6, -86, -113, 16, 11, 70, -76, 9, -9, -72, -96, 78, 34, -77, 76, -111, 61, -123, 88, 52,
                -47, 55, 53, -26, 56, -46, 12, -37, -53, 71, 99, 40, 22, 93, -8, -85, -35, 30, -7, 9, -77, -76, -72,
                -125, -36, 62, 9, 29, 17, -121, 71, -2, -54, 71, 84, 99, 127, -62, 7, 37, 2, 21, -31, 94, 75, 17, -57,
                -1, 112, -27, 99, 81, 114, -85, -110, 50, 115, -77, 85, 9, 21, 91, 2, -22, -103, 43, 3, 38, -20, 94,
                -118, -30, -16, 2, -105, -7, 53, 48, 105, 86, 87, 100, 122, 112, -91, -119, 47, 35, -95, 102, 6, -1,
                2, -78, 15, -114, 2, 107, -57, 0, -18, 103, 89, 47, -81, 87, 32, -77, 45, 108, 102, -118, -38, -102, 5,
                -38, -60, -30, 28, -108, 99, -89, 45, -126, -58, 30, 118, 107, -85, -99, 73, -5, 17, 124, 112, 46, 111,
                117, -128, 31, 38, -107, 52, -110, -90, -15, -35, 8, 18, -64, -103, 81, -92, 121, 105, -68, 90, 67, 39, -22,
                -119, -91, -72, 122, 48, -40, -50, 111, -92, 84, -122, -10, 41, -123, -35, 29, -9, -54, -92, 73, 70, -104,
                -105, -108, 38, 53, -2, -79, 2, -127, -127, 0, -27, 40, 80, -87, 55, 60, -1, -67, -83, 55, -1, -10, 36,
                -76, 111, -70, 56, 113, -19, 78, 123, 19, 77, -29, 10, 75, 31, 68, 116, 3, -47, -74, -107, -84, -17, 118,
                71, -97, 56, -65, 94, 61, -73, 101, 107, -128, -89, -81, 89, 47, 110, 54, -37, 91, -58, 62, -24, 97, -73,
                38, -43, 74, 75, -53, 79, -77, 111, -62, 81, -77, -33, 78, 116, -113, -68, -9, 97, -49, 28, -92, 20, -15,
                42, -84, 86, 76, 75, 40, -64, 127, -18, -43, 55, 39, 45, 41, -35, -128, -23, -58, 106, -105, -43, -22, -102,
                70, -55, -17, -21, -109, 84, 71, 79, -6, 68, 51, -107, 25, 42, 79, 25, 60, 48, -127, 4, 3, -15, 43, 2, -127,
                -127, 0, -54, 26, -120, -49, -16, 86, -99, -14, -50, 23, 16, -49, 55, 4, 31, 8, -20, 79, -115, 103, 89, -71,
                120, -15, 65, -82, -97, -29, 31, 50, -85, 95, 54, 6, 48, -20, -114, -97, 65, 78, -42, 12, -7, 3, 36, -127,
                58, 101, 14, -102, -92, -128, 103, -110, -121, -71, 125, 32, -15, -8, 26, -96, 60, -86, -24, 77, -92, 95,
                66, -3, -14, 107, 62, -84, 122, -34, 37, -43, 36, -70, -94, -82, 119, 24, 100, -62, 5, -19, -6, -119, -25
                , -34, -119, 11, 65, -10, -121, -33, 39, 8, 88, -12, 8, 124, 78, 69, -113, 22, 112, -66, -37, 32, 111, 93,
                -19, 127, -106, 85, -97, 72, -44, 118, -20, -23, 70, 58, -78, -103, 2, -127, -128, 4, -73, -120, -71,
                -126, -10, -111, 121, 17, 8, -73, 15, 21, 0, 85, -48, 106, 123, 5, -36, 120, -78, 20, 7, 89, 93, -28
                , 125, -33, 57, 120, -26, -98, 60, 26, 13, -64, 57, -87, -52, 94, -97, 60, -104, 44, -41, 80, 28, 78, -28,
                -88, 11, 31, 6, -53, -108, -35, -23, -83, 0, 114, -95, 125, -33, -24, 123, 20, 16, -42, -25, -79, -16, 6, 124,
                50, -14, -112, -93, -41, -66, 20, -28, 69, -15, -104, 49, 39, -42, -27, -25, 124, 62, 13, 77, -108, 35,
                105, 120, -50, -41, 19, -72, -98, 97, -98, -35, 88, 40, -59, 27, -102, 63, -127, -120, -16, 64, 115, -112,
                58, 11, -72, -60, 65, 103, 86, 114, -46, 83, 2, -127, -128, 33, -116, 124, 15, -128, -122, -16, 91, 64, -24,
                -59, 74, -112, -120, 103, -102, 116, -105, -44, 18, 70, -21, -87, -18, -22, -121, 81, 85, 67, 117, -122,
                -53, 28, 77, -76, -53, 95, -11, -127, -62, -45, 119, 125, -46, -114, -71, -64, -10, -53, -112, -46, 35,
                -38, -115, -111, 104, 50, 36, 72, -26, 117, -115, 9, -27, 25, 0, -30, 123, 73, -60, -101, -4, 63, -128, 36,
                57, 66, 48, 122, -109, 92, -77, -9, 99, 30, 62, -78, -5, 73, 46, -95, 69, -24, -101, -20, -52, 62, -53,
                -59, 79, -10, 108, 52, 8, 102, 121, -41, -127, -45, 31, 107, -126, -101, -38, 12, -75, -76, 63, -20, 92, 11,
                -96, -34, 60, -17, 2, -119, 105, 2, -127, -127, 0, -106, 21, -25, -115, -6, 36, 3, 87, -117, -96, -70,
                35, 28, -66, 49, 18, -47, 59, -19, 73, 15, 40, 99, -47, 81, -65, -121, -53, 30, 106, 4, -12, 80, 46, 11,
                79, -58, 127, -5, -1, -123, -111, 57, 98, 21, 9, 101, -66, -63, -111, -49, -50, 51, 108, -107, -103, -9,
                100, 113, -110, -116, -68, 123, -103, 88, -21, -23, 20, -50, 109, -93, -47, 31, -85, 18, -26, -101, -33,
                -66, -52, -75, -17, -37, 8, 122, -54, 4, -24, -16, -13, -30, 125, 20, -22, -25, 39, 121, -42, 95, 1, 112,
                45, -63, 99, 46, -91, 75, 124, 78, -94, -128, 101, -99, -114, 90, -60, 122, -8, 108, 32, -117, 14, 119, -29, -83, -18, -25, -82};
        EtxCommonMatchers.assertThat(entries.get(1).getAlias(), Matchers.is("key2"));
        EtxCommonMatchers.assertThat(entries.get(1).getX509Certificate(), Matchers.is(Matchers.notNullValue()));
        EtxCommonMatchers.assertThat(dnNamesToEntrySet(entries.get(1).getX509Certificate().getSubjectDN().toString()), everyItem(isIn( dnNamesToEntrySet("C=ro,ST=valer,L=valer,O=valer,OU=B1,CN=second"))));
        EtxCommonMatchers.assertThat(entries.get(1).getKeyEntry(), Matchers.is(Matchers.instanceOf(PrivateKey.class)));
        EtxCommonMatchers.assertThat(entries.get(1).getKeyEntry().getAlgorithm(), Matchers.is("RSA"));
        EtxCommonMatchers.assertThat(entries.get(1).getKeyEntry().getEncoded(), Matchers.is(Matchers.equalTo(key2Value)));
    }

    @Test
    public void testGetKeyStoreEntries_UnknownKeyType() throws Exception {
        URI fileURI = this.getClass().getResource("/testStore.p12").toURI();

        List<KeyStoreEntry<DSAPrivateKey>> entries;
        try (InputStream inputStream = Files.newInputStream(Paths.get(fileURI))) {
            entries = certificateService.getKeyStoreEntries(inputStream, "PKCS12", "test123".toCharArray(), DSAPrivateKey.class);
        }

        EtxCommonMatchers.assertThat(entries, Matchers.is(Matchers.notNullValue()));
        EtxCommonMatchers.assertThat(entries.size(), Matchers.is(0));
    }

    private Set<Map.Entry<String, String>> dnNamesToEntrySet(String dnNames) {
        Map<String, String> map = new HashMap<>();

        for(String pair: dnNames.split(",")) {
            String[] entry = pair.split("=");
            map.put(entry[0].trim(), entry[1].trim());
        }

        return map.entrySet();
    }

}
