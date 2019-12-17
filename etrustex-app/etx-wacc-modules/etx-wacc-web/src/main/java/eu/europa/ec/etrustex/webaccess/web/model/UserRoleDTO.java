package eu.europa.ec.etrustex.webaccess.web.model;

public class UserRoleDTO {
    private final String name;
    private final String email;
    private final Boolean notificationsEnabled;
    private String statusNotificationEmail;
    private Boolean statusNotificationEnabled;

    public UserRoleDTO(String name, String email, Boolean notificationsEnabled, String statusNotificationEmail, Boolean statusNotificationEnabled) {
        this.name = name;
        this.email = email;
        this.notificationsEnabled = notificationsEnabled;
        this.statusNotificationEmail = statusNotificationEmail;
        this.statusNotificationEnabled = statusNotificationEnabled;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;
    }

    public String getStatusNotificationEmail() {
        return statusNotificationEmail;
    }

    public Boolean getStatusNotificationEnabled() {
        return statusNotificationEnabled;
    }
}
