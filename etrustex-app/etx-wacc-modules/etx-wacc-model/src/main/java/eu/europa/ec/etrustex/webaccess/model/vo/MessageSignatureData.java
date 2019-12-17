package eu.europa.ec.etrustex.webaccess.model.vo;

import java.io.Serializable;

public class MessageSignatureData implements Serializable {

    private final boolean signatureValid;
    private final CertificateData certificateData;

    public MessageSignatureData(CertificateData certificateData, boolean signatureValid) {
        this.certificateData = certificateData;
        this.signatureValid = signatureValid;
    }

    public boolean isSignatureValid() {
        return signatureValid;
    }

    public CertificateData getCertificateData() {
        return certificateData;
    }
}
