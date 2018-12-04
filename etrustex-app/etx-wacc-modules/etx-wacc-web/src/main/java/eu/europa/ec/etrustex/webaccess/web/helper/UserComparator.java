package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.webaccess.model.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {
        if (user1.getName() == null && user2.getName() == null) {
            return 0;
        }
        return user2.getName().toLowerCase().compareTo(user1.getName().toLowerCase());
    }
}
