package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.CompatibleBrowser;

import java.util.List;

public interface CompatibleBrowserDAO extends AbstractDAO<CompatibleBrowser, Long> {

    List<CompatibleBrowser> getAllCompatibleBrowsers();
}
