package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.MessageStatus;

public interface MessageStatusDAO extends AbstractDAO<MessageStatus, Long> {
    MessageStatus saveOrUpdateMessageStatus(MessageStatus messageStatus);

    /**
     * Search for particular message status
     *
     * @param statusUuid - requested message identifier
     * @return - requested message status
     */
    public MessageStatus findByStatusUuid(String statusUuid);
}
