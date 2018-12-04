package eu.europa.ec.etrustex.webaccess.model.vo;

import java.io.Serializable;
import java.util.Date;

public class CertificateValidity implements Serializable {
    private boolean expired;
    private boolean notYetValid;
    private Date endDate;
    private Date startDate;

    public CertificateValidity() {
        this.expired = false;
        this.notYetValid = false;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isNotYetValid() {
        return notYetValid;
    }

    public void setNotYetValid(boolean notYetValid) {
        this.notYetValid = notYetValid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
