package eu.europa.ec.etrustex.webaccess.webservice.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author: micleva
 * @date: 12/4/12 2:07 PM
 * @project: ETX
 */
public class UserSignatureData implements Serializable {

    private static final long serialVersionUID = -4738336579591375508L;

    private final byte[] encodedCertificate;
    private final byte[] encodedKey;
    private final String algorithm;
    private final String userName;
    private final Date creationDate;

    public UserSignatureData(byte[] encodedCertificate, byte[] encodedKey, String algorithm, String userName, Date creationDate) {
        this.encodedCertificate = encodedCertificate;
        this.encodedKey = encodedKey;
        this.algorithm = algorithm;
        this.userName = userName;
        this.creationDate = creationDate;
    }

    public byte[] getEncodedCertificate() {
        return encodedCertificate;
    }

    public byte[] getEncodedKey() {
        return encodedKey;
    }

    public String getUserName() {
        return userName;
    }

    public String getAlgorithm() {
        return algorithm;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserSignatureData{");
        sb.append("encodedCertificate=").append(Arrays.toString(encodedCertificate));
        sb.append(", encodedKey=").append(Arrays.toString(encodedKey));
        sb.append(", algorithm='").append(algorithm).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append('}');
        return sb.toString();
    }
}
