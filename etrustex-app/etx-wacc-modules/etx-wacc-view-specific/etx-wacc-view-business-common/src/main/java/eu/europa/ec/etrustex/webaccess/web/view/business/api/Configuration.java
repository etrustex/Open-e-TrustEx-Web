package eu.europa.ec.etrustex.webaccess.web.view.business.api;

public interface Configuration {
    WebHandler getWebHandler(WebAction action);

    boolean isGenericViewEnabled(WebAction action);

    String getCustomViewLabel(WebAction action);

    int getMessageListPageSize();
}
