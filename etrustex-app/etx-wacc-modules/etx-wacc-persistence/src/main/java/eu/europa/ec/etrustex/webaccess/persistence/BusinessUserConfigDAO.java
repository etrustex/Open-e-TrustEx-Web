package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.*;

import java.util.List;

public interface BusinessUserConfigDAO extends AbstractDAO<BusinessUserConfig, Long> {

    BusinessUserConfig getBusinessUserConfig(User user, Business business);

    List<BusinessUserConfig> getBusinessUserConfigs(Party party, Privilege.Type type);

    List<BusinessUserConfig> getBusinessUserConfigs(Business business, Privilege.Type type);

    List<BusinessUserConfig> getSystemScopeBusinessUserConfigs(Business business, Privilege.Type type);
}
