package eu.europa.ec.etrustex.webaccess.business.mail;

import eu.europa.ec.etrustex.webaccess.business.api.*;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.persistence.*;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public abstract class AbstractMailHandlerService implements MailHandlerService {

    @Autowired
    protected VelocityService velocityService;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Autowired
    private BusinessDAO businessDAO;

    @Autowired
    private MessageStatusDAO messageStatusDAO;

    @Autowired
    protected ConfigurationService configurationService;

    @Autowired
    private NotificationDAO notificationDAO;

    @Autowired
    private BusinessUserConfigDAO businessUserConfigDAO;

    @Autowired
    protected MailboxManager mailboxManager;

    @Autowired
    protected AttachmentHandler attachmentHandler;

    @Autowired
    private PayloadMailContentExtractor payloadMailContentExtractor;

    protected static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected static final DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private static final String TEMPLATES_ROOT = "templates/";

    private static final String PAYLOAD_FOLDER = "payload";

    protected final Set<String> resources = new HashSet<>();

    protected final Set<String> payloadResources = new HashSet<>();

    private final int MAXIMUM_EMAIL_SUBJECT_LENGTH = 256;

    private Logger logger = Logger.getLogger(AbstractMailHandlerService.class);

    @PostConstruct
    private void init() {
        // check for root resources
        String resource = TEMPLATES_ROOT + contentUserTemplateName();
        if (velocityService.resourceExists(resource)) {
            resources.add(resource);
        }
        resource = TEMPLATES_ROOT + contentPartyTemplateName();
        if (velocityService.resourceExists(resource)) {
            resources.add(resource);
        }
        resource = TEMPLATES_ROOT + subjectTemplateName();
        if (velocityService.resourceExists(resource)) {
            resources.add(resource);
        }

        // check resources per business
        List<Business> allBusinesses = businessDAO.getAllBusinesses();
        for (Business business : allBusinesses) {
            Map<BusinessConfigProperty, String> businessConfigs = business.getBusinessConfigValues();
            String customViewName = businessConfigs.get(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
            if (customViewName != null && !customViewName.isEmpty()) {
                customViewName = customViewName.toLowerCase();
                resource = TEMPLATES_ROOT + customViewName + "/" + PAYLOAD_FOLDER + "/" + contentUserTemplateName();
                if (velocityService.resourceExists(resource)) {
                    payloadResources.add(resource);
                }
                resource = TEMPLATES_ROOT + customViewName + "/" + PAYLOAD_FOLDER + "/" + contentPartyTemplateName();
                if (velocityService.resourceExists(resource)) {
                    payloadResources.add(resource);
                }
                resource = TEMPLATES_ROOT + customViewName + "/" + PAYLOAD_FOLDER + "/" + subjectTemplateName();
                if (velocityService.resourceExists(resource)) {
                    payloadResources.add(resource);
                }
                resource = TEMPLATES_ROOT + customViewName + "/" + contentUserTemplateName();
                if (velocityService.resourceExists(resource)) {
                    resources.add(resource);
                }
                resource = TEMPLATES_ROOT + customViewName + "/" + contentPartyTemplateName();
                if (velocityService.resourceExists(resource)) {
                    resources.add(resource);
                }
                resource = TEMPLATES_ROOT + customViewName + "/" + subjectTemplateName();
                if (velocityService.resourceExists(resource)) {
                    resources.add(resource);
                }
            }
        }
    }

    /**
     * Creates the message mail to be send for an user
     *
     * @param messageId
     * @param bucId
     * @param notificationType
     * @return Mail object with all the info to send the mail
     */
    @Override
    public Mail prepareMessageMailForUser(Long messageId, Long bucId, Notification.NotificationType notificationType) {
        return getMessageMailWithNotification(messageId, bucId, null, notificationType).get(0);
    }

    /**
     * Creates the message mail to be send for a party
     *
     * @param messageId
     * @param partyId
     * @param notificationType
     * @return List of  mail objects with all the info to send the mails
     */
    @Override
    public List<Mail> prepareMessageMailForParty(Long messageId, Long partyId, Notification.NotificationType notificationType) {
        return getMessageMailWithNotification(messageId, null, partyId, notificationType);
    }

    /**
     * Creates the status mail to be send for an user
     *
     * @param messageStatusId
     * @param bucId
     * @return Mail object with all the info to send the mail
     */
    @Override
    public Mail prepareStatusMailForUser(Long messageStatusId, Long bucId) {
        return getStatusMailWithNotification(messageStatusId, bucId, null);
    }

    /**
     * Creates the status mail to be send for an party
     *
     * @param messageStatusId
     * @param partyId
     * @return Mail object with all the info to send the mail
     */
    @Override
    public Mail prepareStatusMailForParty(Long messageStatusId, Long partyId) {
        return getStatusMailWithNotification(messageStatusId, null, partyId);
    }

    private List<Mail> getMessageMailWithNotification(Long messageId, Long bucId, Long partyId, Notification.NotificationType notificationType) {
        Message message = messageDAO.findById(messageId);
        String userName = null;
        String receiverName = null;
        String email;
        Business business;
        List<Mail> mails = new ArrayList();

        if (bucId != null && partyId == null) {
            BusinessUserConfig businessUserConfig = businessUserConfigDAO.findById(bucId);
            business = businessUserConfig.getBusiness();
            userName = businessUserConfig.getName();
            email = businessUserConfig.getEmail();
        } else if (partyId != null && bucId == null) {
            Party party = partyDAO.findById(partyId);
            business = party.getBusiness();
            receiverName = party.getDisplayName();
            email = party.getEmail();
        } else {
            throw new IllegalStateException("partyId: " + partyId + ", bucId: " + bucId);
        }

        Map<BusinessConfigProperty, String> businessConfigs = business.getBusinessConfigValues();
        String customViewName = businessConfigs.get(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);

        MailData mailData = getMailData(messageId, bucId, customViewName);
        String subjectTemplateFile = mailData.getSubjectTemplateFile();
        String contentTemplateFile = mailData.getContentTemplateFile();
        String subject = null;

        MessageMailContentDTO messageMailContentDTO = new MessageMailContentDTO();
        messageMailContentDTO.setMessage(message);
        messageMailContentDTO.setUserName(userName);
        messageMailContentDTO.setReceiverName(receiverName);
        messageMailContentDTO.setContentTemplateFile(contentTemplateFile);

        MailContentData mailContentData = null;
        if (mailData.getMailContentData() != null && mailData.getMailContentData() instanceof PayloadMailContentData) {
            PayloadMailContentData payloadMailContentData = (PayloadMailContentData) mailData.getMailContentData();
            subject = buildSubject(subjectTemplateFile, message, payloadMailContentData.getTitle());
            mailContentData = payloadMailContentData;
        } else {
            subject = buildSubject(subjectTemplateFile, message, null);
            mailContentData = new MailContentData();
        }
        buildMessageMailContentData(mailContentData, messageMailContentDTO);
        String content = buildContent(mailContentData);

        String[] splittedMails = email.split(";|,");
        for (int i = 0; i < splittedMails.length; i++) {
            Notification notification = createAndSaveNotification(message, bucId, partyId, splittedMails[i], notificationType);
            Mail mail = buildMail(splittedMails[i], subject, content, notification.getId());
            mails.add(mail);
        }

        return mails;
    }

    private void buildMessageMailContentData(MailContentData mailContentData, MessageMailContentDTO messageMailContentDTO) {
        mailContentData.setTemplateFile(messageMailContentDTO.getContentTemplateFile());
        mailContentData.setMessageReferenceId(messageMailContentDTO.getMessage().getReferenceId());
        mailContentData.setMessageId(messageMailContentDTO.getMessage().getId());
        mailContentData.setUserName(messageMailContentDTO.getUserName());
        mailContentData.setReceiverName(messageMailContentDTO.getReceiverName());
        mailContentData.setMessageTitle(messageMailContentDTO.getMessage().getSubject());
        mailContentData.setMessageCreatedOn(messageMailContentDTO.getMessage().getCreatedOn());
    }

    private Mail getStatusMailWithNotification(Long messageStatusId, Long bucId, Long partyId) {
        MessageStatus messageStatus = messageStatusDAO.findById(messageStatusId);
        Message message = messageStatus.getMessage();
        String userName = null;
        String receiverName = message.getRemoteParty().getDisplayName();
        String senderName = message.getLocalParty().getDisplayName();
        Business business;
        String email;

        if (bucId != null && partyId == null) {
            BusinessUserConfig businessUserConfig = businessUserConfigDAO.findById(bucId);
            userName = businessUserConfig.getName();
            business = businessUserConfig.getBusiness();
            email = businessUserConfig.getStatusNotificationEmail();
        } else if (partyId != null && bucId == null) {
            Party party = partyDAO.findById(partyId);
            business = party.getBusiness();
            email = party.getStatusNotificationEmail();
        } else {
            throw new IllegalStateException("partyId: " + partyId + ", bucId: " + bucId);
        }

        Map<BusinessConfigProperty, String> businessConfigs = business.getBusinessConfigValues();
        String customViewName = businessConfigs.get(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);

        MailData mailData = getMailData(message.getId(), bucId, customViewName);
        String subjectTemplateFile = mailData.getSubjectTemplateFile();
        String contentTemplateFile = mailData.getContentTemplateFile();
        Long numOfAtt = mailboxManager.countBinaryAttachments(message.getId());
        String subject = null;

        StatusMailContentDTO statusMailContentDTO = new StatusMailContentDTO();
        statusMailContentDTO.setMessageStatus(messageStatus);
        statusMailContentDTO.setMessage(message);
        statusMailContentDTO.setUserName(userName);
        statusMailContentDTO.setReceiverName(receiverName);
        statusMailContentDTO.setSenderName(senderName);
        statusMailContentDTO.setContentTemplateFile(contentTemplateFile);
        statusMailContentDTO.setNumOfAtt(numOfAtt);

        MailContentData mailContentData = null;
        if (mailData.getMailContentData() != null && mailData.getMailContentData() instanceof PayloadMailContentData) {
            PayloadMailContentData payloadMailContentData = (PayloadMailContentData) mailData.getMailContentData();
            subject = buildSubject(subjectTemplateFile, messageStatus, payloadMailContentData.getTitle());
            mailContentData = payloadMailContentData;
        } else {
            subject = buildSubject(subjectTemplateFile, messageStatus, null);
            mailContentData = new MailContentData();
        }
        buildStatusMailContentData(mailContentData, statusMailContentDTO);
        String content = buildContent(mailContentData);
        Notification notification = createAndSaveNotification(messageStatus, bucId, partyId, email, Notification.NotificationType.STATUS_RECEIVED_EMAIL);

        return buildMail(email, subject, content, notification.getId());
    }

    private void buildStatusMailContentData(MailContentData mailContentData, StatusMailContentDTO statusMailContentDTO) {
        mailContentData.setTemplateFile(statusMailContentDTO.getContentTemplateFile());
        mailContentData.setMessageReferenceId(statusMailContentDTO.getMessage().getReferenceId());
        mailContentData.setMessageId(statusMailContentDTO.getMessage().getId());
        mailContentData.setUserName(statusMailContentDTO.getUserName());
        mailContentData.setReceiverName(statusMailContentDTO.getReceiverName());
        mailContentData.setSenderName(statusMailContentDTO.getSenderName());
        mailContentData.setMessageTitle(statusMailContentDTO.getMessage().getSubject());
        mailContentData.setStatus(statusMailContentDTO.getMessageStatus().getMstStatus().name());
        mailContentData.setStatusCode(statusMailContentDTO.getMessageStatus().getMstStatusCode());
        mailContentData.setCreatedByUser(statusMailContentDTO.getMessage().getCreatedBy().getName());
        mailContentData.setStatusDate(dateFormat.format(statusMailContentDTO.getMessageStatus().getCreatedOn()));
        mailContentData.setStatusTime(timeFormat.format(statusMailContentDTO.getMessageStatus().getCreatedOn()));
        mailContentData.setNumFiles(statusMailContentDTO.getNumOfAtt());
    }

    private Notification createAndSaveNotification(Message message, Long bucId, Long partyId, String email, Notification.NotificationType notificationType) {
        Notification notification = createNotification(bucId, partyId, email, notificationType);
        notification.setMessage(message);
        notificationDAO.save(notification);
        return notification;
    }

    private Notification createAndSaveNotification(MessageStatus messageStatus, Long bucId, Long partyId, String email, Notification.NotificationType notificationType) {
        Notification notification = createNotification(bucId, partyId, email, notificationType);
        notification.setMessageStatus(messageStatus);
        notificationDAO.save(notification);
        return notification;
    }

    private static Notification createNotification(Long bucId, Long partyId, String email, Notification.NotificationType notificationType) {
        Notification notification = new Notification();
        notification.setBusinessUserConfigId(bucId);
        notification.setPartyId(partyId);
        notification.setMailAddress(email);
        notification.setNotificationType(notificationType);
        notification.setNotificationState(Notification.NotificationState.CREATED);
        notification.setCreatedOn(new Date());
        return notification;
    }

    protected static Mail buildMail(final String recipientEmailAddress, final String subject, final String content, final Long notificationId) {
        Mail mail = new Mail();
        mail.setNotificationId(notificationId);
        mail.setSubject(subject);
        mail.setContent(content);
        mail.setMailAddress(recipientEmailAddress);

        Calendar cal = Calendar.getInstance();
        mail.setSentDate(cal.getTime());
        return mail;
    }

    /* this method is used to create the message url in notification emails */
    protected String buildMessageUrlWS(Long messageId) {
        Configuration config = configurationService.getConfiguration();
        return config.getApplicationUrl() + "messageViewReceivedWS.do" + "?mid=" + messageId;
    }

    protected String buildMessageUrl(Long messageId) {
        Configuration config = configurationService.getConfiguration();
        return config.getApplicationUrl() + "messageViewReceived.do" + "?mid=" + messageId;
    }

    private String contentTemplateFile(String customViewName, boolean isParty, boolean fetchPayload) {
        String templateName = isParty ? contentPartyTemplateName() : contentUserTemplateName();
        return templateFile(customViewName, templateName, fetchPayload);
    }

    private String subjectTemplateFile(String customViewName, boolean fetchPayload) {
        String templateName = subjectTemplateName();
        return templateFile(customViewName, templateName, fetchPayload);
    }

    private String templateFile(String customViewName, String templateName, boolean fetchPayload) {
        String templateLocation = null;
        if (customViewName != null && !customViewName.isEmpty()) {
            if (fetchPayload) {
                templateLocation = TEMPLATES_ROOT + customViewName.toLowerCase() + "/" + PAYLOAD_FOLDER + "/" + templateName;
                if (resourceExists(templateLocation)) {
                    return templateLocation;
                }
            }
            templateLocation = TEMPLATES_ROOT + customViewName.toLowerCase() + "/" + templateName;
            if (resourceExists(templateLocation)) {
                return templateLocation;
            }
        }
        return TEMPLATES_ROOT + templateName;
    }

    private String buildSubject(String subjectTemplateFile, Message message, String payloadDossierTitle) {
        Map<String, Object> model = new HashMap<>();
        model.put("messageTitle", message.getSubject());
        model.put("payloadDossierTitle", payloadDossierTitle);
        String subject = velocityService.applyTemplate(subjectTemplateFile, model);
        if (subject.length() > MAXIMUM_EMAIL_SUBJECT_LENGTH) { // Email subject notification are truncated after a maximum length of characters
            subject = subject.substring(0, MAXIMUM_EMAIL_SUBJECT_LENGTH - 3);
            subject += "...";
        }
        return subject;
    }

    private String buildSubject(String subjectTemplateFile, MessageStatus messageStatus, String payloadDossierTitle) {
        Map<String, Object> model = new HashMap<>();
        model.put("payloadDossierTitle", payloadDossierTitle);
        model.put("messageDossierId", messageStatus.getMessage().getReferenceId());
        model.put("messageMstStatus", messageStatus.getMstStatus().name());
        model.put("messageTitle", messageStatus.getMessage().getSubject());
        return velocityService.applyTemplate(subjectTemplateFile, model);
    }

    private boolean resourceExists(String resource) {
        return resources.contains(resource) || payloadResources.contains(resource);
    }

    private MailData getMailData(Long messageId, Long bucId, String customViewName) {
        MailData mailData = new MailData();
        MailContentData mailContentData = null;
        String subjectTemplateFile = null;
        String contentTemplateFile = null;

        if (customViewName != null) {
            subjectTemplateFile = subjectTemplateFile(customViewName, true);
            contentTemplateFile = contentTemplateFile(customViewName, bucId == null, true);

            if (payloadResources.contains(subjectTemplateFile) || payloadResources.contains(contentTemplateFile)) {
                String payload = null;

                try {
                    payload = attachmentHandler.retrievePayloadContent(messageId);
                } catch (EtxException etxExc) {
                    logger.warn("The payload could not succesfully be loaded" + etxExc);
                }

                if (payload != null) {
                    mailContentData = payloadMailContentExtractor.extractPayloadMailContentDataFromPayload(payload);

                    if (mailContentData != null && !mailContentData.isValid()) {
                        subjectTemplateFile = subjectTemplateFile(customViewName, false);
                        contentTemplateFile = contentTemplateFile(customViewName, bucId == null, false);
                    }
                } else {
                    //not succesfull show default
                    subjectTemplateFile = subjectTemplateFile(customViewName, false);
                    contentTemplateFile = contentTemplateFile(customViewName, bucId == null, false);
                }
            } else {
                subjectTemplateFile = subjectTemplateFile(customViewName, false);
                contentTemplateFile = contentTemplateFile(customViewName, bucId == null, false);
            }
        } else {
            subjectTemplateFile = subjectTemplateFile(customViewName, false);
            contentTemplateFile = contentTemplateFile(customViewName, bucId == null, false);
        }

        mailData.setSubjectTemplateFile(subjectTemplateFile);
        mailData.setContentTemplateFile(contentTemplateFile);
        mailData.setMailContentData(mailContentData);
        return mailData;
    }


    protected abstract String buildContent(MailContentData statusMailContentData);

    protected abstract String subjectTemplateName();

    protected abstract String contentUserTemplateName();

    protected abstract String contentPartyTemplateName();

    private static class StatusMailContentDTO {
        private MessageStatus messageStatus;
        private Message message;
        private String userName;
        private String receiverName;
        private String senderName;
        private String contentTemplateFile;
        private Long numOfAtt;

        public MessageStatus getMessageStatus() {
            return messageStatus;
        }

        public void setMessageStatus(MessageStatus messageStatus) {
            this.messageStatus = messageStatus;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getContentTemplateFile() {
            return contentTemplateFile;
        }

        public void setContentTemplateFile(String contentTemplateFile) {
            this.contentTemplateFile = contentTemplateFile;
        }

        public Long getNumOfAtt() {
            return numOfAtt;
        }

        public void setNumOfAtt(Long numOfAtt) {
            this.numOfAtt = numOfAtt;
        }


    }

    private static class MessageMailContentDTO {
        private Message message;
        private String userName;
        private String receiverName;
        private String contentTemplateFile;

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getContentTemplateFile() {
            return contentTemplateFile;
        }

        public void setContentTemplateFile(String contentTemplateFile) {
            this.contentTemplateFile = contentTemplateFile;
        }

    }

}
