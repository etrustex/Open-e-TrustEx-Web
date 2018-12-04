package eu.europa.ec.etrustex.webaccess.webservice.message;

import ec.schema.xsd.commonaggregatecomponents_2.DocumentReferenceRequestType;
import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import ec.schema.xsd.commonbasiccomponents_1.RetrieveIndicatorType;
import ec.schema.xsd.retrieverequest_2.RetrieveRequestType;
import ec.services.wsdl.retrieverequest_2.RetrieveRequestPortType;
import ec.services.wsdl.retrieverequest_2.SubmitRetrieveRequestRequest;
import ec.services.wsdl.retrieverequest_2.SubmitRetrieveRequestResponse;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import eu.europa.ec.etrustex.webaccess.webservice.NodeObjectBuilder;
import eu.europa.ec.etrustex.webaccess.webservice.NodeWebServiceProvider;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Holder;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

    @Autowired
    private NodeWebServiceProvider nodeWebServiceProvider;

    public SubmitRetrieveRequestResponse retrieveMessage(String nodeUserName, String nodePassword,
                                                         String messageUuid, String messageType, String remotePartyName, String localPartyNodeName,
                                                         String senderIdentifierParty) throws EtxException {

        logger.info("Start reading node message with messageUuid id " + messageUuid + " and messageType: " + messageType);
        Holder<HeaderType> authorizationHolder = NodeObjectBuilder.buildHeader(senderIdentifierParty, null);

        // Prepare WS request
        SubmitRetrieveRequestRequest request = new SubmitRetrieveRequestRequest();
        request.setRetrieveRequest(convertMessage(messageUuid, messageType, remotePartyName, localPartyNodeName));

        SubmitRetrieveRequestResponse response = null;
        try {
            RetrieveRequestPortType port = nodeWebServiceProvider.buildRetrieveRequestPort();
            nodeWebServiceProvider.setupConnectionCredentials(port, nodeUserName, nodePassword);

            response = port.submitRetrieveRequest(request, authorizationHolder);
        } catch (Exception e) {
            throw new EtxException(e.getMessage(), e);
        }
        logger.info("Completed receiving bundle for message with messageUuid id " + messageUuid + " and messageType: " + messageType);
        return response;
    }

    private RetrieveRequestType convertMessage(String messageUuid, String messageType, String remotePartyName, String localPartyNodeName) {

        IDType idType = new IDType();
        idType.setValue(messageUuid);

        DocumentTypeCodeType documentTypeCode = new DocumentTypeCodeType();
        documentTypeCode.setValue(messageType);

        DocumentReferenceRequestType documentReference = new DocumentReferenceRequestType();
        documentReference.setID(idType);
        documentReference.setDocumentTypeCode(documentTypeCode);

        RetrieveRequestType request = new RetrieveRequestType();
        request.setSenderParty(NodeObjectBuilder.buildParty(remotePartyName));
        request.setReceiverParty(NodeObjectBuilder.buildParty(localPartyNodeName));
        request.setDocumentReferenceRequest(documentReference);

        RetrieveIndicatorType indicator = new RetrieveIndicatorType();
        indicator.setValue(true);
        request.setRetrieveIndicator(indicator);
        return request;
    }

}
