package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.model.Business;
import eu.europa.ec.etrustex.webaccess.model.BusinessConfigProperty;
import eu.europa.ec.etrustex.webaccess.persistence.BusinessDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusinessManagerImpl implements BusinessManager {

    @Autowired
    private BusinessDAO businessDAO;

    @Override
    public Business getBusinessById(Long businessId) {
        return businessDAO.findById(businessId);
    }

    @Override
    public List<Business> getAllBusinesses() {
        return businessDAO.getAllBusinesses();
    }

    @Override
    public boolean isBusinessEmailEnforceEnabledFor(Business business) {
        business = getBusinessById(business.getId());
        return business == null ? false : Boolean.valueOf(business.getBusinessConfigValues().get(BusinessConfigProperty.BUS_ENFORCE_EMAIL_NOTIF_ENABLED));
    }
}
