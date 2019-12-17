package eu.europa.ec.etrustex.webaccess.security;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.internal.util.reflection.Whitebox.getInternalState;

public class BouncyCryptoImplTest extends AbstractTest {

    private BouncyCryptoImpl cryptoAES;
    private CryptoService cryptoService;

    @Override
    protected void onSetUp() {
        EtxSecurityProvider.init();
        EtxSecurityProvider.getInstance();
        cryptoAES = new BouncyCryptoImpl();
        cryptoService = EtxSecurityProvider.getInstance().getCryptoService();
    }

    @Test
    public void testGetOutputSize() throws Exception {

        byte[] aes256Key = cryptoService.genAes256Key();
        Random random = new Random();
        for (int fileSize = 0; fileSize < 5000; fileSize++) {
            byte[] data = new byte[fileSize];
            random.nextBytes(data);
            long encryptedFileSize = cryptoService.getEncryptedOutputSize(fileSize, aes256Key);

            byte[] encryptedData = cryptoService.symmetricAESEncrypt(aes256Key, data);
            assertThat(encryptedData.length, is((int)encryptedFileSize));
        }
    }

    @Test
    public void testEncryptWithAES() throws Exception {
        String msg = "MessageBundle";

        byte[] aesKey = new byte[]{-123, -94, 125, -39, 113, -102, 78, -43, 97, -66, -90, -16, 40, -80, -44, -118, 39, -66, 68, -75, 79, 41, -70, -11, 4, -3, 99, 69, -112, -8, -32, 81};

        byte[] result = cryptoAES.symmetricAESEncrypt(aesKey, msg.getBytes());

        byte[] expectedResult = new byte[]{-45, -34, 113, 126, 91, -114, 29, 21, -111, -103, 96, 7, -5, -20, 81, 22};

        assertThat(result, is(expectedResult));
    }

    @Test
    public void testDecryptWithAES() throws Exception {
        byte[] aesKey = new byte[]{-123, -94, 125, -39, 113, -102, 78, -43, 97, -66, -90, -16, 40, -80, -44, -118, 39,
                -66, 68, -75, 79, 41, -70, -11, 4, -3, 99, 69, -112, -8, -32, 81};
        byte[] encryptedMessage = new byte[]{-45, -34, 113, 126, 91, -114, 29, 21, -111, -103, 96, 7, -5, -20, 81, 22};

        byte[] result = cryptoAES.symmetricAESDecrypt(aesKey, encryptedMessage);

        String expectedResult = "MessageBundle";

        assertThat(new String(result), is(expectedResult));
    }

    @Test
    public void testEncrypt() throws Exception {
        String msg = "MessageBundle";

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

        KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(keyValue);
        PublicKey publicKey = kf.generatePublic(x509Spec);

        byte[] result = cryptoAES.asymmetricEncrypt(publicKey, msg.getBytes());

        assertThat(result.length, is(256));
    }

    @Test
    public void testEncryptWithMatchingKey() throws Exception {
        String msg = "This is my message for test";

        KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(matchingPublicKeyValue);
        PublicKey publicKey = kf.generatePublic(x509Spec);

        byte[] result = cryptoAES.asymmetricEncrypt(publicKey, msg.getBytes());

        assertThat(result.length, is(256));
    }

    @Test
    public void testEncryptDecryptAes256Key() throws Exception {

        KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(matchingPublicKeyValue);
        PublicKey publicKey = kf.generatePublic(x509Spec);

        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(matchingPrivateKeyValue);
        PrivateKey pk = kf.generatePrivate(ks);

        byte[] dataToEncrypt = new byte[100];

        for (int i = 0; i < 10; i++) {
            dataToEncrypt[i] = 0;
        }

        for (int i = 10; i < 20; i++) {
            dataToEncrypt[i] = (byte) i;
        }

        for (int i = 30; i < 100; i++) {
            dataToEncrypt[i] = 0;
        }

        byte[] encryptedResult = cryptoAES.asymmetricEncrypt(publicKey, dataToEncrypt);

        byte[] decryptedResult = cryptoAES.asymmetricDecrypt(pk, encryptedResult);

        assertThat(decryptedResult, is(equalTo(dataToEncrypt)));
    }

