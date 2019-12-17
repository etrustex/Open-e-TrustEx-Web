package eu.europa.ec.etrustex.services.converter;

import eu.europa.ec.etrustex.services.model.RestUser;
import eu.europa.ec.etrustex.webaccess.model.User;

public class RestUserConverter {

    private static final RestUserConverter INSTANCE = new RestUserConverter();

    /**
     * Gets singleton instance.
     *
     * @return Singleton instance.
     */
    public static RestUserConverter getInstance() {
        return INSTANCE;
    }

    private RestUserConverter() {
    }

    /**
     * Converts a User to a REST user entity
     *
     * @param user User entity
     * @return REST user entity
     */
    public RestUser convertToRestUser(User user) {
        RestUser restUser = new RestUser();

        if (user != null) {
            restUser.setUserId(String.valueOf(user.getId()));
            restUser.setFullName(String.valueOf(user.getName()));
        }

        return restUser;
    }

}
