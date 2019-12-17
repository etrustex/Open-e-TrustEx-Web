package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.Config;

import java.util.List;

/**
 * Data access object for system configuration data
 *
 * @author dzierma
 */
public interface ConfigurationDAO extends AbstractDAO<Config, Long>{

    /**
     * Provides configuration data list
     *
     * @return configuration data list
     */
    public List<Config> getConfiguration();


}
