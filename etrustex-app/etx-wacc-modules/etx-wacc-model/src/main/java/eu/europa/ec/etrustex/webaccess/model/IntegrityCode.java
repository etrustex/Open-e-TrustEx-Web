package eu.europa.ec.etrustex.webaccess.model;

import org.apache.log4j.Logger;

public enum IntegrityCode {

    MODERATE(0, false),

    CRITICAL(1, true),

    STRATEGIC(2, true);

    private static final Logger logger = Logger.getLogger(IntegrityCode.class.getName());

    private int code;
    private boolean signatureRequired;

    IntegrityCode(int code, boolean signatureRequired) {
        this.code = code;
        this.signatureRequired = signatureRequired;
    }

    public int getCode() {
        return code;
    }

    public static IntegrityCode forCode(String value) {
        IntegrityCode integrityCode = null;
        try {
            int code = Integer.valueOf(value);
            integrityCode = forCode(code);
        } catch (NumberFormatException e) {
            logger.debug("Loading of integrity code failed", e);
        }

        return integrityCode;
    }

    public static IntegrityCode forCode(Integer value) {

        IntegrityCode integrityCode = null;
        if (value != null) {
            for (IntegrityCode intCode : IntegrityCode.values()) {
                if (value == intCode.getCode()) {
                    integrityCode = intCode;
                    break;
                }
            }
        }
        return integrityCode;
    }

    public boolean getIsSignatureRequired() {
        return signatureRequired;
    }
}
