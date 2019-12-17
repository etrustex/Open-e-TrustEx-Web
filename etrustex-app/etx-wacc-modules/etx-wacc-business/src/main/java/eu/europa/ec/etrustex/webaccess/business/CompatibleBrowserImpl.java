package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.webaccess.business.api.CompatibleBrowserService;
import eu.europa.ec.etrustex.webaccess.model.CompatibleBrowser;
import eu.europa.ec.etrustex.webaccess.persistence.CompatibleBrowserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompatibleBrowserImpl implements CompatibleBrowserService {

    @Autowired
    private CompatibleBrowserDAO compatibleBrowserDAO;

    @Override
    public List<CompatibleBrowser> getCompatibleBrowsers() {
        return compatibleBrowserDAO.getAllCompatibleBrowsers();
    }
}
