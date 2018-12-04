package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.VelocityService;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.BusinessConfigProperty;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.converters.JsonStringAttachmentsToFileElementVO;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.*;
import eu.europa.ec.etrustex.webaccess.utils.applet.Encoder;
import eu.europa.ec.etrustex.webaccess.web.i18n.DataSourceMessageSource;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.WebHandlerHelper;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EDMAUtils;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EdmaMessage;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static eu.europa.ec.etrustex.webaccess.utils.applet.Encoder.encode;

/**
 * Controller in charge of generating the jnlp file for the webstat components
 *
 */
@Controller
@RequestMapping("applet")
public class WebstartController {

    private final Logger logger = Logger.getLogger(WebstartController.class);
    private static final String EMPTY_ARGUMENT = "#null#";

    @Autowired
    private MailboxManager mailboxManager;

    @Autowired
    private PartyManager partyManager;

    @Autowired
    private UserSessionContext userSessionContext;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private EDMAUtils edmaUtils;

    @Autowired
    private DataSourceMessageSource messageSource;

    @Autowired
    private WebHandlerHelper handlerHelper;

    @Autowired
    private VelocityService velocityService;


    /**
     * This is the method called by the web page which generates the jnlp file for the donwload webstart
     * @param msgId message id
     * @param isCertificateTrusted flags which indicates if the message certificate is trusted
     * @param externalToken session token
     * @param request http request
     * @return the jnlp string generated
     */
    @RequestMapping(method = RequestMethod.GET,
            produces = "application/x-java-jnlp-file;charset=UTF-8",
            value = "downloadApplet.do",
            params = {"msgId", "token"}
    )

    @ResponseBody
    public String generateDownloadAppletJnlp(@RequestParam(value = "sessionId") String sessionId,@RequestParam(value = "msgId") Long msgId, @RequestParam(value = "trusted") Boolean isCertificateTrusted, @RequestParam(value = "token") String externalToken,
                                             HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = prepareVelocityModel(msgId,null, null, isCertificateTrusted, externalToken, request, sessionId, null, null);
        response.setHeader("Content-Disposition", "attachment;");
        return velocityService.applyTemplate("jnlp/download/APPLICATION_TEMPLATE.JNLP", model);
    }

    /**
     * This is the method called by the web page which generates the jnlp file for the upload webstart
     * @param sessionId Session id.
     * @param msgId Message id.
     * @param partyId Party id.
     * @param externalToken Session token.
     * @param currentAttachments Selected attachments.
     * @param remotePartyNodeName Selected destination party.
     * @param request HTTP request.
     * @return The JNLP string generated.
     */
    @RequestMapping(method = RequestMethod.POST,
            produces = "application/x-java-jnlp-file;charset=UTF-8",
            value = "uploadApplet.jnlp",
            params = {"sessionId","msgId", "partyId", "token"}
    )
    @ResponseBody
    public String generateUploadAppletJnlp(@RequestParam(value = "sessionId") String sessionId, @RequestParam(value = "msgId") Long msgId,@RequestParam(value = "msgSubject") String msgSubject, @RequestParam(value = "partyId") Long partyId ,@RequestParam(value = "token") String externalToken,
                                           @RequestParam(value = "currentAttachments") String currentAttachments, @RequestParam(value = "remotePartyNodeName") String remotePartyNodeName, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = prepareVelocityModel(msgId,msgSubject ,partyId, null, externalToken, request, sessionId, currentAttachments, remotePartyNodeName);
        response.setHeader("Content-Disposition", "attachment;");
        return velocityService.applyTemplate("jnlp/upload/APPLICATION_TEMPLATE.JNLP", model);
    }

