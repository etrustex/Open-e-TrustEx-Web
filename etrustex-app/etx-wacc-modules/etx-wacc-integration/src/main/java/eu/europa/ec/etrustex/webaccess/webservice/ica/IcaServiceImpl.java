package eu.europa.ec.etrustex.webaccess.webservice.ica;

import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import ec.schema.xsd.commonaggregatecomponents_2.InterchangeAgreementType;
import ec.schema.xsd.retrieveinterchangeagreementsrequest_2.RetrieveInterchangeAgreementsRequestType;
import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.RetrieveInterchangeAgreementsRequestPortType;
import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.SubmitRetrieveInterchangeAgreementsRequestRequest;
import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.SubmitRetrieveInterchangeAgreementsRequestResponse;
import eu.europa.ec.etrustex.webaccess.model.vo.ExtendedCertificateData;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import eu.europa.ec.etrustex.webaccess.webservice.NodeObjectBuilder;
import eu.europa.ec.etrustex.webaccess.webservice.NodeWebServiceProvider;
import eu.europa.ec.etrustex.webaccess.webservice.converter.NodeExtendedCertificateDataConverter;
import eu.europa.ec.etrustex.webaccess.webservice.converter.NodeInterchangeAgreementTypeConverter;
import eu.europa.ec.etrustex.webaccess.webservice.model.RawNodeIcaDetails;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@DependsOn("nodeConfigurationService")
public class IcaServiceImpl implements IcaService {

    private static final Logger logger = Logger.getLogger(IcaServiceImpl.class.getName());

    @Autowired
    private NodeWebServiceProvider nodeWebServiceProvider;

    public static final long POOL_MAX_WAIT_MILLIS = 2 * 60 * 1000;
    protected ObjectPool<RetrieveInterchangeAgreementsRequestPortType> icaWrapperPool;

    @PostConstruct
    public void init() {
        EtxSecurityProvider.init();

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMinIdle(MAX_CONCURRENT_TRANSMISSIONS);
        config.setMaxTotal(MAX_CONCURRENT_TRANSMISSIONS);
        config.setMaxWaitMillis(POOL_MAX_WAIT_MILLIS);
        icaWrapperPool = new GenericObjectPool<>(nodeWebServiceProvider.getIcaPortFactory(), config);

        fillIcaWrapperPool();
    }

    public void fillIcaWrapperPool() {
        try {
            icaWrapperPool.clear();
        } catch (Exception e) {
            logger.warn("Unable to clear polled ICAWrapper instances");
        }
        //pre-fill the pool of objects
        for (int i = 0; i < MAX_CONCURRENT_TRANSMISSIONS; i++) {
            try {
                icaWrapperPool.addObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<NodeIcaDetails> retrieveICADetails(String nodeUserName, String nodePassword, String loggedInUserParty, String senderParty, String receiverParty) {
        logger.info("Start retrieving ICA for loggedInUserParty: " + loggedInUserParty
                + " senderParty: " + senderParty
                + " receiverParty: " + receiverParty
        );

        long startTime = System.currentTimeMillis();
        List<NodeIcaDetails> nodeIcaDetailsList = new ArrayList<>();

        try {
            List<InterchangeAgreementType> interchangeAgreements = retrieveInterchangeAgreement(nodeUserName, nodePassword, loggedInUserParty, senderParty, receiverParty);

            if (interchangeAgreements != null) {
                nodeIcaDetailsList = retrieveNodeIcaDetails(interchangeAgreements, senderParty, receiverParty);
            } else {
                logger.warn("Couldn't match any ICA for senderParty: " + senderParty + ", receiverParty: " + receiverParty);
            }

        } catch (Exception e) {
            logger.warn("Retrieving of ICA details failed", e);
        }

        logger.info("Finish retrieving ICA for loggedInUserParty: " + loggedInUserParty
                + " senderParty: " + senderParty
                + " receiverParty: " + receiverParty
                + " nodeIcaDetails: " + nodeIcaDetailsList
                + " completed in " + (System.currentTimeMillis() - startTime)
        );

        return nodeIcaDetailsList;
    }

    protected List<InterchangeAgreementType> retrieveInterchangeAgreement(String nodeUserName, String nodePassword, String loggedInUserParty, String sender, String receiver) throws Exception {
        RetrieveInterchangeAgreementsRequestType requestType = new RetrieveInterchangeAgreementsRequestType();
        if (receiver != null) {
            requestType.setReceiverParty(NodeObjectBuilder.buildParty(receiver));
        }
        requestType.setSenderParty(NodeObjectBuilder.buildParty(sender));

        SubmitRetrieveInterchangeAgreementsRequestRequest request = new SubmitRetrieveInterchangeAgreementsRequestRequest();
        request.setRetrieveInterchangeAgreementsRequest(requestType);

        Holder<HeaderType> header = NodeObjectBuilder.buildHeader(loggedInUserParty, null);

        RetrieveInterchangeAgreementsRequestPortType icaPortType = null;
        SubmitRetrieveInterchangeAgreementsRequestResponse response = null;
        try {
            icaPortType = icaWrapperPool.borrowObject();
            nodeWebServiceProvider.setupConnectionCredentials(icaPortType, nodeUserName, nodePassword);
            response = icaPortType.submitRetrieveInterchangeAgreementsRequest(request, header);
            return response.getRetrieveInterchangeAgreementsResponse().getInterchangeAgreement();
        } catch (Exception e) {
            logger.warn("Initialization of RetrieveInterchangeAgreementsPort failed", e);
        } finally {
            if (icaPortType != null) {
                try {
                    icaWrapperPool.returnObject(icaPortType);
                } catch (Exception e) {
                    logger.warn("error occurred while returning object to pool: " + e.getMessage(), e);
                }
            }
        }
        return null;
    }

    private List<NodeIcaDetails> retrieveNodeIcaDetails(List<InterchangeAgreementType> interchangeAgreements, String senderReqParty, String receiverReqParty) {
        List<NodeIcaDetails> nodeIcaDetailsList = new ArrayList<>();

        for (InterchangeAgreementType interchangeAgreementType : interchangeAgreements) {
            RawNodeIcaDetails rawNodeIcaDetails = NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(interchangeAgreementType);

            if (rawNodeIcaDetails.getConfidentialityCode() != null && rawNodeIcaDetails.getIntegrityCode() != null) {

                String senderParty = interchangeAgreementType.getSenderParty().getEndpointID().getValue();
                String receiverParty = interchangeAgreementType.getReceiverParty().getEndpointID().getValue();

                if (!senderParty.equals(senderReqParty) || (receiverReqParty != null && !receiverReqParty.equals(receiverParty))) {
                    continue;
                }

                ExtendedCertificateData extendedCertificateData = null;

                if (rawNodeIcaDetails.getConfidentialityCode().isEncryptionRequired()) {
                    if (rawNodeIcaDetails.getEncodedCertificate() == null) {
                        logger.warn("Encription required but certificate is null for sender: " + senderParty + ", receiver: " + receiverParty);
                        continue;
                    } else {
                        extendedCertificateData = NodeExtendedCertificateDataConverter.toExtendedCertificateData(rawNodeIcaDetails.getEncodedCertificate(), rawNodeIcaDetails.getX509SubjectName());
                    }
                }

                NodeIcaDetails nodeIcaDetails = new NodeIcaDetails(new Date(), senderParty, receiverParty, rawNodeIcaDetails.getConfidentialityCode(),
                        rawNodeIcaDetails.getIntegrityCode(), extendedCertificateData, true);
                nodeIcaDetailsList.add(nodeIcaDetails);
            }
        }

        return nodeIcaDetailsList;
    }

}
