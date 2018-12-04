package eu.europa.ec.etrustex.webaccess.webservice.message;

import ec.services.wsdl.retrieverequest_2.SubmitRetrieveRequestResponse;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;

public interface MessageService {

    SubmitRetrieveRequestResponse retrieveMessage(String nodeUserName, String nodePassword,
                                                  String messageUUID, String messageType, String remotePartyName, String localPartyNodeName,
                                                  String senderIdentifierParty) throws EtxException;
}
