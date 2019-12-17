package eu.europa.ec.etrustex.webaccess.business.message;

public enum MessageType {
    MESSAGE_BUNDLE("BDL"),
    MESSAGE_STATUS("301");

    private String type;

    MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
