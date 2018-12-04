package eu.europa.ec.etrustex.webaccess.model.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class CertificateData implements Serializable {

    private final BigInteger serialNumber;
    private final String commonName;
    private final String organizationUnit;
    private final String organizationName;
    private final String locality;
    private final String state;
    private final String country;
    private final String email;
    private final String thumbPrint;
    private final Date expiryDate;

    public CertificateData(BigInteger serialNumber, String commonName, String organizationUnit, String organizationName,
                           String locality, String state, String country, String email, String thumbPrint, Date expiryDate) {
        this.serialNumber = serialNumber;
        this.commonName = commonName;
        this.organizationUnit = organizationUnit;
        this.organizationName = organizationName;
        this.locality = locality;
        this.state = state;
        this.country = country;
        this.email = email;
        this.thumbPrint = thumbPrint;
        this.expiryDate = expiryDate;
    }

    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getLocality() {
        return locality;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getThumbPrint() {
        return thumbPrint;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getUniqueKey() {
        return new StringBuilder(commonName)
                    .append('_').append(serialNumber)
                    .append('_').append(thumbPrint).toString();
    }
}
