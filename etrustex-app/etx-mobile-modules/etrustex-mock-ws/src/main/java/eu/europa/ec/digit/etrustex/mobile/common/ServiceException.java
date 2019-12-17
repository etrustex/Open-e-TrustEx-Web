package eu.europa.ec.digit.etrustex.mobile.common;

import java.util.Objects;
import java.util.UUID;

/**
 * Exception that could be thrown when processing user request.
 */
public class ServiceException extends RuntimeException {
    private final String id = UUID.randomUUID().toString();
    private final String type;

    public ServiceException(String message, String type) {
        this(message, type, null);
    }

    public ServiceException(String message, String type, Throwable cause) {
        super(message, cause);
        this.type = (null != type) ? type : "GENERIC";
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
