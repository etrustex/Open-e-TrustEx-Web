package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Notification;
import eu.europa.ec.etrustex.webaccess.persistence.NotificationDAO;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationDAOImpl extends AbstractDAOImpl<Notification, Long> implements NotificationDAO {
}
