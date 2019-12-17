package eu.europa.ec.etrustex.services.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RestAuthenticationException extends UsernameNotFoundException {
    public RestAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
}
