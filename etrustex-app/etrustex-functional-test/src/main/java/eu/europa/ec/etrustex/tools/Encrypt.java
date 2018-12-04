package eu.europa.ec.etrustex.tools;

import eu.europa.ec.etrustex.webaccess.security.CertificateService;
import eu.europa.ec.etrustex.webaccess.security.CryptoService;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import eu.europa.ec.etrustex.webaccess.security.KeyStoreEntry;

import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.FileSystem;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;

/**
 * @author: micleva
 * @date: 7/16/12 9:17 AM
 * @project: ETX
 */
public class Encrypt {

    private static CryptoService cryptoService;
    private static CertificateService certificateService;

    static {
        EtxSecurityProvider.init();
        cryptoService = EtxSecurityProvider.getInstance().getCryptoService();
        certificateService = EtxSecurityProvider.getInstance().getCertificateService();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4 && args.length != 2) {
            printUsage();
            return;
        }

        String filePath;
        PublicKey publicKey = null;
        if (args.length == 4) {
            String keyStoreFile = args[0];
            String storePass = args[1];
            String keyAlias = args[2];

            publicKey = loadKey(keyStoreFile, storePass, keyAlias);
            if (publicKey == null) {
                System.err.println("Key store entry " + keyAlias + " from key store file: " + keyStoreFile + " NOT FOUND!");
                return;
            }
            filePath = args[3];
        } else {
            String certificateFile = args[0];
            publicKey = loadKey(certificateFile);
            if (publicKey == null) {
                System.err.println("Could not load the public key from certificate file " + certificateFile);
                return;
            }
            filePath = args[1];
        }

        //encrypt the file(s)
        Path fileToEncrypt = Paths.get(filePath);
        if (Files.isRegularFile(fileToEncrypt)) {
            encryptFile(fileToEncrypt, publicKey);
        } else if (Files.isDirectory(fileToEncrypt)) {
            final DirectoryStream.Filter<Path> regularFileFilter = new DirectoryStream.Filter<Path>() {
                @Override
                public boolean accept(Path entry) throws IOException {
                    return Files.isRegularFile(entry);
                }
            };

            try (final DirectoryStream<Path> paths = Files.newDirectoryStream(fileToEncrypt, regularFileFilter)) {
                for (Path path : paths) {
                    encryptFile(path, publicKey);
                }
            }
        }
    }

    private static void encryptFile(Path fileToEncrypt, PublicKey publicKey) throws Exception {
        System.out.println("Start encrypting file: " + fileToEncrypt.getFileName().toString());
        long startTime = System.currentTimeMillis();

        String fileNoExtension = fileToEncrypt.toAbsolutePath().toString();
        fileNoExtension = fileNoExtension.substring(0, fileNoExtension.lastIndexOf('.'));

        //generate the key
        byte[] aes256Key = cryptoService.genAes256Key();

        //encrypt the key
        byte[] encryptedKey = cryptoService.asymmetricEncrypt(publicKey, aes256Key);

        Path encryptedFile = Paths.get(fileNoExtension + ".enc");
        writeEncryptedFile(fileToEncrypt, encryptedFile, aes256Key);

        Path zipFile = Paths.get(fileNoExtension + ".zip");
        writeZipFile(fileToEncrypt.getFileName().toString(), encryptedFile, zipFile, encryptedKey);

        Files.delete(encryptedFile);
        System.out.println("Encryption completed after " + (double) (System.currentTimeMillis() - startTime) / 1000 + " sec");
    }

    private static void writeEncryptedFile(Path fileToEncrypt, Path encryptedFile, byte[] aes256Key) throws IOException {
        Files.deleteIfExists(encryptedFile);
        Files.createFile(encryptedFile);

        try (OutputStream outputStream = cryptoService.getCipherOutputStream(Files.newOutputStream(encryptedFile), aes256Key, true)) {
            Files.copy(fileToEncrypt, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PublicKey loadKey(String certificateFile) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try (InputStream certInputStream = new FileInputStream(certificateFile)) {
            while ((length = certInputStream.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, length);
            }
        }

        byte[] x509CertificateContent = baos.toByteArray();
        X509Certificate cert = certificateService.getCertificate(x509CertificateContent);
        return cert.getPublicKey();
    }

    private static PublicKey loadKey(String keyStoreFile, String storePass, String keyAlias) throws Exception {
        List<KeyStoreEntry<PublicKey>> keyEntries;
        try (InputStream keyStoreStream = new FileInputStream(keyStoreFile)) {
            keyEntries = certificateService.getKeyStoreEntries(keyStoreStream, "PKCS12", storePass.toCharArray(), PublicKey.class);
        }

        PublicKey publicKey = null;
        for (KeyStoreEntry<PublicKey> keyEntry : keyEntries) {
            if (keyEntry.getAlias().equals(keyAlias)) {
                publicKey = keyEntry.getKeyEntry();
            }
        }
        return publicKey;
    }

    private static void printUsage() {
        StringBuilder usage = new StringBuilder();
        usage.append("Usage: ").append(Encrypt.class.getName()).append(" <keyStoreFile> <keyStorePassword> <keyStoreEntryAlias> <FileToEncrypt>");
        usage.append('\n').append("Where:");
        usage.append('\n').append("<keyStoreFile> - The key store file containing the key to be used for encryption");
        usage.append('\n').append("<keyStorePassword> - The password for accessing the key store file");
        usage.append('\n').append("<keyStoreEntryAlias> - The alias whose PublicKey will be loaded for encryption");
        usage.append('\n').append("<FileToEncrypt> - The file or directory to be encrypted");
        usage.append('\n').append("NOTE: For each file encrypted, a zip file will be created containing the encrypted file + the SessionKey used for encryption.");
        usage.append('\n').append("\nOR: ");
        usage.append("Usage: ").append(Encrypt.class.getName()).append(" <certificateFile> <FileToEncrypt>");
        usage.append('\n').append("Where:");
        usage.append('\n').append("<certificateFile> - The certificate file (x509 format) that contains the public key");
        usage.append('\n').append("<FileToEncrypt> - The file or directory to be encrypted");

        System.out.println(usage.toString());
    }

    private static Path writeZipFile(String originalFileName, Path encryptedFile, Path zipPath, byte[] encryptedKey) throws IOException {
        Files.deleteIfExists(zipPath);

        final URI uri = URI.create("jar:file:" + zipPath.toUri().getPath());

        try(FileSystem zipFileSystem = FileSystems.newFileSystem(uri, Collections.singletonMap("create", "true"))) {
            Files.copy(encryptedFile, zipFileSystem.getPath(originalFileName));
            Files.copy(new ByteArrayInputStream(encryptedKey), zipFileSystem.getPath("SessionKey"));
        }

        return zipPath;
    }
}
