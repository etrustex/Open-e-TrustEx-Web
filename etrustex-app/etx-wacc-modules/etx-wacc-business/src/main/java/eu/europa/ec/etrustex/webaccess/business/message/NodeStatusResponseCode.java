package eu.europa.ec.etrustex.webaccess.business.message;

public enum NodeStatusResponseCode {
    /**
     * Available
     */
    BDL1("BDL:1"),
    /**
     * Hard business rule violated
     */
    BDL4("BDL:4"),
    /**
     * Parent document ID does not exist
     */
    BDL5("BDL:5"),
    /**
     * Missing Attachment binary data
     */
    BDL6("BDL:6"),
    /**
     * Bundle has been read
     */
    BDL7("BDL:7"),
    /**
     * Hard business rule violated
     */
    STS4("301:4"),
    /**
     * Parent document ID does not exist
     */
    STS5("301:5"),
    /**
     * Parent document is not in the expected state
     */
    STS6("301:6");

    private final String code;

    NodeStatusResponseCode(String code) {
        this.code = code;
    }

    public static NodeStatusResponseCode fromCode(String code) {
        for (NodeStatusResponseCode responseCode : values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
