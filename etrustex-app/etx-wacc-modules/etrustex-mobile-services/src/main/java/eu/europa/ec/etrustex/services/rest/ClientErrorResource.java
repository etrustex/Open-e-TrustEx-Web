package eu.europa.ec.etrustex.services.rest;

import eu.europa.ec.etrustex.services.model.RestClientError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller where all the REST services regarding the client errors will be implemented.
 */

@RestController
@RequestMapping("clienterror")
public class ClientErrorResource {

    private static final Logger logger = LoggerFactory.getLogger(ClientErrorResource.class);

    /**
     * Report client side errors.
     *
     * @param currentUser Logged user.
     * @param body        Details of the client side error.
     * @return The HTTP status code will be as follows:
     * - 200: Successful operation.
     * - 500: Internal server error.
     */

    @PostMapping
    public ResponseEntity reportClientError(@AuthenticationPrincipal UserDetails currentUser, @RequestBody(required = true) RestClientError body) {
        logger.error("Client side error for user: " + currentUser.getUsername() + ", location: " + body.getLocation() + ", details: " + body.getDetails());

        return new ResponseEntity(HttpStatus.OK);
    }

}