    static byte[] matchingPublicKeyValue = new byte[]{48, -126, 1, 34, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1, 15, 0,
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

    static byte[] matchingPrivateKeyValue = new byte[]{48, -126, 4, -67, 2, 1, 0, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 4,
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

    @Test
    public void testDecryptWithMatchingKey() throws Exception {


        KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(matchingPrivateKeyValue);
        PrivateKey pk = kf.generatePrivate(ks);

        byte[] encryptedData = new byte[]{126, -67, -16, -13, 127, 98, -86, -98, 55, -57, 18, 40, 125, -71, 100, -93, 34, -40, -44, 0, -89, 113, -15,
                -120, 11, -30, 70, 7, 20, 101, -1, -56, -34, -39, 65, -90, -20, -20, -85, -27, 79, 34, -7, 13, -106, -57, -98, -20, 33, -39, 71, 21,
                -70, 51, -124, -39, 13, 21, -40, 85, 75, 125, 75, -104, 27, -61, 22, 74, 122, 74, 56, 72, -74, 12, -15, -5, 39, -113, 9,
                -77, -120, 74, -5, -43, -80, 16, -75, 99, 124, -43, -50, -75, 126, -16, -98, -18, -47, 118, -86, -43, -60, -44, 112, -24,
                -126, 93, 80, -128, 49, -33, 56, -118, -3, 33, 34, 4, 18, 57, 119, 98, 57, 17, -66, -33, -107, -40, -106, -36, 63, 52, -73,
                53, -1, 54, 15, -74, 92, -49, 73, -99, -104, -26, 101, 14, -38, -36, 114, -31, -110, 114, -93, 33, 77, 113, -25, -63,
                -90, -117, -77, -128, -13, 107, -2, 117, 66, 71, 98, -31, -115, -33, -13, -96, 33, -35, -119, 77, 83, 107, -128, 96, 113,
                -119, -11, -46, 70, -103, -110, 43, 87, 66, -45, 46, -96, -36, 10, -115, -41, 103, 124, -34, -101, -72, -55, 116, 66,
                -82, -78, 57, -55, -62, -51, 0, 124, 47, -54, -104, -96, -89, 67, -26, 27, -2, 87, 126, -51, -9, -6, 0, 23, -16, -39,
                -73, -61, -42, 98, 8, 22, -45, 32, -14, 103, 26, -50, -104, 78, 120, -9, -8, -116, 13, 24, -76, -8, 20, -70, -73};

        byte[] result = cryptoAES.asymmetricDecrypt(pk, encryptedData);

        String expectedResult = "This is my message for test";
        assertThat(new String(result), is(expectedResult));
    }

    @Test
    public void testGetCipherOutputStream() throws Exception {

        byte[] aesKey = new byte[]{-123, -94, 125, -39, 113, -102, 78, -43, 97, -66, -90, -16, 40, -80, -44, -118, 39,
                -66, 68, -75, 79, 41, -70, -11, 4, -3, 99, 69, -112, -8, -32, 81};

        OutputStream outputStream = mock(OutputStream.class);

        OutputStream cipherStream = cryptoAES.getCipherOutputStream(outputStream, aesKey, true);

        assertThat(cipherStream, is(instanceOf(CipherOutputStream.class)));

        assertThat((OutputStream) getInternalState(cipherStream, "out"), is(sameInstance(outputStream)));
        BufferedBlockCipher bufferedBlockCipher = (BufferedBlockCipher) getInternalState(cipherStream, "bufferedBlockCipher");

        assertThat(bufferedBlockCipher, is(notNullValue()));

        //verify that we have a AES/CBC/PKCS7Padding
        assertThat(bufferedBlockCipher, is(instanceOf(PaddedBufferedBlockCipher.class)));
        assertThat(getInternalState(bufferedBlockCipher, "padding"), is(instanceOf(PKCS7Padding.class)));
        assertThat(bufferedBlockCipher.getUnderlyingCipher(), is(instanceOf(CBCBlockCipher.class)));
        assertThat(bufferedBlockCipher.getUnderlyingCipher().getAlgorithmName(), is("AES/CBC"));
        assertThat(((CBCBlockCipher) bufferedBlockCipher.getUnderlyingCipher()).getUnderlyingCipher(), is(instanceOf(AESEngine.class)));
        assertThat((Boolean) getInternalState(((CBCBlockCipher) bufferedBlockCipher.getUnderlyingCipher()).getUnderlyingCipher(), "forEncryption"), is(true));
    }

    @Test
    public void testGetCipherInputStream() throws Exception {

        byte[] aesKey = new byte[]{-123, -94, 125, -39, 113, -102, 78, -43, 97, -66, -90, -16, 40, -80, -44, -118, 39, -66,
                68, -75, 79, 41, -70, -11, 4, -3, 99, 69, -112, -8, -32, 81};

        InputStream inputStream = mock(InputStream.class);

        InputStream cipherStream = cryptoAES.getCipherInputStream(inputStream, aesKey, false);

        assertThat(cipherStream, is(instanceOf(CipherInputStream.class)));

        assertThat((InputStream) getInternalState(cipherStream, "in"), is(sameInstance(inputStream)));
        BufferedBlockCipher bufferedBlockCipher = (BufferedBlockCipher) getInternalState(cipherStream, "bufferedBlockCipher");

        assertThat(bufferedBlockCipher, is(notNullValue()));

        //verify that we have a AES/CBC/PKCS7Padding
        assertThat(bufferedBlockCipher, is(instanceOf(PaddedBufferedBlockCipher.class)));
        assertThat(getInternalState(bufferedBlockCipher, "padding"), is(instanceOf(PKCS7Padding.class)));
        assertThat(bufferedBlockCipher.getUnderlyingCipher(), is(instanceOf(CBCBlockCipher.class)));
        assertThat(bufferedBlockCipher.getUnderlyingCipher().getAlgorithmName(), is("AES/CBC"));
        assertThat(((CBCBlockCipher) bufferedBlockCipher.getUnderlyingCipher()).getUnderlyingCipher(), is(instanceOf(AESEngine.class)));
        assertThat((Boolean) getInternalState(((CBCBlockCipher) bufferedBlockCipher.getUnderlyingCipher()).getUnderlyingCipher(), "forEncryption"), is(false));
    }

}
