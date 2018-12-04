package eu.europa.ec.etrustex.webaccess.business.security;

import eu.europa.ec.etrustex.webaccess.business.administration.CacheService;
import eu.europa.ec.etrustex.webaccess.business.api.IcaDetailsService;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.facade.administration.CacheResetListener;
import eu.europa.ec.etrustex.webaccess.model.ConfidentialityCode;
import eu.europa.ec.etrustex.webaccess.model.IntegrityCode;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.model.converters.ExtendedCertificateDataConverter;
import eu.europa.ec.etrustex.webaccess.model.vo.ExtendedCertificateData;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaKey;
import eu.europa.ec.etrustex.webaccess.model.vo.PartyIcaVO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.PartyDAO;
import eu.europa.ec.etrustex.webaccess.webservice.ica.IcaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@DependsOn("securityProvider")
public class IcaDetailsServiceImpl implements IcaDetailsService, CacheResetListener {

    private static final Logger logger = Logger.getLogger(IcaDetailsServiceImpl.class.getName());

    @Autowired
    protected MessageDAO messageDAO;

    @Autowired
    protected PartyDAO partyDAO;

    @Autowired
    protected IcaService icaService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private PartyManager partyManager;

    @Autowired
    private IcaManager icaManager;

    private final AtomicBoolean loadRequested = new AtomicBoolean(false);

    protected ExecutorService executor;
    protected CompletionService<List<NodeIcaDetails>> completionService;

    @PostConstruct
    public void init() {
        executor = Executors.newFixedThreadPool(IcaService.MAX_CONCURRENT_TRANSMISSIONS);
        completionService = new ExecutorCompletionService<>(executor);

        cacheService.registerCacheResetListener(this);
    }

    @Override
    public void onCacheReset() {
        icaService.fillIcaWrapperPool();
    }

    @Override
    public Map<NodeIcaKey, NodeIcaDetails> loadAllIcaDetails() {
        long statTime = System.currentTimeMillis();
        if (loadRequested.compareAndSet(false, true)) {
            logger.info("load all ICA details requested");
            try {
                loadLocalPartiesIca();
                return getIcasByActivePartiesDetails();
            } finally {
                loadRequested.set(false);

                logger.info("load all ICA details completed in " + (System.currentTimeMillis() - statTime));
            }
        } else {
            logger.debug("load all ICA details already requested");
            return Collections.emptyMap();
        }
    }

    @Override
    public NodeIcaDetails getIcaDetails(Party webParty, String icaParty) {
        long startTime = System.currentTimeMillis();

        PartyIca partyIca = icaManager.getIcaBySenderReceiver(webParty.getNodeName(), icaParty);
        NodeIcaDetails nodeIcaDetails = getNodeIcaDetailsBySenderReceiver(partyIca, true);

        if (nodeIcaDetails == null) {
            logger.info("NodeIcaDetails miss for nodeIcaKey {localParty = '" + webParty.getNodeName() + "', remoteParty = '" + icaParty + "'}; Start requesting it!");
            nodeIcaDetails = loadNodeIcaDetail(webParty, icaParty);
        }

        logger.info("NodeIcaDetails for ICA Key {localParty = '" + webParty.getNodeName() + "', remoteParty = '" + icaParty + "'} retrieved in " + (System.currentTimeMillis() - startTime) + " milliseconds");
        return nodeIcaDetails;
    }

    @Override
    public NodeIcaDetails loadIcaDetails(String senderPartyName, String icaParty) {
        long startTime = System.currentTimeMillis();

        Party senderParty = partyDAO.getWebManagedPartyByNodeName(senderPartyName);
        loadNodeIcaDetail(senderParty, icaParty);

        PartyIca partyIca = icaManager.getIcaBySenderReceiver(senderPartyName, icaParty);
        NodeIcaDetails nodeIcaDetails = getNodeIcaDetailsBySenderReceiver(partyIca, false);

        logger.info("NodeIcaDetails for sender " + senderPartyName + " and receiver " + icaParty +
                " retrieved in " + (System.currentTimeMillis() - startTime) + " milliseconds");
        return nodeIcaDetails;
    }

