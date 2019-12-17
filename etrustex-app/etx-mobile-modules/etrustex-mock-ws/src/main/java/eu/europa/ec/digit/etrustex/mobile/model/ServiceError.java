package eu.europa.ec.digit.etrustex.mobile.model;

import eu.europa.ec.digit.etrustex.mobile.common.ServiceException;
import io.swagger.annotations.ApiModelProperty;

public class ServiceError {
    private final ServiceException cause;

    public ServiceError(ServiceException cause) {
        this.cause = cause;
    }

    public String getType() {
        return cause.getType();
    }

    public String getId() {
        return cause.getId();
    }

    public String getMessage() {
        return cause.getMessage();
    }
}
