package eu.europa.ec.digit.etrustex.mobile.api;

import eu.europa.ec.digit.etrustex.mobile.model.Attachments;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-29T09:50:38.249+02:00")

@Api(value = "messages", description = "the messages API")
public interface MessagesApi {

    @ApiOperation(value = "Get message attachments metadata", notes = "", response = Attachments.class, tags={ "messages", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Attachments.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Attachments.class),
        @ApiResponse(code = 404, message = "Message not found", response = Attachments.class),
        @ApiResponse(code = 500, message = "internal server error", response = Attachments.class) })
    @RequestMapping(value = "/messages/{messageId}/attachments",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Attachments> getAttachmentsByMessageId(@ApiParam(value = "ID of the message", required = true) @PathVariable("messageId") String messageId) {
        // do some magic!
        return new ResponseEntity<Attachments>(HttpStatus.OK);
    }

    @ApiOperation(value = "Report that a message was read", response = Void.class, tags={ "messages", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Void.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
            @ApiResponse(code = 404, message = "Message not found", response = Void.class),
            @ApiResponse(code = 500, message = "internal server error", response = Void.class) })
    @RequestMapping(value = "/messages/{messageId}/read",
            method = RequestMethod.POST)
    default ResponseEntity<Void> reportMessageRead(@ApiParam(value = "ID of the message",required=true ) @PathVariable("messageId") String messageId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
