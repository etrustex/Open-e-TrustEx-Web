package eu.europa.ec.etrustex.webaccess.business.mail;

public class PayloadDossier {
    private String title;
    private String linguisticVersions;
    private Boolean subsidiarityCheck;

    public PayloadDossier() {
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

}
