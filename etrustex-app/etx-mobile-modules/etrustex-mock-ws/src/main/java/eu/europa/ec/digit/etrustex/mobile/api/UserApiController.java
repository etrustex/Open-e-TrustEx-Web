package eu.europa.ec.digit.etrustex.mobile.api;

import eu.europa.ec.digit.etrustex.mobile.model.User;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-22T16:16:31.972+02:00")

@RestController
@RequestMapping("/mobile/services")
public class UserApiController implements UserApi {

    @Autowired
    private ObjectFactory<User> loggedInUser;

    @GetMapping("/user")
    public ResponseEntity<User> getUserByName() {
        return ResponseEntity.ok(getUser());
    }

    private User getUser() {
        User user = this.loggedInUser.getObject();
        if (null == user) {
            throw new IllegalStateException("No auth user found in request!");
        }
        return user;
    }


}
