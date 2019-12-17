package eu.europa.ec.etrustex.webaccess.security;

import java.io.Serializable;
import java.security.Key;
import java.security.cert.X509Certificate;

/**
 * @author: micleva
 * @date: 7/12/12 1:08 PM
 * @project: ETX
 */
public class KeyStoreEntry<T extends Key> implements Serializable {

    private String alias;
    private T keyEntry;
    private X509Certificate x509Certificate;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public X509Certificate getX509Certificate() {
        return x509Certificate;
    }

    public void setX509Certificate(X509Certificate x509Certificate) {
        this.x509Certificate = x509Certificate;
    }

    public T getKeyEntry() {
        return keyEntry;
    }

    public void setKeyEntry(T keyEntry) {
        this.keyEntry = keyEntry;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("KeyStoreEntry");
        sb.append("{alias='").append(alias).append('\'');
        sb.append(", keyEntry=").append(keyEntry);
        sb.append(", x509Certificate=").append(x509Certificate);
        sb.append('}');
        return sb.toString();
    }
}
