package eu.europa.ec.etrustex.webaccess.utils;

import org.apache.log4j.Logger;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Arrays;

public class HashHelper {
    private static final Logger logger = Logger.getLogger(HashHelper.class);

    private final static int BUFFER_SIZE = 16 * 1024;

    /**
     * Allowed hash methods.
     */
    public enum HashMethodType {
        SHA_512("SHA-512");

        private final String code;

        HashMethodType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public static HashMethodType parse(String code) {
            for (HashMethodType method : values()) {
                if (method.code.equals(code))
                    return method;
            }
            throw new IllegalArgumentException("unknown hash method: " + code);
        }
    }

    public byte[] hash(Path path, String hashMethod) throws Exception {
        try (InputStream inputStream = Files.newInputStream(path)) {
            return hash(inputStream, hashMethod);
        }
    }

    public byte[] hash(byte[] input, String hashMethod) throws Exception {
        return hash(new ByteArrayInputStream(input), hashMethod);
    }

    private byte[] hash(InputStream inputStream, String hashMethod) throws Exception {
        MessageDigest ms = MessageDigest.getInstance(hashMethod);

        final ReadableByteChannel inputChannel = Channels.newChannel(inputStream);
        final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        while (inputChannel.read(buffer) != -1) {
            // prepare the buffer to be drained
            buffer.flip();

            // update the message digest based on this buffer
            ms.update(buffer);

            // If partial transfer, shift remainder down
            // If buffer is empty, same as doing clear()
            buffer.compact();
        }
        // EOF will leave buffer in fill state
        buffer.flip();
        // make sure the buffer is fully drained.
        while (buffer.hasRemaining()) {
            ms.update(buffer);
        }

        return ms.digest();
    }

    public boolean verifyFileChecksum(Path path, byte[] expectedChecksum, String checksumMethod) throws Exception {
        boolean isChecksumValid = false;

        if (expectedChecksum != null && checksumMethod != null) {
            long startTime = System.currentTimeMillis();
            byte[] computedFileHash = hash(path, checksumMethod);

            if (logger.isTraceEnabled()) {
                logger.trace("Computed file checksum: " + DatatypeConverter.printHexBinary(computedFileHash)
                        + "\n expected checksum: " + DatatypeConverter.printHexBinary(expectedChecksum)
                        + "\n computed in: " + (System.currentTimeMillis() - startTime));
            }

            isChecksumValid = Arrays.equals(computedFileHash, expectedChecksum);
        } else {
            logger.info("Checksum: null");
        }

        return isChecksumValid;
    }

    private static class LazyHolder {
        private static final HashHelper INSTANCE = new HashHelper();
    }

    public static HashHelper getInstance() {
        return LazyHolder.INSTANCE;
    }

}
