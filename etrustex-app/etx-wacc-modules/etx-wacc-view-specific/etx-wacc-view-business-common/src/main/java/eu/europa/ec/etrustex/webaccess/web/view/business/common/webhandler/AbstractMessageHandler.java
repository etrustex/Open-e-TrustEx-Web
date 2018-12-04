package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.IcaDetailsService;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.util.ExternalAuthenticationService;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.*;
import eu.europa.ec.etrustex.webaccess.business.util.IcaHelper;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Map;

public abstract class AbstractMessageHandler implements WebHandler {

    @Autowired
    protected UserSessionContext userSessionContext;

    @Autowired
    protected AttachmentHandler attachmentHandler;

    @Autowired
    protected WebHandlerHelper webHandlerHelper;

    @Autowired
    private ExternalAuthenticationService externalAuthenticationService;

    @Autowired
    protected IcaManager icaManager;

    @Autowired
    protected IcaHelper icaHelper;

    @Autowired
    protected IcaDetailsService icaDetailsService;

    private static final Logger logger = Logger.getLogger(AbstractMessageHandler.class);

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Override
    public Map<String, Object> buildBusinessModel(QueryParams queryParams) throws Exception {
        if (queryParams instanceof MessageQueryParams) {
            Map<String, Object> model = buildBusinessModel((MessageQueryParams) queryParams);
            model.put("token", externalAuthenticationService.registerExternalToken());

            return model;
        } else {
            throw new IllegalStateException("Unexpected type of QueryParams: " + queryParams);
        }
    }

    protected abstract Map<String, Object> buildBusinessModel(MessageQueryParams queryParams) throws Exception;

    protected boolean hasLinkedIcas(Party party) {
        return webHandlerHelper.hasLinkedIcas(party);
    }

    protected IcaDetailsVO adaptMessageIcaDetails(NodeIcaDetails nodeIcaDetails) {
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

}
