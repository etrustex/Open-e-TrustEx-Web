package eu.europa.ec.etrustex.webaccess.model.converters;


import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.vo.ExtendedCertificateData;
import org.junit.Test;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;

public class ExtendedCertificateDataConverterTest extends AbstractTest {

    @Test
    public void testConvertToDatabaseColumn() throws Exception {
        byte[] keyValue = new byte[]{48, -126, 1, 34, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1,
                15, 0, 48, -126, 1, 10, 2, -126, 1, 1, 0, -40, -72, 14, -64, 73, 52, 31, -94, 22, -59, -86, 9, -88, 44, 21,
                98, 6, -44, -29, -97, 57, 99, -64, 123, -71, 58, -46, 102, 59, 82, 15, 5, 1, 113, 84, -51, 49, -45, 8, -16,
                -22, 17, 123, -26, 101, 38, -7, 33, 98, 114, 28, -13, -66, -5, -118, -88, 25, -57, 117, 12, -105, -2, -45, 70,
                62, 18, 42, 13, -75, 52, 62, 32, 81, -51, -24, -34, 78, -8, -106, 106, 77, 45, 100, -3, 108, 4, -85, 8,
                -60, -98, -50, 51, 96, -90, 23, 119, -61, 25, 10, 120, -42, -13, -111, -109, 64, -75, -103, -90, 87, 113,
                100, -99, -4, 25, -52, 12, -79, 102, -25, 76, -96, 100, -78, -105, 45, -109, 87, 8, 92, -88, 35, 34, -99,
                58, 84, 8, -110, 96, -110, -64, -74, -100, -97, 18, -63, -72, 117, -19, 108, -75, 122, -54, 31, -117, -5,
                106, 6, 78, 111, 100, -86, 30, 91, 25, 52, 29, 114, 64, -46, 39, 80, -32, -64, -26, -30, -71, 73, -91,
                -52, -36, -87, 83, -103, 112, -31, 10, -102, -73, 35, 50, 96, -68, 44, -51, 5, 20, -55, 45, -81, -6, -104,
                83, 63, 77, 126, 71, -54, 87, 69, -21, 125, -12, 14, -75, 21, -59, 127, 65, -78, 65, 5, 24, 55, -37, 0, -9,
                -74, -43, -8, 120, 55, 108, 94, -126, 34, -1, -1, 123, -50, -127, 89, 72, 20, 20, 29, -113, -22, 10, -85,
                -57, -103, -57, 42, -105, -117, -25, 2, 3, 1, 0, 1};

        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(keyValue);
        PublicKey publicKey = kf.generatePublic(x509Spec);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.DAY_OF_MONTH, 12);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.HOUR_OF_DAY, 15);
        cal.set(Calendar.MINUTE, 20);
        cal.set(Calendar.SECOND, 35);
        cal.set(Calendar.ZONE_OFFSET, 2);

        Date date = cal.getTime();

        ExtendedCertificateData ecd = new ExtendedCertificateData(publicKey,
                "subject DN",
                "issuer DN",
                date,
                date,
                "serial NR",
                "a sig ALG",
                "a version");

        String result = ExtendedCertificateDataConverter.getInstance().convertToDatabaseColumn(ecd);

        assertThat(result, is("{" +
                "\"startDate\":\"12-03-2017 04:20:35 PM +0100\"," +
                "\"endDate\":\"12-03-2017 04:20:35 PM +0100\"," +
                "\"subjectDn\":\"subject DN\"," +
                "\"signatureAlgorithm\":\"a sig ALG\"," +
                "\"issuerDn\":\"issuer DN\"," +
                "\"version\":\"a version\"," +
                "\"serialNumber\":\"serial NR\"," +
                "\"publicKeyAlgorithm\":\"RSA\"," +
                "\"publicKey\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2LgOwEk0H6IWxaoJqCwVYgbU4585Y8B7uTrSZjtSDwUBcVTNMdMI8OoRe+ZlJvkhYnIc8777iqgZx3UMl/7TRj4SKg21ND4gUc3o3k74lmpNLWT9bASrCMSezjNgphd3wxkKeNbzkZNAtZmmV3FknfwZzAyxZudMoGSyly2TVwhcqCMinTpUCJJgksC2nJ8Swbh17Wy1esofi/tqBk5vZKoeWxk0HXJA0idQ4MDm4rlJpczcqVOZcOEKmrcjMmC8LM0FFMktr/qYUz9NfkfKV0XrffQOtRXFf0GyQQUYN9sA97bV+Hg3bF6CIv//e86BWUgUFB2P6gqrx5nHKpeL5wIDAQAB\"}"));
    }

    @Test
    public void testConvertToDatabaseColumn_when_null() throws Exception {

        String result = ExtendedCertificateDataConverter.getInstance().convertToDatabaseColumn(null);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void testConvertToEntityAttribute() throws Exception {
        byte[] keyValue = new byte[]{48, -126, 1, 34, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1,
                15, 0, 48, -126, 1, 10, 2, -126, 1, 1, 0, -40, -72, 14, -64, 73, 52, 31, -94, 22, -59, -86, 9, -88, 44, 21,
                98, 6, -44, -29, -97, 57, 99, -64, 123, -71, 58, -46, 102, 59, 82, 15, 5, 1, 113, 84, -51, 49, -45, 8, -16,
                -22, 17, 123, -26, 101, 38, -7, 33, 98, 114, 28, -13, -66, -5, -118, -88, 25, -57, 117, 12, -105, -2, -45, 70,
                62, 18, 42, 13, -75, 52, 62, 32, 81, -51, -24, -34, 78, -8, -106, 106, 77, 45, 100, -3, 108, 4, -85, 8,
                -60, -98, -50, 51, 96, -90, 23, 119, -61, 25, 10, 120, -42, -13, -111, -109, 64, -75, -103, -90, 87, 113,
                100, -99, -4, 25, -52, 12, -79, 102, -25, 76, -96, 100, -78, -105, 45, -109, 87, 8, 92, -88, 35, 34, -99,
                58, 84, 8, -110, 96, -110, -64, -74, -100, -97, 18, -63, -72, 117, -19, 108, -75, 122, -54, 31, -117, -5,
                106, 6, 78, 111, 100, -86, 30, 91, 25, 52, 29, 114, 64, -46, 39, 80, -32, -64, -26, -30, -71, 73, -91,
                -52, -36, -87, 83, -103, 112, -31, 10, -102, -73, 35, 50, 96, -68, 44, -51, 5, 20, -55, 45, -81, -6, -104,
                83, 63, 77, 126, 71, -54, 87, 69, -21, 125, -12, 14, -75, 21, -59, 127, 65, -78, 65, 5, 24, 55, -37, 0, -9,
                -74, -43, -8, 120, 55, 108, 94, -126, 34, -1, -1, 123, -50, -127, 89, 72, 20, 20, 29, -113, -22, 10, -85,
                -57, -103, -57, 42, -105, -117, -25, 2, 3, 1, 0, 1};

        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(keyValue);
        PublicKey publicKey = kf.generatePublic(x509Spec);

        String dbCertificateData = "{" +
                "   \"publicKey\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2LgOwEk0H6IWxaoJqCwVYgbU4585Y8B7uTrSZjtSDwUBcVTNMdMI8OoRe+ZlJvkhYnIc8777iqgZx3UMl/7TRj4SKg21ND4gUc3o3k74lmpNLWT9bASrCMSezjNgphd3wxkKeNbzkZNAtZmmV3FknfwZzAyxZudMoGSyly2TVwhcqCMinTpUCJJgksC2nJ8Swbh17Wy1esofi/tqBk5vZKoeWxk0HXJA0idQ4MDm4rlJpczcqVOZcOEKmrcjMmC8LM0FFMktr/qYUz9NfkfKV0XrffQOtRXFf0GyQQUYN9sA97bV+Hg3bF6CIv//e86BWUgUFB2P6gqrx5nHKpeL5wIDAQAB\",\n" +
                "   \"publicKeyAlgorithm\":\"RSA\"," +
                "   \"startDate\":\"13-03-2017 02:42:09 PM +0100\"," +
                "   \"endDate\":\"13-03-2017 02:42:09 PM +0100\"," +
                "   \"subjectDn\":\"subject DN\"," +
                "   \"signatureAlgorithm\":\"a sig ALG\"," +
                "   \"issuerDn\":\"issuer DN\"," +
                "   \"version\":\"a version\"," +
                "   \"serialNumber\":\"serial NR\"" +
                "}";

        ExtendedCertificateData ecd = ExtendedCertificateDataConverter.getInstance().convertToEntityAttribute(dbCertificateData);

        assertThat(ecd.getPublicKey(), is(equalTo(publicKey)));
        assertThat(ecd.getIssuerDN(), is("issuer DN"));
        assertThat(ecd.getSubjectDN(), is("subject DN"));
        assertThat(ecd.getSignatureAlgorithm(), is("a sig ALG"));
        assertThat(ecd.getVersion(), is("a version"));
        assertThat(ecd.getSerialNumber(), is("serial NR"));
    }

    @Test
    public void testConvertToLightEntityAttribute() throws Exception {
        String dbCertificateData = "{" +
                "   \"publicKey\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2LgOwEk0H6IWxaoJqCwVYgbU4585Y8B7uTrSZjtSDwUBcVTNMdMI8OoRe+ZlJvkhYnIc8777iqgZx3UMl/7TRj4SKg21ND4gUc3o3k74lmpNLWT9bASrCMSezjNgphd3wxkKeNbzkZNAtZmmV3FknfwZzAyxZudMoGSyly2TVwhcqCMinTpUCJJgksC2nJ8Swbh17Wy1esofi/tqBk5vZKoeWxk0HXJA0idQ4MDm4rlJpczcqVOZcOEKmrcjMmC8LM0FFMktr/qYUz9NfkfKV0XrffQOtRXFf0GyQQUYN9sA97bV+Hg3bF6CIv//e86BWUgUFB2P6gqrx5nHKpeL5wIDAQAB\",\n" +
                "   \"publicKeyAlgorithm\":\"RSA\"," +
                "   \"startDate\":\"13-03-2017 02:42:09 PM +0100\"," +
                "   \"endDate\":\"13-03-2017 02:42:09 PM +0100\"," +
                "   \"subjectDn\":\"subject DN\"," +
                "   \"signatureAlgorithm\":\"a sig ALG\"," +
                "   \"issuerDn\":\"issuer DN\"," +
                "   \"version\":\"a version\"," +
                "   \"serialNumber\":\"serial NR\"" +
                "}";

        ExtendedCertificateData ecd = ExtendedCertificateDataConverter.getInstance().convertToLightEntityAttribute(dbCertificateData);

        assertThat(ecd.getPublicKey(), nullValue());
        assertThat(ecd.getIssuerDN(), is("issuer DN"));
        assertThat(ecd.getSubjectDN(), is("subject DN"));
        assertThat(ecd.getSignatureAlgorithm(), is("a sig ALG"));
        assertThat(ecd.getVersion(), is("a version"));
        assertThat(ecd.getSerialNumber(), is("serial NR"));
    }

}