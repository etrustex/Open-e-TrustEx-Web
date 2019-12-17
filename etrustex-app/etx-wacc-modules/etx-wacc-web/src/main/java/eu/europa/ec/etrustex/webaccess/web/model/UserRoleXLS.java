package eu.europa.ec.etrustex.webaccess.web.model;

import java.util.Objects;

public class UserRoleXLS {

    private String partyName;
    private String userName;
    private String roleType;
    private String ECASId;
    private String email;
    private String notificationsEnabled;

    public UserRoleXLS() {
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getECASId() {
        return ECASId;
    }

    public void setECASId(String ECASId) {
        this.ECASId = ECASId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(String notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleXLS that = (UserRoleXLS) o;
        return Objects.equals(partyName, that.partyName) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(roleType, that.roleType) &&
                Objects.equals(ECASId, that.ECASId) &&
                Objects.equals(email, that.email) &&
                Objects.equals(notificationsEnabled, that.notificationsEnabled);
    }

    @Override
    public int hashCode() {

        return Objects.hash(partyName, userName, roleType, ECASId, email, notificationsEnabled);
    }
}
