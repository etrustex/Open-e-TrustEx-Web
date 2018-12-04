package eu.europa.ec.etrustex.webaccess.web.view.business.common;

import eu.europa.ec.etrustex.webaccess.web.view.business.api.Configuration;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConfigurationImpl implements Configuration {

    protected Map<String, WebHandler> actionHandlerMap = new HashMap<>();
    protected List<String> genericViews = new ArrayList<>();
    protected Map<String, String> customViewLabels = new HashMap<>();
    private int messageListPageSize;

    public void setActionHandlerMap(Map<String, WebHandler> actionHandlerMap) {
        this.actionHandlerMap = actionHandlerMap;
    }

    public void setGenericViews(List<String> genericViews) {
        this.genericViews = genericViews;
    }

    public void setCustomViewLabels(Map<String, String> customViewLabels) {
        this.customViewLabels = customViewLabels;
    }

    @Override
    public WebHandler getWebHandler(WebAction action) {
        if (actionHandlerMap.containsKey(action.getName())) {
            return actionHandlerMap.get(action.getName());
        } else {
            return null;
        }
    }

    @Override
    public boolean isGenericViewEnabled(WebAction action){
        return genericViews.contains(action.getName());
    }

    @Override
    public String getCustomViewLabel(WebAction action){
        return customViewLabels.get(action.getName());
    }

    @Override
    public int getMessageListPageSize() {
        return messageListPageSize;
    }

    public void setMessageListPageSize(int messageListPageSize) {
        this.messageListPageSize = messageListPageSize;
    }
}
