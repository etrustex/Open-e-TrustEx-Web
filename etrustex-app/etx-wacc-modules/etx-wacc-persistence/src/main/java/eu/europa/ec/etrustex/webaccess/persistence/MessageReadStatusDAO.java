package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.MessageRead;

public interface MessageReadStatusDAO extends AbstractDAO<MessageRead, Long>{

    //todo: [VM] move this method and its content to the service layer
	public void markMessageReadByUser(Long messageId, Long userId);
}