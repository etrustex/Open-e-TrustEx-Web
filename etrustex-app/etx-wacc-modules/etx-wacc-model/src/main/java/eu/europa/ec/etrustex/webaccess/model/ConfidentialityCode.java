package eu.europa.ec.etrustex.webaccess.model;


import org.apache.log4j.Logger;

public enum ConfidentialityCode {

    PUBLIC(0, false),

    LIMITED_BASIC(1, false),

    LIMITED_HIGH(2, true),

    RESTREINT_UE(3, true),

    CONFIDENTIAL_UE(4, true),

    SECRET_UE(5, true),

    TOP_SECRET_UE(6, true);

    private static final Logger logger = Logger.getLogger(ConfidentialityCode.class);

    private final boolean encryptionRequired;
    private final int code;

    ConfidentialityCode(int code, boolean encryptionRequired) {
        this.encryptionRequired = encryptionRequired;
        this.code = code;
    }

    public boolean isEncryptionRequired() {
        return encryptionRequired;
    }

    public int getCode() {
        return code;
    }

    public static ConfidentialityCode forCode(String value) {
        ConfidentialityCode confidentialityCode = null;
        try {
            int code = Integer.valueOf(value);
            confidentialityCode = forCode(code);
        } catch (NumberFormatException e) {
            logger.debug("Loading of confidentiality code failed", e);
        }
        //if we are unable to get the ConfidentialityCode by code, return null
        return confidentialityCode;
    }

    public static ConfidentialityCode forCode(Integer value) {
        ConfidentialityCode confidentialityCode = null;
        if (value != null) {
            for (ConfidentialityCode confCode : ConfidentialityCode.values()) {
                if (value == confCode.getCode()) {
                    confidentialityCode = confCode;
                    break;
                }
            }
        }
        return confidentialityCode;
    }
}
