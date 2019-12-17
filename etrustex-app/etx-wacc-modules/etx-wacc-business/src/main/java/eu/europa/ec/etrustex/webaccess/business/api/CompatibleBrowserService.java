package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.model.CompatibleBrowser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompatibleBrowserService {

    List<CompatibleBrowser> getCompatibleBrowsers();

}