    private NodeIcaDetails loadNodeIcaDetail(Party senderParty, String icaParty) {
        NodeIcaDetails nodeIcaDetails = null;
        NodeIcaKey nodeIcaKey = new NodeIcaKey(senderParty.getNodeName(), icaParty);

        logger.info("Load ICA Details for ICA Key: " + nodeIcaKey);

        List<NodeIcaDetails> nodeIcaDetailsList = icaService.retrieveICADetails(senderParty.getNodeUserName(), senderParty.getNodeUserPass(),
                senderParty.getNodeName(), senderParty.getNodeName(), icaParty);

        if (!nodeIcaDetailsList.isEmpty()) {
            nodeIcaDetails = nodeIcaDetailsList.get(0);
        }

        updateIcaSenderReceiver(nodeIcaDetails, senderParty.getNodeName(), icaParty);

        return nodeIcaDetails;
    }

    private void updateIcaSenderReceiver(NodeIcaDetails nodeIcaDetails, String localPartyRequest, String remotePartyRequest) {
        long startTime = System.currentTimeMillis();
        logger.info("Updating ICA in data base for sender: " + localPartyRequest + ", receiver: " + remotePartyRequest);

        if (nodeIcaDetails != null) {
            insertOrUpdateIca(nodeIcaDetails);
        } else {
            disableIcaSenderReceiver(localPartyRequest, remotePartyRequest);
        }

        logger.info("Finished updating ICA in data base for sender: " + localPartyRequest + ", receiver: " + remotePartyRequest + " in " + (System.currentTimeMillis() - startTime) + " ms");
    }

    private void disableIcaSenderReceiver(String localPartyRequest, String remotePartyRequest) {
        PartyIca partyIcaRequest = icaManager.getActiveIcaBySenderReceiver(localPartyRequest, remotePartyRequest);

        //Reloaded ica active but deactivated in the node
        if (partyIcaRequest != null) {
            partyIcaRequest.setActiveState(false);
            icaManager.saveOrUpdate(partyIcaRequest);

            logger.info("Deactivated ICA[id=" + partyIcaRequest.getId() + "] for sender: " + localPartyRequest + ", receiver: " + remotePartyRequest);
        }
    }

    private void loadLocalPartiesIca() {
        long startTime = System.currentTimeMillis();
        logger.info("Start loading the ICAs for local Parties");

        Map<NodeIcaKey, NodeIcaDetails> partyNodeIcaDetailsMap = new LinkedHashMap<>();
        List<NodeIcaDetails> partyNodeIcaDetails = new ArrayList<>();
        List<Party> parties = partyDAO.getAllParties();
        int createdTasks = 0;
        for (Party party : parties) {
            completionService.submit(prepareIcaRequestTask(party));
            createdTasks++;
        }

        try {
            int completedTasks = 0;
            while (completedTasks < createdTasks) {
                List<NodeIcaDetails> nodeIcaDetailsList = completionService.take().get();

                for (NodeIcaDetails nodeIcaDetails : nodeIcaDetailsList) {
                    logger.info("ICA for sender:" + nodeIcaDetails.getLocalParty() + ", receiver: " + nodeIcaDetails.getRemoteParty());
                    NodeIcaKey nodeIcaKey = new NodeIcaKey(nodeIcaDetails.getLocalParty(), nodeIcaDetails.getRemoteParty());
                    if (nodeIcaDetails.getIntegrityCode() != null && nodeIcaDetails.getConfidentialityCode() != null) {
                        partyNodeIcaDetailsMap.put(nodeIcaKey, nodeIcaDetails);
                    }

                    partyNodeIcaDetails.add(nodeIcaDetails);
                }

                completedTasks++;
                logger.info("Completed #" + completedTasks + " out of " + createdTasks);
            }

            updateAllIcas(partyNodeIcaDetails);
        } catch (InterruptedException e) {
            logger.warn("Worker interrupted", e);
        } catch (ExecutionException e) {
            logger.warn("Execution exception ", e);
        }
        logger.info("Finished loading the ICAs for local Parties in " + (System.currentTimeMillis() - startTime) + " ms");
    }

