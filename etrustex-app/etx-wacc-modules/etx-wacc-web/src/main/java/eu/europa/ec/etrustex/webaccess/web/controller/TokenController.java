package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.rest.TokenRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/authenticate")
public class TokenController {

    /**
     * Validates token and verifies ECAS user id.
     *
     * @param token Token to be validated.
     * @param userId ECAS user id.
     * @return HTTP 200 code if the token was successfully validated, HTTP 401 code otherwise.
     */

    @Autowired
    protected TokenRestService tokenRestService;


    @RequestMapping(method = RequestMethod.GET, value = "/{token}/{userId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Description("Validate token")
    HttpStatus validateToken(@PathVariable String token, @PathVariable String userId) {
        return tokenRestService.validateToken(token, userId);
    }
}
