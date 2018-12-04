package eu.europa.ec.etrustex.webaccess.web.view.business.api;

public enum WebAction {

    INBOX("inbox"),
    OUTBOX("outbox"),
    DRAFTS("drafts"),

    MESSAGE_VIEW_RECEIVED("messageViewReceived"),
    MESSAGE_VIEW_RECEIVED_WS("messageViewReceivedWS"),
    MESSAGE_VIEW_SENT("messageViewSent"),
    MESSAGE_EDIT("messageEdit"),
    MESSAGE_EDIT_WS("messageEditWS"),
    MESSAGE_CREATE("messageCreate"),
    MESSAGE_CREATE_WS("messageCreateWS");

    private final String name;

    WebAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDo() {
        return name + ".do";
    }
}
