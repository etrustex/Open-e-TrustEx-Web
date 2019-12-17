package eu.europa.ec.etrustex.services.security;

import eu.europa.ec.etrustex.webaccess.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserContext implements Serializable, UserDetails, Principal {

    private List<UserRole> roleList;

    private static final long serialVersionUID = 1L;
    private final String username;
    private String firstName;
    private String lastName;
    private String login;

    private Long perId;
    private String domain;
    private String email;
    private String mainLanguage;
    private String administrativePosition;
    private String institution;


    public UserContext(String username) {
        this.username = username;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<UserRole> roles = getRoleList();
        Collection<GrantedAuthority> grantedAuthorities;

        grantedAuthorities = new ArrayList<>();

        if(roles != null) {
            for (UserRole role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
            }
        }

        if (grantedAuthorities.isEmpty()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("anonymousUser"));
        }
        return grantedAuthorities;
    }

    public List<UserRole> getRoleList() {
        return this.roleList;
    }

    public void setRoleList(List<UserRole> roleList) {
        this.roleList = roleList;
    }

    public String getFullName() {
        return (getFirstName() + " " + getLastName());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }


    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public void setMainLanguage(String mainLanguage) {
        this.mainLanguage = mainLanguage;
    }

    public String getAdministrativePosition() {
        return administrativePosition;
    }

    public void setAdministrativePosition(String administrativePosition) {
        this.administrativePosition = administrativePosition;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }
}
