package eu.europa.ec.etrustex.webaccess.webservice.provider;

import ec.schema.xsd.ack_2.AcknowledgmentType;
import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import ec.schema.xsd.commonbasiccomponents_1.AckIndicatorType;
import ec.services.wsdl.applicationresponse_2.ApplicationResponsePortType;
import ec.services.wsdl.applicationresponse_2.FaultResponse;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseRequest;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseResponse;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.api.NotifyService;
import eu.europa.ec.etrustex.webaccess.business.message.MessageType;
import eu.europa.ec.etrustex.webaccess.business.message.NodeStatusResponseCode;
import eu.europa.ec.etrustex.webaccess.model.MessageStatus;
import oasis.names.specification.ubl.schema.xsd.applicationresponse_2.ApplicationResponseType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentResponseType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ResponseType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentTypeCodeType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceContext;

@Component("applicationResponseService")
@RolesAllowed(value = "NODE-SERVICE-ROLE")
@WebService(portName = "ApplicationResponsePort",
        serviceName = "ApplicationResponseService",
        targetNamespace = "ec:services:wsdl:ApplicationResponse-2",
        wsdlLocation = "META-INF/wsdl/ApplicationResponse-2.0.wsdl",
        endpointInterface = "ec.services.wsdl.applicationresponse_2.ApplicationResponsePortType")
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class ApplicationResponseService extends SpringBeanAutowiringSupport implements ApplicationResponsePortType {

    private Logger logger = Logger.getLogger(ApplicationResponseService.class);

    @Resource
    private WebServiceContext context;

    @Autowired
    private MailboxManager mailboxManager;

    @Autowired
    private NotifyService notifyService;

    @Override
    public SubmitApplicationResponseResponse submitApplicationResponse(
            @WebParam(name = "SubmitApplicationResponseRequest",
                    targetNamespace = "ec:services:wsdl:ApplicationResponse-2",
                    partName = "SubmitApplicationResponseRequest") SubmitApplicationResponseRequest submitApplicationResponseRequest,
            @WebParam(name = "Header",
                    targetNamespace = "ec:services:wsdl:ApplicationResponse-2",
                    header = true, mode = WebParam.Mode.INOUT, partName = "Header") Holder<HeaderType> header) throws FaultResponse {

        try {
            ApplicationResponseType applicationResponse = submitApplicationResponseRequest.getApplicationResponse();
            DocumentResponseType documentResponse = applicationResponse.getDocumentResponse();
            ResponseType responseType = documentResponse.getResponse();
            DocumentTypeCodeType documentTypeCode = documentResponse.getDocumentReference().getDocumentTypeCode();
            String statusCode = responseType.getResponseCode() == null ? null : responseType.getResponseCode().getValue();

            StringBuilder builder = new StringBuilder();
            builder.append("\nReceive node application response:");
            builder.append("\n    ID: ").append(applicationResponse.getID().getValue());

            builder.append("\n    Response Reference ID: ").append(responseType.getReferenceID().getValue());
            builder.append("\n    Response Reference code: ").append(documentResponse.getDocumentReference().getID().getValue());

            builder.append("\n    Response Reference type code: ").append(documentTypeCode.getValue());
            builder.append("\n    Response Status code: ").append(statusCode);
            logger.info(builder.toString());

            String localPartyName = header.value.getBusinessHeader().getReceiver().get(0).getIdentifier().getValue();
            String remotePartyName = header.value.getBusinessHeader().getSender().get(0).getIdentifier().getValue();
            String statusUuid = applicationResponse.getID().getValue();
            String referenceUuid = documentResponse.getDocumentReference().getID().getValue();
            NodeStatusResponseCode nodeStatusResponseCode = NodeStatusResponseCode.fromCode(statusCode);
            MessageStatus.Status status = getStatusFrom(nodeStatusResponseCode);
            String referenceType = documentTypeCode.getValue();

            MessageStatus messageStatus = mailboxManager.createMessageStatus(localPartyName, remotePartyName, statusUuid, referenceUuid,
                    referenceType, status, statusCode, context.getUserPrincipal().getName());
            if (messageStatus != null) {
                notifyService.notify(messageStatus);
                mailboxManager.consumeNodeMessage(localPartyName, remotePartyName, statusUuid, MessageType.MESSAGE_STATUS);
            }
        } catch (Exception e) {
            logger.error("Unexpected error: " + e.getMessage(), e);
            throw new FaultResponse("Technical failure");
        }
        // the response
        AckIndicatorType indicator = new AckIndicatorType();
        indicator.setValue(true);

        AcknowledgmentType type = new AcknowledgmentType();
        type.setAckIndicator(indicator);

        SubmitApplicationResponseResponse response = new SubmitApplicationResponseResponse();
        response.setAck(type);
        return response;
    }

    private static MessageStatus.Status getStatusFrom(NodeStatusResponseCode nodeStatusResponseCode) {
        MessageStatus.Status status;
        if (nodeStatusResponseCode != null) {
            switch (nodeStatusResponseCode) {
                case BDL1:
                    status = MessageStatus.Status.AVAILABLE;
                    break;
                case BDL7:
                    status = MessageStatus.Status.READ;
                    break;
                case BDL4:
                case BDL5:
                case BDL6:
                case STS4:
                case STS5:
                case STS6:
                    status = MessageStatus.Status.FAILED;
                    break;
                default:
                    status = MessageStatus.Status.UNKNOWN;
            }
        } else {
            status = MessageStatus.Status.UNKNOWN;
        }
        return status;
    }
}
