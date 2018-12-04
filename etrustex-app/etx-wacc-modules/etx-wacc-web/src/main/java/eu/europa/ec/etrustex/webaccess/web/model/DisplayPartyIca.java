package eu.europa.ec.etrustex.webaccess.web.model;

public class DisplayPartyIca {

    private Long remotePartyId;

    private String remotePartyNodeName;

    private String remotePartyDisplayName;

    private boolean isSignatureMandatory;

    private boolean isEncryptionRequired;


    public Long getRemotePartyId() {
        return remotePartyId;
    }

    public String getRemotePartyNodeName() {
        return remotePartyNodeName;
    }

    public String getRemotePartyDisplayName() {
        return remotePartyDisplayName;
    }

    public boolean isSignatureMandatory() {
        return isSignatureMandatory;
    }

    public boolean isEncryptionRequired() {
        return isEncryptionRequired;
    }

    public void setRemotePartyId(Long remotePartyId) {
        this.remotePartyId = remotePartyId;
    }

    public void setRemotePartyNodeName(String remotePartyNodeName) {
        this.remotePartyNodeName = remotePartyNodeName;
    }

    public void setRemotePartyDisplayName(String remotePartyDisplayName) {
        this.remotePartyDisplayName = remotePartyDisplayName;
    }

    public void setSignatureMandatory(boolean signatureMandatory) {
        isSignatureMandatory = signatureMandatory;
    }

    public void setEncryptionRequired(boolean encryptionRequired) {
        isEncryptionRequired = encryptionRequired;
    }

}
