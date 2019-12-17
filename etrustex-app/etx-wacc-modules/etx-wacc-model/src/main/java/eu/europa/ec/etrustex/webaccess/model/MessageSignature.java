package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "ETX_WEB_MESSAGE_SIG")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MessageSignature extends AbstractEntity<Long> {

    @SequenceGenerator(name = "ETX_WEB_MSGSIGSEQ", sequenceName = "ETX_WEB_MSGSIGSEQ", allocationSize = 1)
    @Id
    @Column(name = "MSS_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_MSGSIGSEQ")
    private Long id;

    @Column(name = "MSG_SIGNED_DATA", nullable = true, unique = false)
    @Lob
    private String signedData;

    @Column(name = "MSG_SIGNATURE", nullable = true, unique = false)
    @Lob
    private String signature;

    @Column(name = "MSG_CERTIFICATE", nullable = true, unique = false)
    @Lob
    private String certificate;

    @Column(name = "MSG_SIGNATURE_VALID", nullable = false)
    private boolean signatureValid;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignedData() {
        return signedData;
    }

    public void setSignedData(String signedData) {
        this.signedData = signedData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public boolean isSignatureValid() {
        return signatureValid;
    }

    public void setSignatureValid(boolean signatureValid) {
        this.signatureValid = signatureValid;
    }
}
