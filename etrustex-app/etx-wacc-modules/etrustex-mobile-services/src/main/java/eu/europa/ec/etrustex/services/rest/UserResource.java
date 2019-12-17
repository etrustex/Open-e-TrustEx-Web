package eu.europa.ec.etrustex.services.rest;

import eu.europa.ec.etrustex.services.business.UserService;
import eu.europa.ec.etrustex.services.converter.RestUserConverter;
import eu.europa.ec.etrustex.services.model.RestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller where all the REST services regarding the users will be implemented.
 */

@RestController
public class UserResource {

    @Autowired
    private UserService userService;


    /**
     * Gets userDetails for logged user.
     *
     * @param currentUser Logged user.
     * @return RestUser. HTTP 200 for a successful operation, HTTP 500 otherwise.
     */

    @GetMapping("/user")
    public ResponseEntity<RestUser> getCurrentUser(@AuthenticationPrincipal UserDetails currentUser) {

        eu.europa.ec.etrustex.webaccess.model.User user = userService.getUserDetails(currentUser.getUsername());
        RestUser restUser = RestUserConverter.getInstance().convertToRestUser(user);

        return new ResponseEntity<>(restUser, HttpStatus.OK);
    }
}
