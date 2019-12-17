package eu.europa.ec.etrustex.webaccess.webservice.status;

import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import ec.services.wsdl.applicationresponse_2.ApplicationResponsePortType;
import ec.services.wsdl.applicationresponse_2.FaultResponse;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseRequest;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseResponse;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import eu.europa.ec.etrustex.webaccess.webservice.NodeObjectBuilder;
import eu.europa.ec.etrustex.webaccess.webservice.NodeWebServiceProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Holder;

@Service
public class ApplicationResponseClientServiceImpl implements ApplicationResponseClientService {
    private static final Logger logger = Logger.getLogger(ApplicationResponseClientServiceImpl.class);

    @Autowired
    private NodeWebServiceProvider nodeWebServiceProvider;

    @Override
    public SubmitApplicationResponseResponse sendMessageStatus(String nodeUserName, String nodePassword,
                                                               SubmitApplicationResponseRequest request,
                                                               String senderPartyName, String receiverPartyName) {
        String statusUuid = request.getApplicationResponse().getID().getValue();
        logger.info("sendStatus start for statusUUID: " + statusUuid);
        SubmitApplicationResponseResponse response = null;
        try {
            // Fill header & request
            Holder<HeaderType> authorisationHeaderHolder = NodeObjectBuilder.buildHeaderWithStatusScope(senderPartyName, receiverPartyName);

            ApplicationResponsePortType port = nodeWebServiceProvider.getApplicationResponsePort();
            nodeWebServiceProvider.setupConnectionCredentials(port, nodeUserName, nodePassword);

            response = port.submitApplicationResponse(request, authorisationHeaderHolder);
            logger.info("sendStatus done for statusUUID: " + statusUuid);
            return response;
        } catch (FaultResponse faultResponse) {
            String faultResponseCode = faultResponse.getFaultInfo() != null ? faultResponse.getFaultInfo().getResponseCode().getValue() : "";
            String description = faultResponse.getMessage();
            logger.warn("send status for statusUUID " + statusUuid + " returns the fault response code: " + faultResponseCode + "; description: " + description);
            throw new EtxException("fault response invoking sendStatus service", faultResponse);
        } catch (Exception e) {
            throw new EtxException("technical exception invoking sendStatus service for statusUUID:" + statusUuid, e);
        }
    }
}
