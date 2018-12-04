package eu.europa.ec.etrustex.webaccess.rest;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST service to perform the validation of the token.
 */

@Path("/authenticate")
public interface TokenRestService {

    /**
     * Validates token and verifies ECAS user id.
     *
     * @param token Token to be validated.
     * @param userId ECAS user id.
     * @return HTTP 200 code if the token was successfully validated, HTTP 401 code otherwise.
     */

    @GET
    @Path(value = "/{token}/{userId}")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Descriptions({
            @Description(value = "Validate token", target = DocTarget.METHOD),
            @Description(value = "Response", target = DocTarget.RETURN)
    })
    Response validateToken(@Description("Token") @PathParam("token") String token, @Description("User id") @PathParam("userId") String userId);

}
