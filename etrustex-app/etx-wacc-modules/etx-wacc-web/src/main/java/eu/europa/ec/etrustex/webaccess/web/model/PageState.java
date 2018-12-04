package eu.europa.ec.etrustex.webaccess.web.model;

import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PageState {

    public enum Param {
        VIEW("v", "custom"),
        SUBJECT("s", ""),
        SORT_DIRECTION("sd", "DESC"),
        PAGE("p", "1"),
        NOTIFY_SUCCESSFUL_OPERATION("nso", "false"),
        UNREAD_ONLY("u", "false"),
        BACK_ACTION_DO("bad", "inbox.do"),
        BACK_VIEW("bv", "custom"),
        MESSAGE_ID("mid", "-1"),
        PARTY_ID("pid", "-1"),
        STATUS("st", MessageListQueryParams.MessageStatus.ALL.name());

        private final String name;
        private final String defaultValue;

        private Param(String name, String defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }

        String getDefaultValue() {
            return defaultValue;
        }

        public String getName() {
            return name;
        }
    }

    protected Map<Param, String> params = new HashMap<>();

    public PageState() {
    }

    public void populate(Map requestParamsMap) {
        for (Param param : Param.values()) {
            if (requestParamsMap.containsKey(param.getName())) {
                String[] values = (String[]) requestParamsMap.get(param.getName());
                if (values.length > 1) {
                    throw new IllegalArgumentException("Not expected multiple values for parameter " + param);
                }
                params.put(param, values[0]);
            }
        }
    }

    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<Param, String> entry : params.entrySet()) {
            map.put(entry.getKey().getName(), entry.getValue());
        }
        return map;
    }

    protected String getValue(Param param) {
        if (params.containsKey(param)) {
            return params.get(param);
        }
        return param.getDefaultValue();
    }

    protected void setValue(Param param, String value) {
        params.put(param, value);
    }

    protected String buildUrl(Param... urlParams) {
        StringBuilder sb = new StringBuilder();
        for (Param urlParam : urlParams) {
            sb.append("&").append(urlParam.getName()).append("=").append(getValue(urlParam));
        }
        return sb.toString();
    }

    public String getResetListUrl() {
        return buildUrl(Param.VIEW, Param.PARTY_ID);
    }

    public String getListWoUnreadUrl() {
        return buildUrl(Param.VIEW, Param.SUBJECT, Param.SORT_DIRECTION, Param.PARTY_ID);
    }

    public String getListWoSortUrl() {
        return buildUrl(Param.VIEW, Param.SUBJECT, Param.UNREAD_ONLY, Param.PARTY_ID);
    }

    public String getListWoSortAndUnreadUrl() {
        return buildUrl(Param.VIEW, Param.SUBJECT, Param.PARTY_ID);
    }

    public String getListWoPageUrl() {
        return buildUrl(Param.VIEW, Param.SUBJECT, Param.SORT_DIRECTION, Param.UNREAD_ONLY, Param.PARTY_ID, Param.STATUS);
    }

    public String getBackToListUrl() {
        return buildUrl(Param.PAGE, Param.SUBJECT, Param.SORT_DIRECTION, Param.UNREAD_ONLY, Param.PARTY_ID);
    }

    public String getToMessageUrl() {
        return buildUrl(Param.BACK_ACTION_DO, Param.BACK_VIEW, Param.PAGE, Param.SUBJECT, Param.SORT_DIRECTION, Param.UNREAD_ONLY);
    }

    public String getCreateMessageUrl() {
        return buildUrl(Param.VIEW, Param.PARTY_ID);
    }

    public String getPartyUrl() {
        return buildUrl(Param.PARTY_ID);
    }

    public String getSubject() {
        return getValue(Param.SUBJECT).trim();
    }

    public void setSubject(String v) {
        setValue(Param.SUBJECT, v);
    }

    public String getSortDirection() {
        return getValue(Param.SORT_DIRECTION);
    }

    public String getOppositeSortDirection() {
        return getValue(Param.SORT_DIRECTION).equals("ASC") ? "DESC" : "ASC";
    }

    public String getPage() {
        return getValue(Param.PAGE);
    }

    public String getUnreadOnly() {
        return getValue(Param.UNREAD_ONLY);
    }

    public String getView() {
        return getValue(Param.VIEW);
    }

    public void setView(String v) {
        setValue(Param.VIEW, v);
    }

    public String getBackAction() {
        return getValue(Param.BACK_ACTION_DO);
    }

    public String getBackView() {
        return getValue(Param.BACK_VIEW);
    }

    public void setBackAction(String v) {
        setValue(Param.BACK_ACTION_DO, v);
    }

    public void setBackView(String v) {
        setValue(Param.BACK_VIEW, v);
    }

    public String getMessageId() {
        return getValue(Param.MESSAGE_ID);
    }

    public void setMessageId(Long v) {
        if (v != null) {
            setValue(Param.MESSAGE_ID, v.toString());
        }
    }

    public String getPartyId() {
        return getValue(Param.PARTY_ID);
    }

    public void setPartyId(Long v) {
        if (v != null) {
            setValue(Param.PARTY_ID, v.toString());
        }
    }

    public String getNotifySuccessfulOperation() {
        return getValue(Param.NOTIFY_SUCCESSFUL_OPERATION);
    }

    public void setNotifySuccessfulOperation(Boolean v) {
        if (v != null) {
            setValue(Param.NOTIFY_SUCCESSFUL_OPERATION, v.toString());
        }
    }

    public String getStatus() {
        return getValue(Param.STATUS);
    }

    public void setStatus(String st) {
        setValue(Param.STATUS, st);
    }
}