    private void updateAllIcas(List<NodeIcaDetails> partyNodeIcaDetails) {
        long startTime = System.currentTimeMillis();
        logger.info("Updating ICAs in data base");

        for (NodeIcaDetails nodeIcaDetails : partyNodeIcaDetails) {
            insertOrUpdateIca(nodeIcaDetails);
        }

        disableAllIcas(partyNodeIcaDetails);

        logger.info("Finished updating ICAs in data base in " + (System.currentTimeMillis() - startTime) + " ms");
    }

    private void disableAllIcas(List<NodeIcaDetails> partyNodeIcaDetails) {
        List<PartyIcaVO> icas = icaManager.getAllIcasSenderReceiver();

        for (PartyIcaVO icaVO : icas) {
            boolean icaExist = false;
            String sender = icaVO.getLocalParty();
            String receiver = icaVO.getRemoteParty();
            Long id = icaVO.getId();

            for (NodeIcaDetails nodeIcaDetails : partyNodeIcaDetails) {
                if (sender != null && receiver != null && sender.equals(nodeIcaDetails.getLocalParty()) && receiver.equals(nodeIcaDetails.getRemoteParty())) {
                    icaExist = true;
                    break;
                }
            }

            if (!icaExist) {
                PartyIca disabledPartyIca = icaManager.findById(id);
                disabledPartyIca.setActiveState(false);
                icaManager.saveOrUpdate(disabledPartyIca);

                logger.info("Deactivated ICA[id=" + id + "] for sender: " + sender + ", receiver: " + receiver);
            }
        }
    }

    private void insertOrUpdateIca(NodeIcaDetails nodeIcaDetails) {
        PartyIca originalPartyIca = icaManager.getIcaBySenderReceiver(nodeIcaDetails.getLocalParty(), nodeIcaDetails.getRemoteParty());

        Integer integrityCode = nodeIcaDetails.getIntegrityCode().getCode();
        Integer confidentialityCode = nodeIcaDetails.getConfidentialityCode().getCode();
        ExtendedCertificateData extendedCertificateData = nodeIcaDetails.getExtendedCertificateData();

        if (originalPartyIca != null) { //Update ICA
            if (!comparePartyNodeIca(originalPartyIca, nodeIcaDetails)) {
                originalPartyIca.setIntegrityCode(integrityCode);
                originalPartyIca.setConfidentialityCode(confidentialityCode);
                originalPartyIca.setCertificate(ExtendedCertificateDataConverter.getInstance().convertToDatabaseColumn(extendedCertificateData));
                originalPartyIca.setLoadedOn(new Date());
                originalPartyIca.setActiveState(true);

                icaManager.saveOrUpdate(originalPartyIca);

                logger.info("Updated ICA for sender: " + nodeIcaDetails.getLocalParty() + ", receiver: " + nodeIcaDetails.getRemoteParty());
            }
        } else { //New ICA
            Party localParty = partyManager.getWebManagedPartyByNodeName(nodeIcaDetails.getLocalParty());
            Party remoteParty = partyManager.getRemotePartyByNodeNameBusId(nodeIcaDetails.getRemoteParty(), localParty.getBusiness().getId());

            if (remoteParty == null) {
                remoteParty = new Party();
                remoteParty.setDisplayName(nodeIcaDetails.getRemoteParty());
                remoteParty.setNodeName(nodeIcaDetails.getRemoteParty());
                remoteParty.setWebManaged(Boolean.FALSE);
                remoteParty.setBusiness(localParty.getBusiness());
                partyManager.saveOrUpdate(remoteParty);
            }

            PartyIca partyIca = new PartyIca();
            partyIca.setIntegrityCode(integrityCode);
            partyIca.setConfidentialityCode(confidentialityCode);
            partyIca.setCertificate(ExtendedCertificateDataConverter.getInstance().convertToDatabaseColumn(extendedCertificateData));
            partyIca.setLoadedOn(new Date());
            partyIca.setParty(localParty);
            partyIca.setRemoteParty(remoteParty);

            icaManager.saveOrUpdate(partyIca);

            logger.info("Added ICA for sender: " + nodeIcaDetails.getLocalParty() + ", receiver: " + nodeIcaDetails.getRemoteParty());
        }
    }

