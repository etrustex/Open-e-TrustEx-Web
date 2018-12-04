package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.User;

/**
 * Data Access Object for a user data
 *
 * @author dzierma
 */
public interface UserDAO extends AbstractDAO<User, Long>{

	/**
	 * Search for a user on a given login
	 *
	 * @param login - requested user login
	 */
	public User getUser(String login);
}
