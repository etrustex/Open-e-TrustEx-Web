package eu.europa.ec.etrustex.webaccess.web.model;

public class AdminFormBean {

	private Long partyId;
	private String email;
    private Boolean notificationsEnabled;
    private String statusNotificationEmail;
    private Boolean statusNotificationEnabled;
    private String businessLabel;
    private boolean checkDisabled;
    private boolean enforceNotifications;

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public String getStatusNotificationEmail() {
        return statusNotificationEmail;
    }

    public void setStatusNotificationEmail(String statusNotificationEmail) {
        this.statusNotificationEmail = statusNotificationEmail;
    }

    public Boolean getStatusNotificationEnabled() {
        return statusNotificationEnabled;
    }

    public void setStatusNotificationEnabled(Boolean statusNotificationEnabled) {
        this.statusNotificationEnabled = statusNotificationEnabled;
    }

    public String getBusinessLabel() {
        return businessLabel;
    }

    public void setBusinessLabel(String businessLabel) {
        this.businessLabel = businessLabel;
    }

    public boolean isCheckDisabled() {
        return checkDisabled;
    }

    public void setCheckDisabled(boolean checkDisabled) {
        this.checkDisabled = checkDisabled;
    }

    public boolean isEnforceNotifications() {
        return enforceNotifications;
    }

    public void setEnforceNotifications(boolean enforceNotifications) {
        this.enforceNotifications = enforceNotifications;
    }
}
