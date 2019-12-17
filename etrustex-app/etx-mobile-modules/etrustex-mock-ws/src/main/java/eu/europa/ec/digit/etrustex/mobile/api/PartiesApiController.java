package eu.europa.ec.digit.etrustex.mobile.api;

import eu.europa.ec.digit.etrustex.mobile.bussiness.GetMessagesUseCase;
import eu.europa.ec.digit.etrustex.mobile.model.InboxCounters;
import eu.europa.ec.digit.etrustex.mobile.model.Messages;
import eu.europa.ec.digit.etrustex.mobile.model.Parties;
import eu.europa.ec.digit.etrustex.mobile.model.Party;
import eu.europa.ec.digit.etrustex.mobile.model.SentCounters;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-22T16:16:31.972+02:00")

@Controller
@RestController
@RequestMapping("/mobile/services")
public class PartiesApiController implements PartiesApi {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Random rand = new Random();

    @Autowired
    GetMessagesUseCase getMessagesUseCase;

    @GetMapping("/parties/{partyId}/inboxcounters")
    public ResponseEntity<InboxCounters> getInboxCountersByParty(@ApiParam(value = "ID of party", required = true) @PathVariable("partyId") String partyId) {
        if (partyHasData(partyId)) {
            return ResponseEntity.ok(getInboxCounters());
        }
        return ResponseEntity.ok(new InboxCounters().all("0").read("0"));
    }

    private boolean partyHasData(String partyId) {
        return !partyId.equalsIgnoreCase("223");
    }

    @GetMapping("/parties/{partyId}/inbox")
    public ResponseEntity<Messages> getInboxMessages(@ApiParam(value = "ID of party", required = true) @PathVariable("partyId") String partyId,
                                                     @NotNull @ApiParam(value = "the index of the messages in the full list", required = true) @RequestParam(value = "startfrom", required = true) String startfrom,
                                                     @NotNull @ApiParam(value = "the number of messages fetched from the server", required = true) @RequestParam(value = "resultsize", required = true) String resultsize,
                                                     @ApiParam(value = "status filter option") @RequestParam(value = "status", required = false) String status,
                                                     @ApiParam(value = "subject filter option") @RequestParam(value = "subject", required = false) String subject,
                                                     @ApiParam(value = "sorting option") @RequestParam(value = "sortorder", required = false) String sortorder) {
        if (partyHasData(partyId)) {
            return ResponseEntity.ok(getMessagesUseCase.getInboxMessages(partyId, status, startfrom, resultsize));
        }
        // no messages for this party
        return ResponseEntity.ok(new Messages().messageCount("0").hasMoreMessages(false));
    }

    @GetMapping("/parties")
    public ResponseEntity<Parties> getPartiesByUsername() {
        // do some magic!
        return ResponseEntity.ok(getParties());
    }

    @GetMapping("/parties/{partyId}/sentcounters")
    @Override
    public ResponseEntity<SentCounters> getSentCountersByParty(@ApiParam(value = "ID of party", required = true) @PathVariable("partyId") String partyId) {
        if (partyHasData(partyId)) {
            return ResponseEntity.ok(getSentCounters());
        }
        return ResponseEntity.ok(new SentCounters().all("0").delivered("0").failed("0").none("0").read("0"));
    }

    @Override
    @GetMapping("/parties/{partyId}/sent")
    public ResponseEntity<Messages> getSentMessages(@ApiParam(value = "ID of party", required = true) @PathVariable("partyId") String partyId,
                                                    @NotNull @ApiParam(value = "the index of the messages in the full list", required = true) @RequestParam(value = "startfrom", required = true) String startfrom,
                                                    @NotNull @ApiParam(value = "the number of messages fetched from the server", required = true) @RequestParam(value = "resultsize", required = true) String resultsize,
                                                    @ApiParam(value = "status filter option") @RequestParam(value = "status", required = false) String status,
                                                    @ApiParam(value = "subject filter option") @RequestParam(value = "subject", required = false) String subject,
                                                    @ApiParam(value = "sorting option") @RequestParam(value = "sortorder", required = false) String sortorder) {
        if (partyHasData(partyId)) {
            return ResponseEntity.ok(getMessagesUseCase.getSentMessages(partyId, status, startfrom, resultsize));
        }
        // no messages for this party
        return ResponseEntity.ok(new Messages().messageCount("0").hasMoreMessages(false));
    }

    private Parties getParties() {
        List<Party> list = Arrays.asList(
                createParty("123", "party ext TEAM 123"),
                createParty("223", "party ext TEAM 223"),
                createParty("225", "party ext TEAM 225"),
                createParty("333", "party ext TEAM 333")
        );
        return new Parties().partyList(list);
    }

    private Party createParty(String id, String name) {
        return new Party().id(id).name(name);
    }

    private InboxCounters getInboxCounters() {
        return new InboxCounters().all(Integer.toString(Math.abs(rand.nextInt()) % 150)).read(Integer.toString(Math.abs(rand.nextInt()) % 50));
    }

    private SentCounters getSentCounters() {
        return new SentCounters()
            .all(Integer.toString(Math.abs(rand.nextInt()) % 200))
            .delivered(Integer.toString(Math.abs(rand.nextInt()) % 50))
            .failed(Integer.toString(Math.abs(rand.nextInt()) % 20))
            .none(Integer.toString(Math.abs(rand.nextInt()) % 10))
            .read(Integer.toString(Math.abs(rand.nextInt()) % 100));
    }

}
