package eu.europa.ec.etrustex.webaccess.web.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import eu.europa.ec.etrustex.webaccess.business.api.*;
import eu.europa.ec.etrustex.webaccess.business.facade.BusinessFacade;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.*;
import eu.europa.ec.etrustex.webaccess.utils.EtxEntityException;
import eu.europa.ec.etrustex.webaccess.utils.HashHelper;
import eu.europa.ec.etrustex.webaccess.utils.ReferenceIDGenerator;
import eu.europa.ec.etrustex.webaccess.utils.applet.Decoder;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.MessageFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.payload.PayloadBuilder;
import eu.europa.ec.etrustex.webaccess.web.payload.PayloadBuilderProvider;
import eu.europa.ec.etrustex.webaccess.web.utils.MessageUtils;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.WebHandlerHelper;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EDMAUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import xades4j.utils.StringUtils;

import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MessageSaveController {

    public static final String PAYLOAD_NAME = "payload.xml";
    private static final Logger logger = Logger.getLogger(MessageSaveController.class);

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Autowired
    protected BusinessFacade businessFacade;
    @Autowired
    protected MessageUtils messageUtils;
    @Autowired
    protected PageState pageState;
    @Autowired
    protected UserSessionContext userSessionContext;
    @Autowired
    protected MailboxManager mailboxManager;
    @Autowired
    protected SecurityChecker securityChecker;
    @Autowired
    protected IcaManager icaManager;
    @Autowired
    protected IcaDetailsService icaDetailsService;
    @Autowired
    private PartyPropertyEditor partyPropertyEditor;
    @Autowired
    private WebHandlerHelper webHandlerHelper;
    @Autowired
    private AttachmentHandler attachmentHandler;
    @Autowired
    private PartyManager partyManager;

    @Autowired
    private EDMAUtils edmaUtils;

    protected boolean isAuthorized(Message message) {
        return message != null
                && message.getActiveState()
                && securityChecker.canAccessMessagesOfParty(message.getLocalParty())
                && (message.getMsgState().equals(Message.MessageState.DRAFT) || message.getMsgState().equals(Message.MessageState.IN_PREPARATION));
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveDraftMessage.do")
    public ModelAndView doSaveDraftMessage(@ModelAttribute("formBean") MessageFormBean formBean, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView;
        ModelAndView errorRedirection = securityCheck(formBean, request);
        if (errorRedirection == null) {
            Message persistedMessage;
            Map<BusinessConfigProperty, String> businessConfigs = formBean.getMessage().getLocalParty().getBusiness().getBusinessConfigValues();
            String customViewName = businessConfigs.get(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
            String payloadXML = null;
            if (PayloadBuilderProvider.hasPayloadBuilder(customViewName)) {
                payloadXML = createPayloadElement(formBean.getMessageMetadata(), customViewName);
            }
            persistedMessage = saveMessage(Message.MessageState.DRAFT, formBean, payloadXML, null);
            pageState.setMessageId(persistedMessage.getId());
            pageState.setNotifySuccessfulOperation(Boolean.TRUE);
            modelAndView = new ModelAndView(new RedirectView(getMessageEditAction()));
            modelAndView.addAllObjects(pageState.getParams());
            modelAndView.addObject("ps", pageState);
        } else {
            modelAndView = errorRedirection;
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveSentMessage.do") // Message Send button
    public ModelAndView doSaveSentMessage(@ModelAttribute("formBean") MessageFormBean formBean, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView;
        ModelAndView errorRedirection = securityCheck(formBean, request);
        if (errorRedirection == null) {
            Message persistedMessage;
            Map<BusinessConfigProperty, String> businessConfigs = formBean.getMessage().getLocalParty().getBusiness().getBusinessConfigValues();
            String customViewName = businessConfigs.get(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
            String payloadXML = null;
            Long payloadId = null;
            if (PayloadBuilderProvider.hasPayloadBuilder(customViewName)) {
                payloadXML = createPayloadElement(formBean.getMessageMetadata(), customViewName);
                Party localParty = formBean.getMessage().getLocalParty();
                String remotePartyName = formBean.getMessage().getRemoteParty().getNodeName();
                ICADetails icaDetails = webHandlerHelper.getIcaDetails(localParty, remotePartyName);
                payloadId = sendPayload(formBean.getMessage(), payloadXML, icaDetails);
            }
            try {
                persistedMessage = saveMessage(Message.MessageState.IN_PREPARATION, formBean, payloadXML, payloadId);
            } catch (EtxEntityException e) {
                if (e.getMessage().equals(BusinessFacade.SENDING_BUNDLE_ERROR)) {
                    pageState.setMessageId(e.getEntity().getId());
                    pageState.setStatus("sendingError");
                    modelAndView = new ModelAndView(new RedirectView(getMessageEditAction()));
                    modelAndView.addAllObjects(pageState.getParams());
                    return modelAndView;
                } else {
                    throw e;
                }

            }
            pageState.setNotifySuccessfulOperation(Boolean.TRUE);
            pageState.setPartyId(persistedMessage.getLocalParty().getId());
            modelAndView = new ModelAndView(new RedirectView(WebAction.INBOX.getDo()));
            modelAndView.addAllObjects(pageState.getParams());
        } else {
            modelAndView = errorRedirection;
        }
        return modelAndView;
    }

    // should be made a public utility function...
    // should also be tested
    private static List<Long> fromJsonArray(String jsonArray) {
        if(jsonArray == null || jsonArray.equals("") ) return null;
        try {
            JsonParser parser = new JsonParser();
            JsonArray rawArray = parser.parse(jsonArray).getAsJsonArray();

            List<Long> res = new ArrayList<>(rawArray.size());

            for (JsonElement e : rawArray) {
                res.add(e.getAsLong());
            }
            return res;
        } catch(Exception e) {
            return null;
        }
    }

    private Message saveMessage(Message.MessageState messageState, MessageFormBean formBean, String payloadXML, Long payloadId) throws IOException, ClassNotFoundException {
        Message message = prepareMessage(formBean.getMessage());
        message.setMsgState(messageState);

        List<Long> uploadedFiles = fromJsonArray(formBean.getFileIdsListJSON());

        if (payloadId != null && uploadedFiles!=null) {
            uploadedFiles.add(payloadId);
        }
        Long idSelectedPayloadFile = null;
        if (formBean.getEncodedIdSelectedPayloadFile() != null && !formBean.getEncodedIdSelectedPayloadFile().equals("")) {
            idSelectedPayloadFile = (Decoder.decode(formBean.getEncodedIdSelectedPayloadFile(), Long.class)).get(0);
        }

        String xmlSignature = formBean.getXmlSignedBundle(); //this is the whole signed bundle, we should use this instead of the signature and signed data below...
        String signedData = formBean.getXmlDataToSign();
        String extractedSignature = formBean.getXmlExtractedSignature();

        SignatureVO signature = null;
        if(!StringUtils.isNullOrEmptyString(xmlSignature)) {
            signature = new SignatureVO(extractedSignature, signedData, null );
        }

        return businessFacade.saveOrUpdateMessage(message, uploadedFiles, idSelectedPayloadFile, payloadXML, signature, userSessionContext.getUser());
    }

    private SignatureVO decodeSignature(String encodedSignature) throws IOException, ClassNotFoundException {
        SignatureVO signature = null;
        if (encodedSignature != null) {
            final List<SignatureVO> signatureList = Decoder.decode(encodedSignature, SignatureVO.class);
            signature = CollectionUtils.isNotEmpty(signatureList) ? signatureList.get(0) : null;
        }

        return signature;
    }

    protected Message prepareMessage(Message message) throws IOException, ClassNotFoundException {
        messageUtils.limitToMessageMaxSize(message);
        message.setIssueDate(new Date());
        message.setActiveState(true);

        Party remoteParty = partyManager.getRemotePartyByNodeNameBusId(message.getRemoteParty().getNodeName(), message.getLocalParty().getBusiness().getId());
        message.setRemoteParty(remoteParty);

        return message;
    }


    private List<Long> decodeUploadedFiles(String encodedFileList) throws IOException, ClassNotFoundException {
        return encodedFileList != null ? Decoder.decode(encodedFileList, Long.class) : null;
    }

    private <T extends Serializable> T decodeParameter(String encodedParameter, Class<T> clazz) throws IOException, ClassNotFoundException {
        T encryptedKey = null;
        if (encodedParameter != null && !encodedParameter.isEmpty()) {
            encryptedKey = Decoder.decode(encodedParameter, clazz).get(0);
        }
        return encryptedKey;
    }
    @InitBinder
    public void initBinderAll(WebDataBinder binder) {
        binder.registerCustomEditor(Party.class, partyPropertyEditor);
    }


    private Long sendPayload(Message message, String xmlPayload, ICADetails icaDetails) throws Exception {

        final byte[] payloadContent = xmlPayload.getBytes("UTF-8");
        byte[] contentChecksum = HashHelper.getInstance().hash(payloadContent, HashHelper.HashMethodType.SHA_512.getCode());
        byte[] transmissionChecksum = contentChecksum;
        String contentChecksumMethod = HashHelper.HashMethodType.SHA_512.getCode();
        String transmissionChecksumMethod = HashHelper.HashMethodType.SHA_512.getCode();
        String filename = PAYLOAD_NAME;
        String mimeType = MediaType.TEXT_XML;
        AttachmentType attachmentType = AttachmentType.METADATA;
        long fileSize = payloadContent.length;
        String fileReferenceID = ReferenceIDGenerator.generateAttachmentReferenceId();
        logger.info("Upload started: fileReferenceId = " + fileReferenceID);
        Long attachmentId;

        attachmentId = attachmentHandler.uploadAttachmentToNode(message.getLocalParty().getId(), fileReferenceID, filename,"/", fileSize, mimeType, attachmentType.name(),
                contentChecksum, contentChecksumMethod, transmissionChecksum, transmissionChecksumMethod, null,
                icaDetails.getX509SubjectName(), new ByteArrayDataSource(payloadContent, mimeType));

        logger.info("Upload finished: fileReferenceId = " + fileReferenceID);
        return attachmentId;
    }


    private ModelAndView securityCheck(MessageFormBean formBean, HttpServletRequest request) {
        ModelAndView modelAndView = null;
        if (formBean.getMessage().getId() != null) {
            Message origMessage = mailboxManager.getMessageByMessageId(formBean.getMessage().getId());
            if (!isAuthorized(origMessage)) {
                modelAndView = new ModelAndView(new RedirectViewWithLogging("error/error403.do", request, userSessionContext.getUser().getLogin()));
            }
        } else {
            if (!securityChecker.canAccessMessagesOfParty(formBean.getMessage().getLocalParty())) {
                modelAndView = new ModelAndView(new RedirectViewWithLogging("error/error403.do", request, userSessionContext.getUser().getLogin()));
            }
        }
        return modelAndView;
    }

    private String createPayloadElement(String jsonMetadata, String businessName) {
        PayloadBuilder payloadBuilder = PayloadBuilderProvider.getPayloadBuilder(businessName);
        String xmlPayload = payloadBuilder.buildPayload(jsonMetadata);
        return xmlPayload;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, value = "reloadMessageIca.do")
    @ResponseBody
    public IcaDetailsVO doReloadMessageIca(@RequestParam(value = "localPartyNodeName", required = true) String localPartyNodeName, @RequestParam(value = "remotePartyNodeName", required = true) String remotePartyNodeName) {
        PartyIca partyIca = icaManager.getActiveIcaBySenderReceiver(localPartyNodeName, remotePartyNodeName);
        NodeIcaDetails nodeIcaDetails = icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, false);

        return adaptMessageIcaDetails(nodeIcaDetails);
    }

    private IcaDetailsVO adaptMessageIcaDetails(NodeIcaDetails nodeIcaDetails) {
        IcaDetailsVO icaDetailsVO = new IcaDetailsVO();

        if (nodeIcaDetails != null) {
            icaDetailsVO.setSenderParty(nodeIcaDetails.getLocalParty());
            icaDetailsVO.setReceiverParty(nodeIcaDetails.getRemoteParty());
            if (nodeIcaDetails.getExtendedCertificateData() != null) {
                try {
                    ExtendedCertificateData extendedCertificateData = nodeIcaDetails.getExtendedCertificateData();
                    icaDetailsVO.setVersion(extendedCertificateData.getVersion());
                    icaDetailsVO.setSerialNumber(extendedCertificateData.getSerialNumber());
                    icaDetailsVO.setIssuerDN(extendedCertificateData.getIssuerDN());
                    icaDetailsVO.setStartDate(extendedCertificateData.getStartDate().toString());
                    icaDetailsVO.setFinalDate(extendedCertificateData.getEndDate().toString());
                    icaDetailsVO.setSubjectDN(extendedCertificateData.getSubjectDN());
                    icaDetailsVO.setSignatureAlgorithm(extendedCertificateData.getSignatureAlgorithm());
                    icaDetailsVO.setHasCertificate(true);
                } catch (Exception e) {
                    logger.warn("missing or incorrect ICA certificate", e.getCause());
                }
            }
            icaDetailsVO.setConfidentialityCode(nodeIcaDetails.getConfidentialityCode() != null ? nodeIcaDetails.getConfidentialityCode().name() : "");
            icaDetailsVO.setIntegrityCode(nodeIcaDetails.getIntegrityCode() != null ? nodeIcaDetails.getIntegrityCode().name() : "");
            icaDetailsVO.setLastDateReloaded(formatter.format(nodeIcaDetails.getCreationDate()));
            icaDetailsVO.setIcaStatus(IcaDetailsVO.IcaStatus.OK);
            icaDetailsVO.setActiveState(nodeIcaDetails.isActiveState());
        } else {
            icaDetailsVO.setIcaStatus(IcaDetailsVO.IcaStatus.UPDATE_FAILED);
        }

        return icaDetailsVO;
    }

    private String getMessageEditAction() {
        return userSessionContext.isUsingWebStart() ? WebAction.MESSAGE_EDIT_WS.getDo() : WebAction.MESSAGE_EDIT.getDo();
    }

}