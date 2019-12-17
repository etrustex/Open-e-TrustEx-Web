package eu.europa.ec.etrustex.webaccess.business.api;


import eu.europa.ec.etrustex.webaccess.model.Business;

import java.util.List;

public interface BusinessManager {
    Business getBusinessById(Long businessId);

    List<Business> getAllBusinesses();

    boolean isBusinessEmailEnforceEnabledFor(Business business);
}
