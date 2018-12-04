package eu.europa.ec.etrustex.webaccess.web.view.business.api;

import java.util.HashMap;
import java.util.Map;

//todo split to PayloadBuilderProvider and WebHandlerProvider?
public class ConfigurationProvider {

    private Map<String, Configuration> configurations = new HashMap<>();

    public void setConfigurations(Map<String, Configuration> configurations) {
        this.configurations = configurations;
    }

    public Configuration getConfiguration(String viewName) {
        if (configurations.containsKey(viewName)) {
            return configurations.get(viewName);
        }
        throw new IllegalArgumentException("View not supported: " + viewName);
    }

    public boolean isWebHandlerAvailable(String viewName, WebAction action) {
        WebHandler webHandler = null;
        if (configurations.containsKey(viewName)) {
            Configuration configuration = configurations.get(viewName);
            webHandler = configuration.getWebHandler(action);
        }
        return webHandler != null;
    }

    public boolean isGenericViewEnabled(String viewName, WebAction action) {
        if (configurations.containsKey(viewName)) {
            Configuration configuration = configurations.get(viewName);
            return configuration.isGenericViewEnabled(action);
        } else {
            return true;
        }
    }
}