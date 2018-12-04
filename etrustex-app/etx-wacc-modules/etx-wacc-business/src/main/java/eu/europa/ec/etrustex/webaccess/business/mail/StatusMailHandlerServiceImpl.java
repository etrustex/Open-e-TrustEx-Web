package eu.europa.ec.etrustex.webaccess.business.mail;

import eu.europa.ec.etrustex.webaccess.business.api.MailHandlerService;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("statusReceivedMailHandler")
public class StatusMailHandlerServiceImpl extends MailHandlerServiceAdapter implements MailHandlerService {

    private static final String CONTENT_USER_TEMPLATE_NAME = "user-status-email.vm";

    private static final String CONTENT_PARTY_TEMPLATE_NAME = "party-status-email.vm";

    private static final String SUBJECT_TEMPLATE_NAME = "status-subject.vm";

    @Override
    protected String buildContent(MailContentData statusMailContentData) {
        Map<String, Object> model = new HashMap<>();
        model.put("messageDossierId", statusMailContentData.getMessageReferenceId());
        model.put("messageURL", buildMessageUrl(statusMailContentData.getMessageId()));
        model.put("userName", statusMailContentData.getUserName());
        model.put("receiverName", statusMailContentData.getReceiverName());
        model.put("senderName", statusMailContentData.getSenderName());
        model.put("messageTitle", statusMailContentData.getMessageTitle());
        model.put("messageMstStatus", statusMailContentData.getStatus());
        model.put("messageMstStatusCode", statusMailContentData.getStatusCode());
        model.put("createdByUser", statusMailContentData.getCreatedByUser());
        model.put("statusDate", statusMailContentData.getStatusDate());
        model.put("statusTime", statusMailContentData.getStatusTime());
        model.put("numFiles", statusMailContentData.getNumFiles());

        if (statusMailContentData instanceof PayloadMailContentData) {
            PayloadMailContentData payloadMailContentData = (PayloadMailContentData) statusMailContentData;
            model.put("payloadDossierTitle", payloadMailContentData.getTitle());
            model.put("subsidiarityCheck", payloadMailContentData.getSubsidiarityCheck());
            model.put("payloadDossierList", payloadMailContentData.getPayloadDossierList());
        }

        return velocityService.applyTemplate(statusMailContentData.getTemplateFile(), model);
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

    @Override
    protected String buildMessageUrl(Long messageId) {
        Configuration config = configurationService.getConfiguration();
        return config.getApplicationUrl() + "messageViewSent.do" + "?mid=" + messageId;
    }
}
