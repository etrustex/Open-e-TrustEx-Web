package eu.europa.ec.etrustex.services.rest;

import eu.europa.ec.etrustex.services.model.RestClientError;
import eu.europa.ec.etrustex.services.security.UserContext;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public class ClientErrorTest extends AbstractTest {

    @InjectMocks
    private ClientErrorResource clientErrorResource;

    @Test
    public void test_reportClientError_should_log_error() {
        UserDetails currentUser = new UserContext("userId");
        RestClientError clientError = new RestClientError();
        clientError.setDetails("Error details");
        clientError.setLocation("Error location");

        //ACTUAL CALL
        ResponseEntity result = clientErrorResource.reportClientError(currentUser, clientError);

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }

}
