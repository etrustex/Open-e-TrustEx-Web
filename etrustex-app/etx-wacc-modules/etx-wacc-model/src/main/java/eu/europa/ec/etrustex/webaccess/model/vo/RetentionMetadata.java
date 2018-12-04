package eu.europa.ec.etrustex.webaccess.model.vo;

import java.util.Calendar;

public class RetentionMetadata {

    public static final RetentionMetadata INVALID_RETENTION_POLICY_SETTINGS_INSTANCE = new RetentionMetadata();

    private boolean retentionExpired;
    private boolean retentionWarning;
    private Calendar expiredOn;

    public boolean getRetentionExpired() {
        return retentionExpired;
    }

    public void setRetentionExpired(boolean retentionExpired) {
        this.retentionExpired = retentionExpired;
    }

    public boolean getRetentionWarning() {
        return retentionWarning;
    }

    public void setRetentionWarning(boolean retentionWarning) {
        this.retentionWarning = retentionWarning;
    }

    public Calendar getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(Calendar expiredOn) {
        this.expiredOn = expiredOn;
    }
}
