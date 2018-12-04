package eu.europa.ec.etrustex.webaccess.business.mail;

import eu.europa.ec.etrustex.webaccess.business.api.MailHandlerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("messageReceivedMailHandler")
//@DependsOn("liquibase")
public class MessageMailHandlerServiceImpl extends MailHandlerServiceAdapter implements MailHandlerService {

    private static final String CONTENT_USER_TEMPLATE_NAME = "user-message-received-email.vm";

    private static final String CONTENT_PARTY_TEMPLATE_NAME = "party-message-received-email.vm";

    private static final String SUBJECT_TEMPLATE_NAME = "message-received-subject.vm";

    @Override
    protected String buildContent(MailContentData mailContentData) {
        Map<String, Object> model = new HashMap<>();

        model.put("messageDossierId", mailContentData.getMessageReferenceId());
        model.put("messageURL", buildMessageUrlWS(mailContentData.getMessageId()));
        model.put("userName", mailContentData.getUserName());
        model.put("receiverName", mailContentData.getReceiverName());
        model.put("messageTitle", mailContentData.getMessageTitle());

        if (mailContentData instanceof PayloadMailContentData) {
            PayloadMailContentData payloadMailContentData = (PayloadMailContentData) mailContentData;
            model.put("payloadDossierTitle", payloadMailContentData.getTitle());
            model.put("subsidiarityCheck", payloadMailContentData.getSubsidiarityCheck());
            model.put("payloadDossierList", payloadMailContentData.getPayloadDossierList());
        }

        return velocityService.applyTemplate(mailContentData.getTemplateFile(), model);
    }

    @Override
    protected String subjectTemplateName() {
        return SUBJECT_TEMPLATE_NAME;
    }

    @Override
    protected String contentUserTemplateName() {
        return CONTENT_USER_TEMPLATE_NAME;
    }

    @Override
    protected String contentPartyTemplateName() {
        return CONTENT_PARTY_TEMPLATE_NAME;
    }
}
