/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Metadata;
import eu.europa.ec.etrustex.webaccess.persistence.MetadataDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * @author apladap
 *
 */
@Repository
public class MetadataDAOImpl extends AbstractDAOImpl<Metadata, Long> implements MetadataDAO {
	
	private final static String QUERY_METADATA_BY_MESSAGEID = "SELECT m FROM Metadata m WHERE m.messageId=:messageId";

	@Override
	public Metadata retrievePayload(long messageId) {
		TypedQuery<Metadata> dataQuery = entityManager.createQuery(QUERY_METADATA_BY_MESSAGEID, Metadata.class);
		dataQuery.setParameter("messageId", messageId);

        try {
            return dataQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
