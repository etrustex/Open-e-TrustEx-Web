package eu.europa.ec.etrustex.webaccess.web.model;

import eu.europa.ec.etrustex.webaccess.model.Role;

import java.util.List;

public class UserRoleFormBean {

    private Long userRoleId;
    private String name;
    private String login;
    private String email;
    private Boolean notificationsEnabled;
    private Boolean statusNotificationEnabled;
    private String statusNotificationEmail;
    private Long partyId;
    private Long businessId;
    private List<Role.Type> roleTypes;
    private Role.Type roleType;
    private boolean deletable;

    public UserRoleFormBean() {
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getName() {
        return (name != null ? name.trim() : name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return (login != null ? login.trim() : login);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return (email != null ? email.trim() : email);
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

    public Boolean getStatusNotificationEnabled() {
        return statusNotificationEnabled;
    }

    public void setStatusNotificationEnabled(Boolean statusNotificationEnabled) {
        this.statusNotificationEnabled = statusNotificationEnabled;
    }

    public String getStatusNotificationEmail() {
        return statusNotificationEmail;
    }

    public void setStatusNotificationEmail(String statusNotificationEmail) {
        this.statusNotificationEmail = statusNotificationEmail;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public List<Role.Type> getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(List<Role.Type> roleTypes) {
        this.roleTypes = roleTypes;
    }

    public Role.Type getRoleType() {
        return roleType;
    }

    public void setRoleType(Role.Type roleType) {
        this.roleType = roleType;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }
}
