package eu.europa.ec.etrustex.webaccess.business.mail;

public class MailData {
    private String subjectTemplateFile;
    private String contentTemplateFile;
    private MailContentData mailContentData;

    protected String getSubjectTemplateFile() {
        return subjectTemplateFile;
    }

    protected void setSubjectTemplateFile(String subjectTemplateFile) {
        this.subjectTemplateFile = subjectTemplateFile;
    }

    protected MailContentData getMailContentData() {
        return mailContentData;
    }

    protected void setMailContentData(MailContentData mailContentData) {
        this.mailContentData = mailContentData;
    }

    protected String getContentTemplateFile() {
        return contentTemplateFile;
    }

    protected void setContentTemplateFile(String contentTemplateFile) {
        this.contentTemplateFile = contentTemplateFile;
    }
}
