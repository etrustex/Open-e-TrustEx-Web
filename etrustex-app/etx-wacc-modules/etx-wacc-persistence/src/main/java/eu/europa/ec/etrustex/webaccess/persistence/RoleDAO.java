package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.Role;

public interface RoleDAO extends AbstractDAO<Role, Role.Type> {

    Role getRole(Role.Type type);
}