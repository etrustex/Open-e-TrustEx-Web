package eu.europa.ec.etrustex.webaccess.webservice.status;

import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseRequest;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseResponse;

public interface ApplicationResponseClientService {

    SubmitApplicationResponseResponse sendMessageStatus(String nodeUserName, String nodePassword,
                                                               SubmitApplicationResponseRequest request,
                                                               String senderPartyName,
                                                               String receiverPartyName);
}
