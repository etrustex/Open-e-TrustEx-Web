package eu.europa.ec.etrustex.webaccess.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingInputStream extends InputStream {

    private InputStream delegate;
    MessageDigest md;
    public HashingInputStream (InputStream delegate, String hashMethod) {
        this.delegate = delegate;
        try {
            this.md = MessageDigest.getInstance(hashMethod);
        } catch (NoSuchAlgorithmException e) {
        }
    }

    @Override
    public int read() throws IOException {
        int b = delegate.read();
        if(b != -1){
            md.update((byte) b);
        }
        return b;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int res = delegate.read(b);
        if(res != -1) {
            this.md.update(b, 0, res);
        }
        return res;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int res = delegate.read(b, off, len);
        if(res != -1) {
            this.md.update(b, off, off+res);
        }
        return res;
    }

    public byte [] getDigest() {
        return this.md.digest();
    }
}
