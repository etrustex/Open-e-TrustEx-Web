package eu.europa.ec.etrustex.webaccess.security;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author: micleva
 * @created: 5/4/12
 * @project ETX
 */
class BouncyCryptoImpl implements CryptoService {
    public byte[] genAes256Key() throws NoSuchProviderException, NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES", EtxSecurityProvider.getInstance().getDefaultSecurityProvider());
        kgen.init(AES_BLOCK_SIZE); // 192 and 256 bits may not be available

        // Generate the secret key specs.
        SecretKey skey = kgen.generateKey();
        return skey.getEncoded();
    }

    public byte[] symmetricAESEncrypt(byte[] aesKey, byte[] input) throws Exception {

        return processAES256(input, aesKey, true);
    }

    public byte[] symmetricAESDecrypt(byte[] aesKey, byte[] input) throws Exception {

        return processAES256(input, aesKey, false);
    }

    private byte[] processAES256(byte[] input, byte[] key, boolean forEncryption) throws InvalidCipherTextException {

        BufferedBlockCipher bufferedBlockCipher = buildAesBufferedBlockCipher(key, forEncryption);

        return process(input, bufferedBlockCipher);
    }

    private BufferedBlockCipher buildAesBufferedBlockCipher(byte[] key, boolean forEncryption) {
        /*
        * A full list of BlockCiphers can be found at http://www.bouncycastle.org/docs/docs1.6/org/bouncycastle/crypto/BlockCipher.html
        */
        BlockCipher blockCipher = new AESEngine();
        CBCBlockCipher cbcBlockCipher = new CBCBlockCipher(blockCipher);

        /*
        * Paddings available (http://www.bouncycastle.org/docs/docs1.6/org/bouncycastle/crypto/paddings/BlockCipherPadding.html):
        *   - ISO10126d2Padding
        *   - ISO7816d4Padding
        *   - PKCS7Padding
        *   - TBCPadding
        *   - X923Padding
        *   - ZeroBytePadding
        */
        BlockCipherPadding blockCipherPadding = new PKCS7Padding();

        BufferedBlockCipher bufferedBlockCipher = new PaddedBufferedBlockCipher(cbcBlockCipher, blockCipherPadding);

        CipherParameters cipherParameters = new KeyParameter(key);
        bufferedBlockCipher.init(forEncryption, cipherParameters);
        return bufferedBlockCipher;
    }

    private byte[] process(byte[] input, BufferedBlockCipher bufferedBlockCipher) throws InvalidCipherTextException {

        int inputOffset = 0;
        int inputLength = input.length;

        int maximumOutputLength = bufferedBlockCipher.getOutputSize(inputLength);
        byte[] output = new byte[maximumOutputLength];
        int outputOffset = 0;
        int outputLength = 0;

        int bytesProcessed;

        bytesProcessed = bufferedBlockCipher.processBytes(
                input, inputOffset, inputLength,
                output, outputOffset
        );
        outputOffset += bytesProcessed;
        outputLength += bytesProcessed;

        bytesProcessed = bufferedBlockCipher.doFinal(output, outputOffset);
        outputOffset += bytesProcessed;
        outputLength += bytesProcessed;

        if (outputLength == output.length) {
            return output;
        } else {
            byte[] truncatedOutput = new byte[outputLength];
            System.arraycopy(
                    output, 0,
                    truncatedOutput, 0,
                    outputLength
            );
            return truncatedOutput;
        }
    }

    public byte[] asymmetricEncrypt(PublicKey publicKey, byte[] toEncodeData) throws Exception {
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm() + "/ECB/PKCS1Padding", EtxSecurityProvider.getInstance().getDefaultSecurityProvider());

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(toEncodeData);
    }

    public byte[] asymmetricDecrypt(PrivateKey privateKey, byte[] toDecodeData) throws Exception {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm() + "/ECB/PKCS1Padding", EtxSecurityProvider.getInstance().getDefaultSecurityProvider());

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(toDecodeData);
    }

    @Override
    public OutputStream getCipherOutputStream(OutputStream os, byte[] aes256Key, boolean forEncryption) {
        BufferedBlockCipher bufferedBlockCipher = buildAesBufferedBlockCipher(aes256Key, forEncryption);

        return new CipherOutputStream(os, bufferedBlockCipher);
    }

    @Override
    public InputStream getCipherInputStream(InputStream inputStream, byte[] aes256Key, boolean forEncryption) {
        BufferedBlockCipher bufferedBlockCipher = buildAesBufferedBlockCipher(aes256Key, forEncryption);

        return new CipherInputStream(inputStream, bufferedBlockCipher);
    }

    @Override
    public long getEncryptedOutputSize(long length, byte[] aes256Key) {
        BufferedBlockCipher bufferedBlockCipher = buildAesBufferedBlockCipher(aes256Key, true);

        return bufferedBlockCipher.getOutputSize((int) length);
    }

    @Override
    public PrivateKey loadPrivateKey(byte[] encodedKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedKey);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePrivate(privateKeySpec);
    }

    @Override
    public void decryptFile(Path encryptedPath, Path destinationPath, byte[] aes256EncryptedKey, PrivateKey decryptionKey) throws Exception {
        final byte[] aes256Key = asymmetricDecrypt(decryptionKey, aes256EncryptedKey);

        //wrap the output stream into an output stream with decryption capabilities
        //the data will be decrypted as they are written
        try (OutputStream outputStream = getCipherOutputStream(Files.newOutputStream(destinationPath), aes256Key, false)) {
            Files.copy(encryptedPath, outputStream);
        }
    }
}
