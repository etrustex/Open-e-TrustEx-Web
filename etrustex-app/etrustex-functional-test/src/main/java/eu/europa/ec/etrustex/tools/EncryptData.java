package eu.europa.ec.etrustex.tools;

import eu.europa.ec.etrustex.webaccess.security.CryptoService;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.util.encoders.Base64;

import java.io.*;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class EncryptData {
    private static CryptoService cryptoService;

    static {
        EtxSecurityProvider.init();
        cryptoService = EtxSecurityProvider.getInstance().getCryptoService();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            printUsage();
            return;
        }

        String publicKeyFile = args[0];
        PublicKey publicKey = loadKey(publicKeyFile);
        if (publicKey == null) {
            System.err.println("Could not load the public key from key file " + publicKeyFile);
            return;
        }

        String dataToEncrypt = args[1];

        byte[] encryptedContent = cryptoService.asymmetricEncrypt(publicKey, dataToEncrypt.getBytes());
        System.out.println("<encrypted>" + new String(Hex.encodeHex(encryptedContent)) + "</encrypted>");
    }

    private static PublicKey loadKey(String certificateFile) throws Exception {
        byte[] base64EncodedPublicKey = readFileContent(certificateFile);

        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(base64EncodedPublicKey)));
    }

    private static byte[] readFileContent(String filePath) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        InputStream certInputStream = new FileInputStream(filePath);
        while ((length = certInputStream.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, length);
        }

        return baos.toByteArray();
    }

    private static void printUsage() {
        StringBuilder usage = new StringBuilder();
        usage.append("Usage: ").append(EncryptData.class.getName()).append(" <keyFile> <dataToEncrypt>");
        usage.append('\n').append("Where:");
        usage.append('\n').append("<keyFile> - the file containing the public key");
        usage.append('\n').append("<dataToEncrypt> - The data to be encrypted");

        System.out.println(usage.toString());
    }
}
