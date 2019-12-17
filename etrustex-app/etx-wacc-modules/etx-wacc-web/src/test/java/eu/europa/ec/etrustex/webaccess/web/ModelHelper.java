package eu.europa.ec.etrustex.webaccess.web;

import eu.europa.ec.etrustex.webaccess.model.*;

import java.lang.reflect.Field;
import java.util.Set;

public class ModelHelper {

    public static UserRole buildUserRole(Long id, User user, Role role, Party party, Business business) throws Exception {
        UserRole userRole = new UserRole();
        userRole.setId(id);
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setParty(party);
        userRole.setBusiness(business);

        return userRole;
    }

    public static Role buildRole(String name, Role.Type roleType, Set<Privilege> privileges) throws Exception {
        Role role = new Role();
        setPrivateField(role, "name", name);
        setPrivateField(role, "type", roleType);
        setPrivateField(role, "privileges", privileges);

        return role;
    }

    public static Privilege buildPrivilege(String name, Privilege.Type privilegeType) throws Exception {
        Privilege privilege = new Privilege();
        setPrivateField(privilege, "name", name);
        setPrivateField(privilege, "type", privilegeType);

        return privilege;
    }

    private static void setPrivateField(Object obj, String fieldName, Object fieldValue) throws Exception {
        if (fieldValue == null) {
            return;
        }
        Field typeField = obj.getClass().getDeclaredField(fieldName);
        typeField.setAccessible(true);
        typeField.set(obj, fieldValue);
        typeField.setAccessible(false);
    }
}
