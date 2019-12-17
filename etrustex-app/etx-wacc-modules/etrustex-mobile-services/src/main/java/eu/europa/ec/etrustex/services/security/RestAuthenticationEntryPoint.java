package eu.europa.ec.etrustex.services.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        // TODO Handle runtime exceptions (non authentication)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not a registered user. Please contact the support team at DIGIT-CIPA-SUPPORT@ec.europa.eu in order to request access rights.");
    }

}