    private boolean comparePartyNodeIca(PartyIca partyIca, NodeIcaDetails nodeIcaDetails) {
        Integer partyIcaIntegrityCode = partyIca.getIntegrityCode();
        Integer partyIcaConfidentialityCode = partyIca.getConfidentialityCode();

        boolean sameIntegrity = Objects.equals(partyIcaIntegrityCode, nodeIcaDetails.getIntegrityCode().getCode());
        boolean sameConfidentiality = Objects.equals(partyIcaConfidentialityCode, nodeIcaDetails.getConfidentialityCode().getCode());

        String encodedCertificate = ExtendedCertificateDataConverter.getInstance().convertToDatabaseColumn(nodeIcaDetails.getExtendedCertificateData());
        boolean sameCertificate = Objects.equals(partyIca.getCertificate(), encodedCertificate);

        boolean sameActiveState = Objects.equals(partyIca.getActiveState(), nodeIcaDetails.isActiveState());

        return sameIntegrity && sameConfidentiality && sameCertificate && sameActiveState;
    }

    private Callable<List<NodeIcaDetails>> prepareIcaRequestTask(final Party senderParty) {
        return new Callable<List<NodeIcaDetails>>() {
            @Override
            public List<NodeIcaDetails> call() throws Exception {
                return icaService.retrieveICADetails(senderParty.getNodeUserName(), senderParty.getNodeUserPass(), senderParty.getNodeName(), senderParty.getNodeName(), null);
            }
        };
    }

    @Override
    public Map<NodeIcaKey, NodeIcaDetails> getIcasByActivePartiesDetails() {
        List<PartyIca> icas = icaManager.getIcasByActiveParties();
        HashMap<NodeIcaKey, NodeIcaDetails> nodeIcasDetailsMap = new LinkedHashMap();

        for (PartyIca ica : icas) {
            NodeIcaKey nodeIcaKey = new NodeIcaKey(ica.getParty().getNodeName(), ica.getRemoteParty().getNodeName());
            NodeIcaDetails nodeIcaDetails = getNodeIcaDetailsBySenderReceiver(ica, false);

            nodeIcasDetailsMap.put(nodeIcaKey, nodeIcaDetails);
        }

        return nodeIcasDetailsMap;
    }

    @Override
    public NodeIcaDetails getNodeIcaDetailsBySenderReceiver(PartyIca ica, boolean extendedCertificate) {
        NodeIcaDetails nodeIcaDetails = null;

        if (ica != null) {
            ExtendedCertificateData extendedCertificateData;
            if (extendedCertificate) {
                extendedCertificateData = ExtendedCertificateDataConverter.getInstance().convertToEntityAttribute(ica.getCertificate());
            } else {
                extendedCertificateData = ExtendedCertificateDataConverter.getInstance().convertToLightEntityAttribute(ica.getCertificate());
            }

            String localParty = ica.getParty().getNodeName();
            String remoteParty = ica.getRemoteParty().getNodeName();
            ConfidentialityCode confidentialityCode = ConfidentialityCode.forCode(ica.getConfidentialityCode());
            IntegrityCode integrityCode = IntegrityCode.forCode(ica.getIntegrityCode());
            Date updatedOn = ica.getUpdatedOn() != null ? ica.getUpdatedOn() : ica.getCreatedOn();
            boolean isActiveState = ica.getActiveState();

            nodeIcaDetails = new NodeIcaDetails(updatedOn, localParty, remoteParty, confidentialityCode, integrityCode, extendedCertificateData, isActiveState);
        }

        return nodeIcaDetails;
    }

}
