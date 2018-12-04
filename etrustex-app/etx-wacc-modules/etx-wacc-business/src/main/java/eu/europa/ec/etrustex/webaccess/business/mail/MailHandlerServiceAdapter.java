package eu.europa.ec.etrustex.webaccess.business.mail;

public class MailHandlerServiceAdapter extends AbstractMailHandlerService {

    @Override
    protected String buildContent(MailContentData statusMailContentData) {
        return null;
    }

    @Override
    protected String subjectTemplateName() {
        return null;
    }

    @Override
    protected String contentUserTemplateName() {
        return null;
    }

    @Override
    protected String contentPartyTemplateName() {
        return null;
    }

}
