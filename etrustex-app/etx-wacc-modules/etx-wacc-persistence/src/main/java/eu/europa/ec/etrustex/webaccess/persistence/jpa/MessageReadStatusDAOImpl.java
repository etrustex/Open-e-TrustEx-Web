package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.MessageRead;
import eu.europa.ec.etrustex.webaccess.persistence.MessageReadStatusDAO;
import org.springframework.stereotype.Repository;

@Repository
public class MessageReadStatusDAOImpl extends AbstractDAOImpl<MessageRead, Long> implements MessageReadStatusDAO {

    @Override
	public void markMessageReadByUser(Long messageId, Long userId) {

		MessageRead messageRead = new MessageRead();
		messageRead.setMessageId(messageId);
		messageRead.setUserId(userId);
		entityManager.persist(messageRead);
	}
}
