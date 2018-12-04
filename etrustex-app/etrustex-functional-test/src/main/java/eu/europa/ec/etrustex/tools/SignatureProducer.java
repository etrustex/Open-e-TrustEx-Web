package eu.europa.ec.etrustex.tools;

import eu.europa.ec.etrustex.webaccess.security.CertificateService;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import eu.europa.ec.etrustex.webaccess.security.KeyStoreEntry;
import eu.europa.ec.etrustex.webaccess.security.SignatureService;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.List;

/**
 * @author: micleva
 * @date: 8/9/12 10:46 AM
 * @project: ETX
 */
public class SignatureProducer {
    private static SignatureService signatureService;

    static {
        EtxSecurityProvider.init();
        signatureService = EtxSecurityProvider.getInstance().getSignatureService();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            printUsage();
            return;
        }

        String keyStoreFile = args[0];
        String storePass = args[1];
        String keyAlias = args[2];
        String signedDataPath = args[3];

        produce(keyStoreFile, storePass, keyAlias, signedDataPath);
    }

    static void produce(String keyStoreFile, String storePass, String keyAlias, String signedDataPath) throws Exception {
        KeyStoreEntry<PrivateKey> keyEntry = loadKey(keyStoreFile, storePass, keyAlias);
        if (keyEntry == null) {
            System.err.println("Key store entry " + keyAlias + " from key store file: " + keyStoreFile + " NOT FOUND!");
            return;
        }

        String signedData = getFileContent(signedDataPath).trim();

        //normalize the signature data content as per http://www.w3.org/TR/REC-xml/#sec-line-ends) when reading it from local file system
        signedData = signedData.replaceAll(System.getProperty("line.separator"), "\n");

        Element signatureElement = signatureService.signData(signedData, keyEntry.getKeyEntry(), keyEntry.getX509Certificate());

        String signatureFileName = signedDataPath.substring(0, signedDataPath.lastIndexOf('.'));
        signatureFileName += ".sig";

        outputNodeDocument(signatureElement, signatureFileName);

        Path signatureFile = Paths.get(signatureFileName);
        if (Files.exists(signatureFile)) {
            System.out.println("Signature successfully written in the file: " + signatureFile.toString());
        } else {
            System.out.println("Failed to write signature in the file: " + signatureFile.toString());
        }
    }

    protected static void outputNodeDocument(Node node, String fileName) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            tf.newTransformer().transform(
                    new DOMSource(node),
                    new StreamResult(out));
        }
    }

    private static String getFileContent(String signedDataPath) throws Exception {
        FileReader in = new FileReader(signedDataPath);
        StringBuilder contents = new StringBuilder();
        char[] buffer = new char[4096];
        int read;
        while ((read = in.read(buffer)) > 0) {
            contents.append(buffer, 0, read);
        }
        return contents.toString();
    }

    private static KeyStoreEntry<PrivateKey> loadKey(String keyStoreFile, String storePass, String keyAlias) throws Exception {
        CertificateService certificateService = EtxSecurityProvider.getInstance().getCertificateService();

        List<KeyStoreEntry<PrivateKey>> keyEntries;
        try (InputStream keyStoreStream = new FileInputStream(keyStoreFile)) {
            keyEntries = certificateService.getKeyStoreEntries(keyStoreStream, "PKCS12", storePass.toCharArray(), PrivateKey.class);
        }

        KeyStoreEntry<PrivateKey> keyStoreEntry = null;
        for (KeyStoreEntry<PrivateKey> keyEntry : keyEntries) {
            if (keyEntry.getAlias().equals(keyAlias)) {
                keyStoreEntry = keyEntry;
            }
        }
        return keyStoreEntry;
    }

    private static void printUsage() {
        StringBuilder usage = new StringBuilder();
        usage.append("Usage: ").append(Encrypt.class.getName()).append(" <keyStoreFile> <keyStorePassword> <keyStoreEntryAlias> <signedDataPath>");
        usage.append('\n').append("Where:");
        usage.append('\n').append("<keyStoreFile> - The key store file containing the key to be used for signature");
        usage.append('\n').append("<keyStorePassword> - The password for accessing the key store file");
        usage.append('\n').append("<keyStoreEntryAlias> - The alias whose PublicKey will be loaded for signature");
        usage.append('\n').append("<signedDataPath> - The file containing the data to be signed");
        usage.append('\n').append("NOTE: For the file containing the data to be signed, a new file will be created with the same file name and sig extension");

        System.out.println(usage.toString());
    }
}
