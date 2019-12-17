package eu.europa.ec.digit.etrustex.mobile.api;

import eu.europa.ec.digit.etrustex.mobile.model.ServerError;
import eu.europa.ec.digit.etrustex.mobile.model.User;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-29T09:50:38.249+02:00")

@Api(value = "user", description = "the user API")
public interface UserApi {

    @ApiOperation(value = "Get logged user information", notes = "", response = User.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = User.class),
        @ApiResponse(code = 500, message = "internal server error", response = User.class) })
    @RequestMapping(value = "/user",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<User> getUserByName() {
        // do some magic!
        return new ResponseEntity<User>(HttpStatus.OK);
    }

}
