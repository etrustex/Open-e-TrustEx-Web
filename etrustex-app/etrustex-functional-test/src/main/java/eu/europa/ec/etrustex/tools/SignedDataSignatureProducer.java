package eu.europa.ec.etrustex.tools;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class sticks together the two steps of generating the data for sign
 * and signing of the generated data.
 */
public class SignedDataSignatureProducer {

    public static void main(String[] args) throws Exception {
        if (args.length != 5) {
            printUsage();
            return;
        }

        String unencryptedFolderPath = args[0];
        String fileMetadataListFile = args[1];
        String keyStoreFile = args[2];
        String storePass = args[3];
        String keyAlias = args[4];

        Path signatureFile = Paths.get(SignedDataProducer.OUTPUT_FILE_NAME);
        Files.deleteIfExists(signatureFile);
        SignedDataProducer.produce(unencryptedFolderPath, fileMetadataListFile);
        signatureFile = Paths.get(SignedDataProducer.OUTPUT_FILE_NAME);
        if (Files.exists(signatureFile)) {
            SignatureProducer.produce(keyStoreFile, storePass, keyAlias, SignedDataProducer.OUTPUT_FILE_NAME);
        }
    }

    private static void printUsage() {
        StringBuilder usage = new StringBuilder();
        usage.append("Usage: ").append(SignedDataSignatureProducer.class.getName()).append(" <unencryptedFolderPath> <fileMetadataListFile> <keyStoreFile> <keyStorePassword> <keyStoreEntryAlias>");
        usage.append('\n').append("Where:");
        usage.append('\n').append("<unencryptedFolderPath> - The folder with unencrypted files for which checksum will be computed");
        usage.append('\n').append("<fileMetadataListFile> - The XML file with files metadata list defined as in OutboxService wsdl");
        usage.append('\n').append("<keyStoreFile> - The key store file containing the key to be used for signature");
        usage.append('\n').append("<keyStorePassword> - The password for accessing the key store file");
        usage.append('\n').append("<keyStoreEntryAlias> - The alias whose PublicKey will be loaded for signature");
        usage.append('\n').append("NOTE: check data to sign result in ").append(SignedDataProducer.OUTPUT_FILE_NAME);
        usage.append('\n').append("NOTE: For the file containing the data to be signed, a new file will be created with the same file name and sig extension");
        System.out.println(usage.toString());
    }
}
