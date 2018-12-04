package eu.europa.ec.etrustex.webaccess.webservice.model;

import eu.europa.ec.etrustex.webaccess.model.ConfidentialityCode;
import eu.europa.ec.etrustex.webaccess.model.IntegrityCode;

import java.io.Serializable;
import java.util.Arrays;

public class RawNodeIcaDetails implements Serializable {
    private static final long serialVersionUID = -4753022659846453682L;

    private final byte[] encodedCertificate;
    private final String X509SubjectName;
    private final ConfidentialityCode confidentialityCode;
    private final IntegrityCode integrityCode;

    public RawNodeIcaDetails(byte[] encodedCertificate, String X509SubjectName, ConfidentialityCode confidentialityCode, IntegrityCode integrityCode) {
        this.encodedCertificate = encodedCertificate;
        this.X509SubjectName = X509SubjectName;
        this.confidentialityCode = confidentialityCode;
        this.integrityCode = integrityCode;
    }

    public ConfidentialityCode getConfidentialityCode() {
        return confidentialityCode;
    }

    public IntegrityCode getIntegrityCode() {
        return integrityCode;
    }

    public byte[] getEncodedCertificate() {
        return encodedCertificate;
    }

    public String getX509SubjectName() {
        return X509SubjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawNodeIcaDetails that = (RawNodeIcaDetails) o;

        if (!Arrays.equals(encodedCertificate, that.encodedCertificate)) return false;
        if (X509SubjectName != null ? !X509SubjectName.equals(that.X509SubjectName) : that.X509SubjectName != null)
            return false;
        if (confidentialityCode != that.confidentialityCode) return false;
        return integrityCode == that.integrityCode;

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(encodedCertificate);
        result = 31 * result + (X509SubjectName != null ? X509SubjectName.hashCode() : 0);
        result = 31 * result + (confidentialityCode != null ? confidentialityCode.hashCode() : 0);
        result = 31 * result + (integrityCode != null ? integrityCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RawNodeIcaDetails{" +
                "X509SubjectName='" + X509SubjectName + '\'' +
                ", confidentialityCode=" + confidentialityCode +
                ", integrityCode=" + integrityCode +
                '}';
    }
}
