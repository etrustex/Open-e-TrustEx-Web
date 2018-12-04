package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.persistence.UserDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractDAOImpl<User, Long> implements UserDAO {

    public User getUser(String login) {

        TypedQuery<User> dataQuery = entityManager.createNamedQuery("findUserByLogin", User.class);
        dataQuery.setParameter("login", login);
		List<User> resultList = dataQuery.getResultList();

		if (resultList == null || resultList.size() == 0) {
			return null;
		} else if (resultList.size() > 1) {
			throw new IllegalStateException("More than one records found for the requested user");
		}

		return resultList.get(0);
	}
}
