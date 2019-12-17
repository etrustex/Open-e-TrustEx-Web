package eu.europa.ec.etrustex.webaccess.webservice.provider;

import ec.schema.xsd.ack_2.AcknowledgmentType;
import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import ec.schema.xsd.commonbasiccomponents_1.AckIndicatorType;
import ec.schema.xsd.documentbundle_1.DocumentBundleType;
import ec.services.wsdl.documentbundle_2.DocumentBundlePortType;
import ec.services.wsdl.documentbundle_2.FaultResponse;
import ec.services.wsdl.documentbundle_2.SubmitDocumentBundleRequest;
import ec.services.wsdl.documentbundle_2.SubmitDocumentBundleResponse;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.message.MessageType;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.utils.ConcurrentHelper;
import eu.europa.ec.etrustex.webaccess.webservice.provider.business.InboxNotificationBO;
import eu.europa.ec.etrustex.webaccess.webservice.provider.convert.EtxDocumentBundleConverter;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
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
import java.security.Principal;

@Component("documentBundleService")
@RolesAllowed(value = "NODE-SERVICE-ROLE")
@WebService(portName = "documentBundlePort",
        serviceName = "documentBundleService",
        targetNamespace = "ec:services:wsdl:DocumentBundle-2",
        wsdlLocation = "META-INF/wsdl/DocumentBundle-2.0.wsdl",
        endpointInterface = "ec.services.wsdl.documentbundle_2.DocumentBundlePortType")
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class DocumentBundleService extends SpringBeanAutowiringSupport implements DocumentBundlePortType {

    private Logger logger = Logger.getLogger(DocumentBundleService.class);

    @Resource
    private WebServiceContext context;

    @Autowired
    private InboxNotificationBO inboxNotificationBO;

    @Autowired
    private MailboxManager mailboxManager;

    @Autowired
    private ConcurrentHelper concurrentHelper;

    @Autowired
    private EtxDocumentBundleConverter etxDocumentBundleConverter;

    @Override
    public SubmitDocumentBundleResponse submitDocumentBundle(
            @WebParam(name = "SubmitDocumentBundleRequest",
                    targetNamespace = "ec:services:wsdl:DocumentBundle-2",
                    partName = "SubmitDocumentBundleRequest") SubmitDocumentBundleRequest submitDocumentBundleRequest,
            @WebParam(name = "Header", targetNamespace = "ec:services:wsdl:DocumentBundle-2", header = true,
                    mode = WebParam.Mode.INOUT, partName = "Header") Holder<HeaderType> header) throws FaultResponse {

        logger.debug("DocumentBundleService.submitDocumentBundle start");

        DocumentBundleType documentBundle = submitDocumentBundleRequest.getDocumentBundle();

        String bundleId = documentBundle.getID().getValue();
        logger.info("Receiving bundle '" + bundleId + "'");

        // block the processing of the bundle with id bundleId
        if (!concurrentHelper.addKey(bundleId)) {
            try {
                PartyType senderParty = documentBundle.getSenderParty();
                String remotePartyName = null;

                if (senderParty != null && senderParty.getEndpointID() != null) {
                    remotePartyName = senderParty.getEndpointID().getValue();
                } else {
                    logger.warn("Remote sender party for bundle '" + bundleId + "' is empty");
                }

                if (documentBundle.getReceiverParty().size() != 1) {
                    logger.warn("Message '" + bundleId + "' does not have exactly one receiver party!");
                }
                PartyType receiverParty = documentBundle.getReceiverParty().get(0);
                String localPartyName = receiverParty.getEndpointID().getValue();

                Message message = etxDocumentBundleConverter.convertMessage(documentBundle);
                Principal principal = context.getUserPrincipal();

                Message savedMessage = inboxNotificationBO.handleMessage(principal.getName(), message, localPartyName, remotePartyName);

                if (savedMessage != null && savedMessage.getId() != null) {
                    mailboxManager.consumeNodeMessage(localPartyName, remotePartyName, message.getBundleId(), MessageType.MESSAGE_BUNDLE);
                }
            } catch (Exception e) {
                logger.error("Unexpected error: " + e.getMessage(), e);
                throw new FaultResponse("Technical failure");
            } finally {
                // release the processing of the bundle with id bundleId
                concurrentHelper.removeKey(bundleId);
            }
        } else {
            logger.warn("Message '" + bundleId + "' is being processed!");
        }

        // the response
        AckIndicatorType indicator = new AckIndicatorType();
        indicator.setValue(true);

        AcknowledgmentType type = new AcknowledgmentType();
        type.setAckIndicator(indicator);

        SubmitDocumentBundleResponse response = new SubmitDocumentBundleResponse();
        response.setAck(type);

        logger.debug("DocumentBundleService.submitDocumentBundle completed");
        return response;
    }
}
