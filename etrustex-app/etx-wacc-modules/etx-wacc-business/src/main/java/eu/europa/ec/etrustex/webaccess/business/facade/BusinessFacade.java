package eu.europa.ec.etrustex.webaccess.business.facade;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.vo.SignatureVO;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;

import java.util.List;

public interface BusinessFacade {
    String SENDING_BUNDLE_ERROR = "SendMessageBundle failed";
    Message saveOrUpdateMessage(Message message, List<Long> uploadedFiles, Long idSelectedPayloadFile, String payloadXML, SignatureVO signature, User currentUser) throws EtxException;
    void disableMessage(Message message) throws EtxException;
}
