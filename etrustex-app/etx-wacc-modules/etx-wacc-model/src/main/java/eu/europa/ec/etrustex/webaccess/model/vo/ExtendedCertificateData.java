package eu.europa.ec.etrustex.webaccess.model.vo;


import java.io.Serializable;
import java.security.PublicKey;
import java.util.Date;

public class ExtendedCertificateData implements Serializable {

    private final PublicKey publicKey;

    private final Date startDate;
    private final Date endDate;
    private final String subjectDN;
    private final String signatureAlgorithm;
    private final String issuerDN;
    private final String version;
    private final String serialNumber;

    public ExtendedCertificateData(PublicKey publicKey, String subjectDN, String issuerDN, Date startDate,
                                   Date endDate, String serialNumber, String signatureAlgorithm, String version) {

        this.publicKey = publicKey;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subjectDN = subjectDN;
        this.signatureAlgorithm = signatureAlgorithm;
        this.issuerDN = issuerDN;
        this.version = version;
        this.serialNumber = serialNumber;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getSubjectDN() {
        return subjectDN;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public String getIssuerDN() {
        return issuerDN;
    }

    public String getVersion() {
        return version;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtendedCertificateData that = (ExtendedCertificateData) o;

        if (publicKey != null ? !publicKey.equals(that.publicKey) : that.publicKey != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (subjectDN != null ? !subjectDN.equals(that.subjectDN) : that.subjectDN != null) return false;
        if (signatureAlgorithm != null ? !signatureAlgorithm.equals(that.signatureAlgorithm) : that.signatureAlgorithm != null)
            return false;
        if (issuerDN != null ? !issuerDN.equals(that.issuerDN) : that.issuerDN != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        return serialNumber != null ? serialNumber.equals(that.serialNumber) : that.serialNumber == null;

    }

    @Override
    public int hashCode() {
        int result = publicKey != null ? publicKey.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (subjectDN != null ? subjectDN.hashCode() : 0);
        result = 31 * result + (signatureAlgorithm != null ? signatureAlgorithm.hashCode() : 0);
        result = 31 * result + (issuerDN != null ? issuerDN.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        return result;
    }
}
