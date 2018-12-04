package eu.europa.ec.etrustex.webaccess.security;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;


/**
 * @author: micleva
 * @created: 5/4/12
 * @project ETX
 */
public interface CryptoService {

    int AES_BLOCK_SIZE = 256;

    byte[] genAes256Key() throws Exception;

    byte[] symmetricAESEncrypt(byte[] aesKey, byte[] input) throws Exception;

    byte[] symmetricAESDecrypt(byte[] aesKey, byte[] input) throws Exception;

    byte[] asymmetricEncrypt(PublicKey publicKey, byte[] toEncodeData) throws Exception;

    byte[] asymmetricDecrypt(PrivateKey privateKey, byte[] toDecodeData) throws Exception;

    OutputStream getCipherOutputStream(OutputStream os, byte[] aes256Key, boolean forEncryption);

    InputStream getCipherInputStream(InputStream inputStream, byte[] aes256Key, boolean forEncryption);

    long getEncryptedOutputSize(long length, byte[] aes256Key);

    PrivateKey loadPrivateKey(byte[] encodedKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException;

    void decryptFile(Path encryptedFile, Path destinationFile, byte[] aes256EncryptedKey, PrivateKey decryptionKey) throws Exception;
}
