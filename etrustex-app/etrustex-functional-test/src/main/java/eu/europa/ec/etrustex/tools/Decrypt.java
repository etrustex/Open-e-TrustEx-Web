package eu.europa.ec.etrustex.tools;

import eu.europa.ec.etrustex.webaccess.security.CertificateService;
import eu.europa.ec.etrustex.webaccess.security.CryptoService;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import eu.europa.ec.etrustex.webaccess.security.KeyStoreEntry;
import org.bouncycastle.crypto.io.CipherOutputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.PrivateKey;
import java.util.Iterator;
import java.util.List;

/**
 * @author: micleva
 * @date: 7/16/12 9:17 AM
 * @project: ETX
 */
public class Decrypt {
    private static final PathMatcher ZIP_FILE_MATCHER = FileSystems.getDefault().getPathMatcher("glob:*.zip");
    public static final String SESSION_KEY = "SessionKey";

    private static CryptoService cryptoService;
    private static CertificateService certificateService;

    static {
        EtxSecurityProvider.init();
        cryptoService = EtxSecurityProvider.getInstance().getCryptoService();
        certificateService = EtxSecurityProvider.getInstance().getCertificateService();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            printUsage();
            return;
        }

        String keyStoreFile = args[0];
        String storePass = args[1];
        String keyAlias = args[2];
        String filePath = args[3];

        final PrivateKey privateKey = loadKey(keyStoreFile, storePass, keyAlias);
        if (privateKey == null) {
            System.err.println("Key store entry " + keyAlias + " from key store file: " + keyStoreFile + " NOT FOUND!");
            return;
        }

        //encrypt the file(s)
        Path fileToDecrypt = Paths.get(filePath);
        if (Files.isRegularFile(fileToDecrypt)) {
            decryptFile(fileToDecrypt, privateKey);
        } else if (Files.isDirectory(fileToDecrypt)) {
            final DirectoryStream.Filter<Path> regularFileFilter = new DirectoryStream.Filter<Path>() {
                @Override
                public boolean accept(Path entry) throws IOException {
                    return Files.isRegularFile(entry);
                }
            };

            try (final DirectoryStream<Path> paths = Files.newDirectoryStream(fileToDecrypt, regularFileFilter)) {
                for (Path path : paths) {
                    decryptFile(path, privateKey);
                }
            }
        }
    }

    private static void decryptFile(Path path, PrivateKey privateKey) throws Exception {
        if (!ZIP_FILE_MATCHER.matches(path.getFileName())) {
            return;
        }

        System.out.println("Start decrypting path: " + path.getFileName().toString());
        long startTime = System.currentTimeMillis();

        byte[] encryptedAes256Key = readSessionKeyZipEntry(path);

        //encrypt the key
        byte[] aes256Key = cryptoService.asymmetricDecrypt(privateKey, encryptedAes256Key);

        decryptFileZipEntry(path, aes256Key);
        System.out.println("Decryption completed after " + (double) (System.currentTimeMillis() - startTime) / 1000 + " sec");
    }

    private static void decryptFileZipEntry(final Path zipPath, final byte[] aes256Key) throws IOException {
        try(FileSystem zipFileSystem = FileSystems.newFileSystem(zipPath, null)) {
            final Iterator<Path> zipPathIterator = zipFileSystem.getRootDirectories().iterator();
            while(zipPathIterator.hasNext()) {
                Files.walkFileTree(zipPathIterator.next(), new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult visitFile(Path zipEntry, BasicFileAttributes attrs) throws IOException {
                        final Path zipEntryFileName = zipEntry.getFileName();
                        if (!zipEntryFileName.toString().equals(SESSION_KEY)) {
                            final Path decryptedFile = zipPath.getParent().resolve(zipEntryFileName);
                            writeDecryptedFile(zipEntry, decryptedFile, aes256Key);
                        }

                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        }
    }

    private static void writeDecryptedFile(Path zipEntry, Path decryptedFile, byte[] aes256Key) throws IOException {
        Files.deleteIfExists(decryptedFile);
        Files.createFile(decryptedFile);

        try (CipherOutputStream outputStream = (CipherOutputStream) cryptoService.getCipherOutputStream(Files.newOutputStream(decryptedFile), aes256Key, false)) {
            Files.copy(zipEntry, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] readSessionKeyZipEntry(Path zipPath) throws IOException {
        final SessionKey sessionKey = new SessionKey();

        try(FileSystem zipFileSystem = FileSystems.newFileSystem(zipPath, null)) {
            final Iterator<Path> zipPathIterator = zipFileSystem.getRootDirectories().iterator();
            while(sessionKey.isEmpty() && zipPathIterator.hasNext()) {
                Files.walkFileTree(zipPathIterator.next(), new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult visitFile(Path zipEntry, BasicFileAttributes attrs) throws IOException {
                        if (zipEntry.getFileName().toString().equals(SESSION_KEY)) {
                            sessionKey.rawBytes = Files.readAllBytes(zipEntry);
                            return FileVisitResult.TERMINATE;
                        }

                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        }

        return sessionKey.rawBytes;
    }

    private static PrivateKey loadKey(String keyStoreFile, String storePass, String keyAlias) throws Exception {
        PrivateKey privateKey = null;

        List<KeyStoreEntry<PrivateKey>> keyEntries;
        try (InputStream keyStoreStream = new FileInputStream(keyStoreFile)) {
            keyEntries = certificateService.getKeyStoreEntries(keyStoreStream, "PKCS12", storePass.toCharArray(), PrivateKey.class);
        }

        for (KeyStoreEntry<PrivateKey> keyEntry : keyEntries) {
            if (keyEntry.getAlias().equals(keyAlias)) {
                privateKey = keyEntry.getKeyEntry();
            }
        }
        return privateKey;
    }

    private static void printUsage() {
        StringBuilder usage = new StringBuilder();
        usage.append("Usage: ").append(Decrypt.class.getName()).append(" <keyStoreFile> <keyStorePassword> <keyStoreEntryAlias> <FileToDecrypt>");
        usage.append('\n').append("Where:");
        usage.append('\n').append("<keyStoreFile> - The key store file containing the key to be used for decryption");
        usage.append('\n').append("<keyStorePassword> - The password for accessing the key store file");
        usage.append('\n').append("<keyStoreEntryAlias> - The alias whose PrivateKey will be loaded for decryption");
        usage.append('\n').append("<FileToDecrypt> - The file or directory to be decrypted");
        usage.append('\n').append("NOTE: Zip files containing an encrypted file plus a SessionKey entry will only be considered for decryption!");

        System.out.println(usage.toString());
    }

    private static class SessionKey {
        byte[] rawBytes = null;

        boolean isEmpty() {
            return rawBytes == null;
        }
    }
}
