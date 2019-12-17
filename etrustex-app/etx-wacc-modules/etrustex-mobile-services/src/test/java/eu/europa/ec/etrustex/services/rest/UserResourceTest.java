package eu.europa.ec.etrustex.services.rest;


import eu.europa.ec.etrustex.services.business.UserService;
import eu.europa.ec.etrustex.services.model.RestUser;
import eu.europa.ec.etrustex.services.security.UserContext;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.User;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;


public class UserResourceTest extends AbstractTest {

    @InjectMocks
    private UserResource userResource;

    @Mock
    private UserService userService;

    @Mock
    private UserDetails currentUser;


    @Test
    public void getCurrentUser() {

        UserDetails currentUser = new UserContext("userId");

        User user = new User();
        user.setName("John Doo");
        user.setId(1L);

        when(userService.getUserDetails(currentUser.getUsername())).thenReturn(user);

        //ACTUAL CALL
        ResponseEntity<RestUser> result = userResource.getCurrentUser(currentUser);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody().getUserId(), is("1"));
        assertThat(result.getBody().getFullName(), is("John Doo"));

        verify(userService).getUserDetails("userId");
        verifyNoMoreInteractions(userService);

    }

    @Test
    public void getCurrentUser_no_user_found() {

        UserDetails currentUser = new UserContext("userId");

        when(userService.getUserDetails(currentUser.getUsername())).thenReturn(null);

        //ACTUAL CALL
        ResponseEntity<RestUser> result = userResource.getCurrentUser(currentUser);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody().getUserId(), is(""));
        assertThat(result.getBody().getFullName(), is(""));

        verify(userService).getUserDetails("userId");
        verifyNoMoreInteractions(userService);

    }
}
