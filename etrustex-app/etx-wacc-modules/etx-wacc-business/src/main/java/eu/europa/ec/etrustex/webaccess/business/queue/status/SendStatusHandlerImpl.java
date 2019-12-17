package eu.europa.ec.etrustex.webaccess.business.queue.status;

import ec.schema.xsd.ack_2.AcknowledgmentType;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseRequest;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseResponse;
import eu.europa.ec.etrustex.webaccess.business.message.MessageType;
import eu.europa.ec.etrustex.webaccess.business.message.NodeStatusResponseCode;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.MessageStatus;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.persistence.MessageStatusDAO;
import eu.europa.ec.etrustex.webaccess.webservice.status.ApplicationResponseClientService;
import oasis.names.specification.ubl.schema.xsd.applicationresponse_2.ApplicationResponseType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentResponseType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ResponseType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class SendStatusHandlerImpl implements SendStatusHandler {

    private final Logger logger = Logger.getLogger(SendStatusHandlerImpl.class);

    @Autowired
    private MessageStatusDAO messageStatusDAO;

    @Autowired
    private ApplicationResponseClientService applicationResponseClientService;

    @Override
    @Transactional(readOnly = false)
    public void handleSendStatus(Long messageStatusId) throws Exception {
        logger.info("NODE.applicationResponseService.sendStatus for messageStatusId: " + messageStatusId);

        MessageStatus messageStatus = messageStatusDAO.findById(messageStatusId);

        // service call
        Party localParty = messageStatus.getMessage().getLocalParty();
        SubmitApplicationResponseRequest request = new SubmitApplicationResponseRequest();
        request.setApplicationResponse(convertMessageStatus(messageStatus));
        SubmitApplicationResponseResponse response = applicationResponseClientService.sendMessageStatus(localParty.getNodeUserName(),
                localParty.getNodeUserPass(), request, messageStatus.getMessage().getLocalParty().getNodeName(),
                messageStatus.getMessage().getRemoteParty().getNodeName());

        boolean result = processResponse(response);
        logger.info("NODE.documentBundleService.sendStatus UUID: " + messageStatus.getStatusUuid() + ", ack: " + result);
        messageStatus.setMstState(result ? MessageStatus.State.SENT : MessageStatus.State.FAILED);
        messageStatus.setUpdatedOn(new Date());
        messageStatusDAO.saveOrUpdateMessageStatus(messageStatus);
    }

    @Override
    @Transactional(readOnly = false)
    public void handleError(Long messageStatusId) {
        MessageStatus messageStatus = messageStatusDAO.findById(messageStatusId);
        if (messageStatus != null) {
            logger.info("handling send status error for message status: " + messageStatusId);
            messageStatus.setMstState(MessageStatus.State.FAILED);
            messageStatus.setUpdatedOn(new Date());
            messageStatusDAO.saveOrUpdateMessageStatus(messageStatus);
        } else {
            logger.warn("null message status not expected here");
        }
    }

    protected ApplicationResponseType convertMessageStatus(MessageStatus messageStatus) throws DatatypeConfigurationException {
        Message message = messageStatus.getMessage();
        ApplicationResponseType sdo = new ApplicationResponseType();

        IDType id = new IDType();
        sdo.setID(id);
        id.setValue(messageStatus.getStatusUuid());

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(message.getIssueDate().getTime());
        XMLGregorianCalendar xgcDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH) + 1, gc.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);

        IssueDateType issueDate = new IssueDateType();
        issueDate.setValue(xgcDate);
        sdo.setIssueDate(issueDate);

        DocumentResponseType documentResponse = new DocumentResponseType();

        ResponseType response = new ResponseType();

        ReferenceIDType referenceID = new ReferenceIDType();
        String messageReferenceUuid = message.getBundleId();
        referenceID.setValue(messageReferenceUuid);
        response.setReferenceID(referenceID);

        ResponseCodeType responseCode = new ResponseCodeType();
        responseCode.setValue(NodeStatusResponseCode.BDL7.getCode());
        response.setResponseCode(responseCode);
        documentResponse.setResponse(response);

        DocumentReferenceType documentReference = new DocumentReferenceType();
        IDType refId = new IDType();
        refId.setValue(messageReferenceUuid);
        documentReference.setID(refId);

        DocumentTypeCodeType documentTypeCode = new DocumentTypeCodeType();
        documentTypeCode.setValue(MessageType.MESSAGE_BUNDLE.getType());
        documentReference.setDocumentTypeCode(documentTypeCode);
        documentResponse.setDocumentReference(documentReference);

        sdo.setDocumentResponse(documentResponse);

        return sdo;
    }

    protected boolean processResponse(SubmitApplicationResponseResponse response) {
        AcknowledgmentType ack = response.getAck();
        return ack.getAckIndicator().isValue();
    }
}
