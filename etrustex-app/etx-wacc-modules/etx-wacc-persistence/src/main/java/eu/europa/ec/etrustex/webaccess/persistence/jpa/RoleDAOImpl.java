package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Role;
import eu.europa.ec.etrustex.webaccess.persistence.RoleDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class RoleDAOImpl extends AbstractDAOImpl<Role, Role.Type> implements RoleDAO {

    @Override
    public Role getRole(Role.Type type) {
        TypedQuery<Role> dataQuery = entityManager.createNamedQuery("findRoleByType", Role.class);
        dataQuery.setParameter("roleType", type.name());
        return dataQuery.getSingleResult();
    }
}
