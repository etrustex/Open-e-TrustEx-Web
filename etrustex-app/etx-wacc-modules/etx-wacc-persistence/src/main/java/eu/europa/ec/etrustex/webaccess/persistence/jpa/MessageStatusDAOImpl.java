package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.MessageStatus;
import eu.europa.ec.etrustex.webaccess.persistence.MessageStatusDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class MessageStatusDAOImpl extends AbstractDAOImpl<MessageStatus, Long> implements MessageStatusDAO {
    private static final Logger logger = Logger.getLogger(MessageStatusDAOImpl.class);

    @Override
    public MessageStatus saveOrUpdateMessageStatus(MessageStatus messageStatus) {

        // message already exists
        if (messageStatus.getId() != null) {
            messageStatus = update(messageStatus);
        } else {
            save(messageStatus);
        }

        return messageStatus;
    }

    @Override
    public MessageStatus findByStatusUuid(String statusUuid) {
        TypedQuery<MessageStatus> dataQuery = entityManager.createNamedQuery("findByStatusUuid", MessageStatus.class);
        dataQuery.setParameter("statusUuid", statusUuid);

        MessageStatus messageStatus;
        try {
            messageStatus = dataQuery.getSingleResult();
        } catch (NoResultException e) {
            messageStatus = null;
        }
        return messageStatus;
    }
}
