package eu.europa.ec.etrustex.webaccess.rest;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RestServiceExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger logger = Logger.getLogger(RestServiceExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        logger.error("Exception occurred while invoking the rest service", exception);
        return Response.serverError().header("exception", exception.getMessage()).build();
    }
}
