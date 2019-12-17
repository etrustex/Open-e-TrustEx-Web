/**
 *
 */
package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.User;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Calendar;

/**
 * @author apladap
 */
public class UserDAOTest extends DAOTest {

    private UserDAOImpl userDao;

    private Calendar currentDate = Calendar.getInstance();
    private Calendar expiredDate = Calendar.getInstance();

    @Override
    protected void onSetUp(EntityManager entityManager) {

        userDao = new UserDAOImpl();
        userDao.entityManager = entityManager;

        currentDate.set(2012, 9, 10); //10-SEP-2012
        expiredDate.set(2012, 7, 10); //10-JUL-2012

        insertUser(1L, "user1", "user1", currentDate.getTime(), 1L, true);

        insertParty(4L, "party@party4.eu", "Party4", "ref4", null, currentDate.getTime(), 1L, true, true);
        insertParty(5L, "party@party5.eu", "Party5", "ref5", null, currentDate.getTime(), 1L, true, true);
        insertParty(7L, "party@party7.eu", "Party7", "ref7", null, expiredDate.getTime(), 1L, true, true);
        insertParty(8L, "party@party8.eu", "Party8", "ref8", null, currentDate.getTime(), 1L, true, true);
        insertParty(6L, "party@party6.eu", "Party6", "ref6", null, currentDate.getTime(), 1L, true, true);

        insertUser(101L, "user101", "user101", currentDate.getTime(), 1L, true);
        insertUser(102L, "user102", "user102", currentDate.getTime(), 1L, true);
        insertUser(103L, "user102", "user102", currentDate.getTime(), 1L, true);
    }

    @Test
    public void testUpdateUser_updateTrue() {
        User testUser = new User();
        testUser.setId(101L);
        testUser.setLogin("user103");

        testUser = userDao.update(testUser);

        Assert.assertNotNull(testUser);
        Assert.assertEquals(testUser.getLogin(), "user103");
    }

    @Test
    public void testUpdateUser_saveTrue() {
        User user = userDao.getUser("user1");

        User testUser = new User();
        testUser.setLogin("user104");

        testUser.setCreatedOn(currentDate.getTime());
        testUser.setActiveState(true);
        testUser.setCreatedBy(user);

        userDao.save(testUser);

        Assert.assertNotNull(testUser);
        Assert.assertEquals(testUser.getLogin(), "user104");
    }
}
