package eu.europa.ec.etrustex.webaccess.business.mail;

import java.util.List;

public class PayloadMailContentData extends MailContentData {

    private String title;
    private String linguisticVersions;
    private Boolean subsidiarityCheck;
    private List<PayloadDossier> payloadDossierList;


    public PayloadMailContentData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinguisticVersions() {
        return linguisticVersions;
    }

    public void setLinguisticVersions(String linguisticVersions) {
        this.linguisticVersions = linguisticVersions;
    }

    public Boolean getSubsidiarityCheck() {
        return subsidiarityCheck;
    }

    public void setSubsidiarityCheck(Boolean subsidiarityCheck) {
        this.subsidiarityCheck = subsidiarityCheck;
    }

    public List<PayloadDossier> getPayloadDossierList() {
        return payloadDossierList;
    }


    public void setPayloadDossierList(List<PayloadDossier> payloadDossierList) {
        this.payloadDossierList = payloadDossierList;
    }


}
