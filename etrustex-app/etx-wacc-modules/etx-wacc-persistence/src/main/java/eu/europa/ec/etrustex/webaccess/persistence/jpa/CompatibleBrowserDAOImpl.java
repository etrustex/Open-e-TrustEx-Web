package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.CompatibleBrowser;
import eu.europa.ec.etrustex.webaccess.persistence.CompatibleBrowserDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CompatibleBrowserDAOImpl extends AbstractDAOImpl<CompatibleBrowser, Long> implements CompatibleBrowserDAO {

    @Override
    public List<CompatibleBrowser> getAllCompatibleBrowsers() {
        TypedQuery<CompatibleBrowser> dataQuery = entityManager.createNamedQuery("findAllCompatibleBrowsers", CompatibleBrowser.class);
        return dataQuery.getResultList();
    }
}
