package eu.europa.ec.digit.etrustex.mobile.api;

import eu.europa.ec.digit.etrustex.mobile.model.InboxCounters;
import eu.europa.ec.digit.etrustex.mobile.model.Messages;
import eu.europa.ec.digit.etrustex.mobile.model.Parties;
import eu.europa.ec.digit.etrustex.mobile.model.SentCounters;
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

@Api(value = "parties", description = "the parties API")
public interface PartiesApi {

    @ApiOperation(value = "Get count of all and read messages in inbox", notes = "", response = InboxCounters.class, tags={ "parties", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = InboxCounters.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = InboxCounters.class),
        @ApiResponse(code = 404, message = "Party not found", response = InboxCounters.class),
        @ApiResponse(code = 500, message = "internal server error", response = InboxCounters.class) })
    @RequestMapping(value = "/parties/{partyId}/inboxcounters",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<InboxCounters> getInboxCountersByParty(@ApiParam(value = "ID of party",required=true ) @PathVariable("partyId") String partyId) {
        // do some magic!
        return new ResponseEntity<InboxCounters>(HttpStatus.OK);
    }


    @ApiOperation(value = "Get inbox messages", notes = "", response = Messages.class, tags={ "parties", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Messages.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Messages.class),
        @ApiResponse(code = 404, message = "Party not found", response = Messages.class),
        @ApiResponse(code = 500, message = "internal server error", response = Messages.class) })
    @RequestMapping(value = "/parties/{partyId}/inbox",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Messages> getInboxMessages(@ApiParam(value = "ID of party",required=true ) @PathVariable("partyId") String partyId,
         @NotNull @ApiParam(value = "the index of the messages in the full list", required = true) @RequestParam(value = "startfrom", required = true) String startfrom,
         @NotNull @ApiParam(value = "the number of messages fetched from the server", required = true) @RequestParam(value = "resultsize", required = true) String resultsize,
         @ApiParam(value = "status filter option") @RequestParam(value = "status", required = false) String status,
         @ApiParam(value = "subject filter option") @RequestParam(value = "subject", required = false) String subject,
         @ApiParam(value = "sorting order option (asc or desc)") @RequestParam(value = "sortorder", required = false) String sortorder) {
        // do some magic!
        return new ResponseEntity<Messages>(HttpStatus.OK);
    }


    @ApiOperation(value = "Get parties for logged user", notes = "", response = Parties.class, tags={ "parties", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Parties.class),
        @ApiResponse(code = 500, message = "internal server error", response = Parties.class) })
    @RequestMapping(value = "/parties",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Parties> getPartiesByUsername() {
        // do some magic!
        return new ResponseEntity<Parties>(HttpStatus.OK);
    }


    @ApiOperation(value = "Get count of all and read messages in inbox", notes = "", response = SentCounters.class, tags={ "parties", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = SentCounters.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = SentCounters.class),
        @ApiResponse(code = 404, message = "Party not found", response = SentCounters.class),
        @ApiResponse(code = 500, message = "internal server error", response = SentCounters.class) })
    @RequestMapping(value = "/parties/{partyId}/sentcounters",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<SentCounters> getSentCountersByParty(@ApiParam(value = "ID of party",required=true ) @PathVariable("partyId") String partyId) {
        // do some magic!
        return new ResponseEntity<SentCounters>(HttpStatus.OK);
    }


    @ApiOperation(value = "Get sent messages", notes = "", response = Messages.class, tags={ "parties", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Messages.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Messages.class),
        @ApiResponse(code = 404, message = "Party not found", response = Messages.class),
        @ApiResponse(code = 500, message = "internal server error", response = Messages.class) })
    @RequestMapping(value = "/parties/{partyId}/sent",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Messages> getSentMessages(@ApiParam(value = "ID of party",required=true ) @PathVariable("partyId") String partyId,
         @NotNull @ApiParam(value = "the index of the messages in the full list", required = true) @RequestParam(value = "startfrom", required = true) String startfrom,
         @NotNull @ApiParam(value = "the number of messages fetched from the server", required = true) @RequestParam(value = "resultsize", required = true) String resultsize,
         @ApiParam(value = "status filter option") @RequestParam(value = "status", required = false) String status,
         @ApiParam(value = "subject filter option") @RequestParam(value = "subject", required = false) String subject,
         @ApiParam(value = "sorting order option (asc or desc)") @RequestParam(value = "sortorder", required = false) String sortorder) {
        // do some magic!
        return new ResponseEntity<Messages>(HttpStatus.OK);
    }

}
