package eu.europa.ec.etrustex.webaccess.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class TransferHelper {

    private static final int BUFFER_SIZE = 16 * 1024;

    public static void transferData(InputStream is, OutputStream os) throws IOException {
        transferData(is, os, null);
    }

    public static void transferData(InputStream is, OutputStream os, TransferProgressNotifiable progressNotifier) throws IOException {
        final ReadableByteChannel inputChannel = Channels.newChannel(is);
        final WritableByteChannel outputChannel = Channels.newChannel(os);

        final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        long bytesProcessed = 0;
        try {

            while (inputChannel.read(buffer) != -1) {
                // prepare the buffer to be drained
                buffer.flip();
                // write to the channel, may block
                outputChannel.write(buffer);

                bytesProcessed += buffer.limit();
                notifyProgress(progressNotifier, bytesProcessed);
                if (Thread.currentThread().isInterrupted()) {
                    if (os != null) {
                        os.close();
                    }
                    throw new IOException("Thread interrupted...");
                }

                // If partial transfer, shift remainder down
                // If buffer is empty, same as doing clear()
                buffer.compact();
            }
            // EOF will leave buffer in fill state
            buffer.flip();
            // make sure the buffer is fully drained.
            while (buffer.hasRemaining()) {
                outputChannel.write(buffer);

                bytesProcessed += buffer.limit();
                notifyProgress(progressNotifier, bytesProcessed);
            }

        } finally {
            if (is != null) {
                is.close();
            }
            // VERY IMPORTANT : DO NOT CLOSE THE OUTPUTSTREAM (received from parameters) IN THIS METHOD !!!
        }
    }

    private static void notifyProgress(TransferProgressNotifiable progressNotifier, long bytesProcessed) {
        if (progressNotifier != null) {
            progressNotifier.notifyProgress(bytesProcessed);
        }
    }
}
