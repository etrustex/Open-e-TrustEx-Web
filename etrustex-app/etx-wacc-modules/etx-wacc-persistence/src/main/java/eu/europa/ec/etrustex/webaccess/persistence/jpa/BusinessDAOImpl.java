package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Business;
import eu.europa.ec.etrustex.webaccess.persistence.BusinessDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BusinessDAOImpl extends AbstractDAOImpl<Business, Long> implements BusinessDAO {

    @Override
    public List<Business> getAllBusinesses() {
        TypedQuery<Business> dataQuery = entityManager.createNamedQuery("findAllBusinesses", Business.class);
        return dataQuery.getResultList();
    }
}
