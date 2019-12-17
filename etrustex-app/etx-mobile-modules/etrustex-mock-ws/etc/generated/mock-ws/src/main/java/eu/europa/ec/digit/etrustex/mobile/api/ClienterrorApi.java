package eu.europa.ec.digit.etrustex.mobile.api;

import eu.europa.ec.digit.etrustex.mobile.model.ClientError;
import eu.europa.ec.digit.etrustex.mobile.model.ServerError;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-04T11:03:17.949+02:00")

@Api(value = "clienterror", description = "the clienterror API")
public interface ClienterrorApi {

    @ApiOperation(value = "Report client side errors", notes = "", response = Void.class, tags={ "clienterror", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Void.class),
        @ApiResponse(code = 500, message = "internal server error", response = Void.class) })
    @RequestMapping(value = "/clienterror",
        produces = { "application/xml", "application/json" }, 
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> reportClientError(@ApiParam(value = "Details of the error generated on the client side" ,required=true ) @RequestBody ClientError body) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
