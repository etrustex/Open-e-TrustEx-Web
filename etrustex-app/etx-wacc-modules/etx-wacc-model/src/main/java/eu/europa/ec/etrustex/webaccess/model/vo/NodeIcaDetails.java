package eu.europa.ec.etrustex.webaccess.model.vo;

import eu.europa.ec.etrustex.webaccess.model.ConfidentialityCode;
import eu.europa.ec.etrustex.webaccess.model.IntegrityCode;

import java.io.Serializable;
import java.util.Date;

public class NodeIcaDetails implements Serializable {

    private final Date creationDate;

    private final String localParty;
    private final String remoteParty;

    private final ConfidentialityCode confidentialityCode;
    private final IntegrityCode integrityCode;

    private final ExtendedCertificateData extendedCertificateData;

    private final boolean activeState;

    public NodeIcaDetails(Date creationDate, String localParty, String remoteParty, ConfidentialityCode confidentialityCode, IntegrityCode integrityCode, ExtendedCertificateData extendedCertificateData, boolean activeState) {
        this.creationDate = creationDate;
        this.localParty = localParty;
        this.remoteParty = remoteParty;
        this.confidentialityCode = confidentialityCode;
        this.integrityCode = integrityCode;
        this.extendedCertificateData = extendedCertificateData;
        this.activeState = activeState;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getLocalParty() {
        return localParty;
    }

    public String getRemoteParty() {
        return remoteParty;
    }

    public ConfidentialityCode getConfidentialityCode() {
        return confidentialityCode;
    }

    public IntegrityCode getIntegrityCode() {
        return integrityCode;
    }

    public ExtendedCertificateData getExtendedCertificateData() {
        return extendedCertificateData;
    }

    public boolean isActiveState() {
        return activeState;
    }

    @Override
    public String toString() {
        return "NodeIcaDetails{" +
                "creationDate=" + creationDate +
                ", sender='" + localParty + '\'' +
                ", remoteParty='" + remoteParty + '\'' +
                ", confidentialityCode=" + confidentialityCode +
                ", integrityCode=" + integrityCode +
                ", activeState=" + activeState +
                '}';
    }
}
