package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Config;
import eu.europa.ec.etrustex.webaccess.persistence.ConfigurationDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ConfigurationDAOImpl extends AbstractDAOImpl<Config, Long> implements ConfigurationDAO {

    @Override
    @Transactional(readOnly = true)
    public List<Config> getConfiguration() {

        TypedQuery<Config> dataQuery = entityManager.createNamedQuery("findAllConfigs", Config.class);
        return dataQuery.getResultList();
    }

}
