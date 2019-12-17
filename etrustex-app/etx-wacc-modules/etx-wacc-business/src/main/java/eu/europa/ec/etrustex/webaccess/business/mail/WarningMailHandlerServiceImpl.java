package eu.europa.ec.etrustex.webaccess.business.mail;

import eu.europa.ec.etrustex.webaccess.business.api.MailHandlerService;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service("warningMailHandler")
public class WarningMailHandlerServiceImpl extends MailHandlerServiceAdapter implements MailHandlerService {

    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Autowired
    private ConfigurationService configurationService;

    private static final String CONTENT_USER_TEMPLATE_NAME = "user-warning-email.vm";

    private static final String CONTENT_PARTY_TEMPLATE_NAME = "party-warning-email.vm";

    private static final String SUBJECT_TEMPLATE_NAME = "warning-subject.vm";

    @Override
    protected String buildContent(MailContentData mailContentData) {
        Configuration configuration = configurationService.getConfiguration();
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(mailContentData.getMessageCreatedOn());
        expiryDate.add(Calendar.DATE, configuration.getRetentionPolicyWeeks() * 7);

        Map<String, Object> model = new HashMap<>();
        model.put("userName", mailContentData.getUserName());
        model.put("receiverName", mailContentData.getReceiverName());
        model.put("retentionPolicyWarningDays", configuration.getRetentionPolicyWarningBeforeDays());
        model.put("expirationDate", formatter.format(expiryDate.getTime()));
        model.put("messageTitle", mailContentData.getMessageTitle());
        model.put("messageURL", buildMessageUrl(mailContentData.getMessageId()));

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
