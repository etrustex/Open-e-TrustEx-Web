package eu.europa.ec.etrustex.services.rest;

import eu.europa.ec.etrustex.services.business.MailboxService;
import eu.europa.ec.etrustex.services.business.MessageService;
import eu.europa.ec.etrustex.services.business.PartyService;
import eu.europa.ec.etrustex.services.business.UserService;
import eu.europa.ec.etrustex.services.converter.RestPartyConverter;
import eu.europa.ec.etrustex.services.model.RestInboxCounters;
import eu.europa.ec.etrustex.services.model.RestMessages;
import eu.europa.ec.etrustex.services.model.RestParties;
import eu.europa.ec.etrustex.services.model.RestSentCounters;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller where all the REST services regarding the parties will be implemented.
 */

@RestController
@RequestMapping(value = "/parties")
public class PartyResource {

    @Autowired
    private PartyService partyService;

    @Autowired
    private MailboxService mailboxService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    private static final Logger logger = LoggerFactory.getLogger(PartyResource.class);

    /**
     * Gets parties for logged user.
     *
     * @param currentUser Logged user.
     * @return List of parties. HTTP 200 for a successful operation, HTTP 500 otherwise.
     */

    @GetMapping
    public ResponseEntity<RestParties> getPartiesByUsername(@AuthenticationPrincipal UserDetails currentUser) {
        List<Party> userParties = partyService.getPartiesByUser(currentUser.getUsername());
        RestParties result = RestPartyConverter.getInstance().convertToRestParties(userParties);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets count of all and read messages in inbox.
     *
     * @param partyId     Party id.
     * @param currentUser Logged user.
     * @return Inbox counters. The HTTP status code will be as follows:
     * - 200: Successful operation.
     * - 400: Invalid ID supplied.
     * - 404: Party not found.
     * - 500: Internal server error
     */

    @GetMapping("/{partyId}/inboxcounters")
    public ResponseEntity<RestInboxCounters> getInboxCountersByParty(@AuthenticationPrincipal UserDetails currentUser, @PathVariable(name = "partyId") String partyId) {
        Long partyLongId;

        try {
            partyLongId = Long.valueOf(partyId);
        } catch (NumberFormatException numberFormatException) {
            logger.warn(partyId + " is not a valid party id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Party party = partyService.getPartyById(partyLongId);
        if (party == null) {
            logger.warn("Party with id: " + partyLongId + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userService.getUserDetails(currentUser.getUsername());
        RestInboxCounters result = mailboxService.getInboxCountersByParty(party, user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * Gets the counters used for sent messages (All, Read, Delivered, Failed, None)
     *
     * @param partyId
     * @return sent counters. The HTTP status code will be as follows:
     * - 200: Successful operation.
     * - 400: Invalid ID supplied.
     * - 404: Party not found.
     * - 500: Internal server error
     */

    @GetMapping("/{partyId}/sentcounters")
    public ResponseEntity<RestSentCounters> getSentCountersByParty(@PathVariable(name = "partyId") String partyId) {
        Long partyLongId;

        try {
            partyLongId = Long.valueOf(partyId);
        } catch (NumberFormatException numberFormatException) {
            logger.warn(partyId + " is not a valid party id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Party party = partyService.getPartyById(partyLongId);
        if (party == null) {
            logger.warn("Party with id: " + partyLongId + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RestSentCounters result = mailboxService.getSentCountersByParty(party);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    /**
     * Get sent messages by party.
     *
     * @param partyId    Part id.
     * @param startfrom  Index of the messages in the full list.
     * @param resultsize Number of messages fetched from the server.
     * @param status     Status filter option.
     * @param subject    Subject filter option.
     * @param sortorder  Sorting order option (asc or desc).
     * @return List of sent messages.
     * - 200: Successful operation.
     * - 400: Invalid ID supplied.
     * - 404: Party not found.
     * - 500: Internal server error
     */

    @GetMapping("/{partyId}/sent")
    public ResponseEntity<RestMessages> getSentMessages(@PathVariable(name = "partyId") String partyId, @RequestParam(name = "startfrom") String startfrom,
                                                        @RequestParam(name = "resultsize") String resultsize, @RequestParam(name = "status", required = false) String status,
                                                        @RequestParam(name = "subject", required = false) String subject, @RequestParam(name = "sortorder", required = false) String sortorder) {
        Long partyLongId;

        try {
            partyLongId = Long.valueOf(partyId);
        } catch (NumberFormatException numberFormatException) {
            logger.warn(partyId + " is not a valid party id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Party party = partyService.getPartyById(partyLongId);
        if (party == null) {
            logger.warn("Party with id: " + partyLongId + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RestMessages result = messageService.getMessages(Message.MessageState.SENT, party, startfrom, resultsize, status, subject, sortorder);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Get inbox messages by party.
     *
     * @param partyId    Part id.
     * @param startfrom  Index of the messages in the full list.
     * @param resultsize Number of messages fetched from the server.
     * @param status     Status filter option.
     * @param subject    Subject filter option.
     * @param sortorder  Sorting order option (asc or desc).
     * @return List of inbox messages.
     * - 200: Successful operation.
     * - 400: Invalid ID supplied.
     * - 404: Party not found.
     * - 500: Internal server error
     */

    @GetMapping("/{partyId}/inbox")
    public ResponseEntity<RestMessages> getInboxMessages(@PathVariable(name = "partyId") String partyId, @RequestParam(name = "startfrom") String startfrom,
                                                         @RequestParam(name = "resultsize") String resultsize, @RequestParam(name = "status", required = false) String status,
                                                         @RequestParam(name = "subject", required = false) String subject, @RequestParam(name = "sortorder", required = false) String sortorder) {
        Long partyLongId;

        try {
            partyLongId = Long.valueOf(partyId);
        } catch (NumberFormatException numberFormatException) {
            logger.warn(partyId + " is not a valid party id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Party party = partyService.getPartyById(partyLongId);
        if (party == null) {
            logger.warn("Party with id: " + partyLongId + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RestMessages result = messageService.getMessages(Message.MessageState.INCOMING, party, startfrom, resultsize, status, subject, sortorder);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}