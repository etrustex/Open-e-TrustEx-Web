package eu.europa.ec.etrustex.webaccess.model.vo;

import java.io.Serializable;
import java.security.PublicKey;

public class ICADetails implements Serializable {
    private final boolean isSignatureRequired;
    private final boolean isEncryptionRequired;
    private final String X509SubjectName;
    private PublicKey publicKey;
    private CertificateValidity certificateValidity;

    public ICADetails(boolean isSignatureRequired, boolean isEncryptionRequired, String x509SubjectName, PublicKey publicKey) {
        this.isSignatureRequired = isSignatureRequired;
        this.isEncryptionRequired = isEncryptionRequired;
        this.X509SubjectName = x509SubjectName;
        this.publicKey = publicKey;
    }

    public boolean getIsSignatureRequired() {
        return isSignatureRequired;
    }

    public boolean getIsEncryptionRequired() {
        return isEncryptionRequired;
    }

    public String getX509SubjectName() {
        return X509SubjectName;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public CertificateValidity getCertificateValidity() {
        return certificateValidity;
    }

    public void setCertificateValidity(CertificateValidity certificateValidity) {
        this.certificateValidity = certificateValidity;
    }
}