    private Map<String, Object> prepareVelocityModel(Long msgId,String msgSubject, Long partyId, Boolean isCertificateTrusted, String externalToken, HttpServletRequest request, String sessionId, String currentAttachments, String remotePartyNodeName) {
        Locale locale = RequestContextUtils.getLocale(request);
        Configuration configuration = configurationService.getConfiguration();

        Party localParty;
        if (partyId != null) {
            localParty = partyManager.getPartyById(partyId);

        } else {
            localParty = mailboxManager.getMessageByMessageId(msgId).getLocalParty();
        }
        String customViewName = localParty.getBusiness().getBusinessConfigValues().get(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        BusinessCustomViewName businessCustomViewName = BusinessCustomViewName.forCustomViewName(customViewName);

        Map<String, Object> model = new HashMap<>();
        model.put("etxLocaleCode", locale.toString());
        model.put("etxLookupFailsDefaultEnabled", configuration.isLookupFailsDefaultEnabled());
        model.put("etxConnectionToken", externalToken);
        model.put("etxBusiness", businessCustomViewName.name());
        model.put("etxLocalPartyId", localParty.getId());
        model.put("etxIsTrustedCertificate", isCertificateTrusted != null ? isCertificateTrusted : EMPTY_ARGUMENT);
        model.put("etxIcaDetails", EMPTY_ARGUMENT);
        model.put("etxEncodedFileElements", EMPTY_ARGUMENT);
        model.put("etxEncodedMessageSignatureData", EMPTY_ARGUMENT);
        model.put("etxIsLightweightClient", EMPTY_ARGUMENT);
        model.put("etxRetentionExpired", EMPTY_ARGUMENT);
        model.put("etxRetentionExpiredMessage", EMPTY_ARGUMENT);
        model.put("etxMessageSubject", EMPTY_ARGUMENT);
        model.put("etxMessageRemotePartyName", EMPTY_ARGUMENT);
        model.put("etxSessionId", sessionId);
        model.put("etxBusinessFolderStructureEnabled", localParty.getBusiness().getBusinessConfigValues().get(BusinessConfigProperty.BUS_FOLDER_STRUCTURE_ENABLED));

        if (msgId != null && msgId != -1) {
            populateContextForExistingMessage(model, msgId, configuration, locale);
        } else {
            if(msgSubject==null||msgSubject.trim().equals("")){
                msgSubject=EMPTY_ARGUMENT;
            }
            model.put("etxMessageSubject", msgSubject);
            List<FileElementVO> fileElementVOS=null;
            if(!currentAttachments.equals("null")){
                String attachmentsJsonString= new String(Base64.decodeBase64(currentAttachments));
                fileElementVOS= JsonStringAttachmentsToFileElementVO.convert(attachmentsJsonString);
            }
            populateContextForNewMessage(model, partyId, fileElementVOS, remotePartyNodeName);
        }

        return model;
    }


    private void populateContextForNewMessage(Map<String, Object> model, Long partyId, List<FileElementVO> currentAttachments, String remotePartyNodeName) {
        try {
            final Party localParty = userSessionContext.getMessagePartyById(partyId);
            ICADetails icaDetails = handlerHelper.getIcaDetails(localParty, remotePartyNodeName);

            model.put("etxIcaDetails", icaDetails != null ? Encoder.encode(icaDetails) : EMPTY_ARGUMENT);
            model.put("etxIsLightweightClient", handlerHelper.isLightweightClient(icaDetails));
            model.put("etxEncodedFileElements", currentAttachments != null && currentAttachments.size()>0 ? Encoder.encode(currentAttachments) : EMPTY_ARGUMENT);

        } catch (Exception e) {
            logger.warn("Couldn't load system configuration!", e);
        }
    }

    private void populateContextForExistingMessage(Map<String, Object> model, Long msgId, Configuration configuration, Locale locale) {
        User user = userSessionContext.getUser();
        final Message message = mailboxManager.getMessageDetailsAndFetchSignatureEagerly(msgId, user);
        message.setAttachments(mailboxManager.getAttachments(msgId, user));

        model.put("etxMessageSubject", message.getSubject());
        model.put("etxMessageRemotePartyName", message.getRemoteParty().getNodeName());

        final Party localParty = message.getLocalParty();
        model.put("etxLocalPartyId", localParty.getId());

        Date createdOn = message.getCreatedOn();
        RetentionMetadata retentionMetadata = configurationService.computeRetentionMetadata(configuration, createdOn);

        model.put("etxRetentionExpired", retentionMetadata.getRetentionExpired());
        try {
            model.put("etxRetentionExpiredMessage", Encoder.encode(messageSource.getMessage("label.retention.expired", new Object[]{configuration.getRetentionPolicyWeeks()}, locale)));
        } catch (IOException e) {
            logger.error("The following error occurred while encoding the expiration error string for the message with id: " + msgId, e);
        }

        try {
            ICADetails icaDetails = handlerHelper.getIcaDetails(localParty, message.getRemoteParty().getNodeName());
            model.put("etxIcaDetails", icaDetails != null ? Encoder.encode(icaDetails) : EMPTY_ARGUMENT);

            String payloadXml = handlerHelper.fetchPayloadIfReady(null, msgId);
            EdmaMessage edmaMessage = new EdmaMessage();
            if (payloadXml != null) {
                try {
                    edmaMessage = edmaUtils.convertXMLToEdma(payloadXml);
                } catch (Exception e) {
                    logger.error("The following error occurred while fetching payload for the message with id: " + msgId, e);
                }
            }

            List<FileElementVO> fileElementsToToEncode = handlerHelper.getFileElementsToEncode(message.getAttachments(), edmaMessage.getAttachmentMetadataList());
            model.put("etxEncodedFileElements", fileElementsToToEncode != null && fileElementsToToEncode.size()>0 ? Encoder.encode(fileElementsToToEncode) : EMPTY_ARGUMENT);

            MessageSignatureData messageSignatureData = handlerHelper.getMessageSignatureData(message.getSignatures());
            model.put("etxEncodedMessageSignatureData", messageSignatureData != null ? encode(messageSignatureData) : EMPTY_ARGUMENT);

            model.put("etxIsLightweightClient", handlerHelper.isLightweightClient(icaDetails));
        } catch (Exception e) {
            logger.warn("Couldn't load system configuration!", e);
        }
    }

}
