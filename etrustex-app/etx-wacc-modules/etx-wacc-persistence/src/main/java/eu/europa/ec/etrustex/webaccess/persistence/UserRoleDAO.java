package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.*;

import java.util.List;
import java.util.Map;

public interface UserRoleDAO extends AbstractDAO<UserRole, Long> {


    List<UserRole> getUserRoles(User user);

    List<UserRole> getUserRolesWithPrivilege(String login, Business business, Privilege.Type privilege);

    Map<UserRole, BusinessUserConfig> getUserRoles(Party party, Role.Type type);

    Map<UserRole, BusinessUserConfig> getUserRoles(Business business, Role.Type type);

    Map<UserRole, User> getUserRoles(Role.Type type);

    Map<Party, String> loadBusinessUserConfigNameByAccessiblePartyForRoles(List<UserRole> partyLevelRoles);

    Map<Party, String> loadBusinessUserConfigNameByAccessibleBusinessForRoles(List<UserRole> businessLevelRoles);

    UserRole getUserRole(Long userRoleId);
}
