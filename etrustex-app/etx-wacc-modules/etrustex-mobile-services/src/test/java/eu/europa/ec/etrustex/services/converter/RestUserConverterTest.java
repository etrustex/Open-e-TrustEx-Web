package eu.europa.ec.etrustex.services.converter;

import eu.europa.ec.etrustex.services.model.RestUser;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.User;
import org.junit.Test;

public class RestUserConverterTest extends AbstractTest {

    @Test
    public void test_convertToRestUser_nullUser_should_return_empty_RestUser() {

        //ACTUAL CALL
        RestUser restUser = RestUserConverter.getInstance().convertToRestUser(null);

        assertThat(restUser.getUserId(), is(""));
        assertThat(restUser.getFullName(), is(""));
    }


    @Test
    public void test_convertToRestUser_should_return_restUser() {

        User user = new User();
        user.setId(1L);
        user.setName("John Doo");

        // DO THE ACTUAL CALL
        RestUser restUser = RestUserConverter.getInstance().convertToRestUser(user);

        assertThat(restUser.getUserId(), is("1"));
        assertThat(restUser.getFullName(), is("John Doo"));
    }

}
