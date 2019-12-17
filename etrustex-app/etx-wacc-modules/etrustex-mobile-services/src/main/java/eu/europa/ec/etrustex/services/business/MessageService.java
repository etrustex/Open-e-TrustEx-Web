package eu.europa.ec.etrustex.services.business;

import eu.europa.ec.etrustex.services.model.RestMessages;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;


/**
 * Service layer for messages.
 */

public interface MessageService {

    RestMessages getMessages(Message.MessageState type, Party party, String startfrom, String resultsize, String status, String subject, String sortby);

}
