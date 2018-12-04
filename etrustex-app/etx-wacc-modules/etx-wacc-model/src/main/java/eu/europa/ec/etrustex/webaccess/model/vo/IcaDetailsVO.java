package eu.europa.ec.etrustex.webaccess.model.vo;

public class IcaDetailsVO {

    private String senderParty;
    private String receiverParty;
    private String confidentialityCode;
    private String integrityCode;
    private IcaStatus icaStatus;
    private String lastDateReloaded;
    private String version;
    private String serialNumber;
    private String issuerDN;
    private String startDate;
    private String finalDate;
    private String subjectDN;
    private String signatureAlgorithm;
    private boolean hasCertificate;
    private boolean activeState;

    public static enum IcaStatus {
        OK,
        UPDATE_FAILED;
    }

    public String getSenderParty() {
        return senderParty;
    }

    public void setSenderParty(String senderParty) {
        this.senderParty = senderParty;
    }

    public String getReceiverParty() {
        return receiverParty;
    }

    public void setReceiverParty(String receiverParty) {
        this.receiverParty = receiverParty;
    }

    public String getConfidentialityCode() {
        return confidentialityCode;
    }

    public void setConfidentialityCode(String confidentialityCode) {
        this.confidentialityCode = confidentialityCode;
    }

    public String getIntegrityCode() {
        return integrityCode;
    }

    public void setIntegrityCode(String integrityCode) {
        this.integrityCode = integrityCode;
    }

    public IcaStatus getIcaStatus() {
        return icaStatus;
    }

    public void setIcaStatus(IcaStatus icaStatus) {
        this.icaStatus = icaStatus;
    }

    public String getLastDateReloaded() {
        return lastDateReloaded;
    }

    public void setLastDateReloaded(String lastDateReloaded) {
        this.lastDateReloaded = lastDateReloaded;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getIssuerDN() {
        return issuerDN;
    }

    public void setIssuerDN(String issuerDN) {
        this.issuerDN = issuerDN;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public String getSubjectDN() {
        return subjectDN;
    }

    public void setSubjectDN(String subjectDN) {
        this.subjectDN = subjectDN;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public boolean isHasCertificate() {
        return hasCertificate;
    }

    public void setHasCertificate(boolean hasCertificate) {
        this.hasCertificate = hasCertificate;
    }

    public boolean isActiveState() {
        return activeState;
    }

    public void setActiveState(boolean activeState) {
        this.activeState = activeState;
    }

}
