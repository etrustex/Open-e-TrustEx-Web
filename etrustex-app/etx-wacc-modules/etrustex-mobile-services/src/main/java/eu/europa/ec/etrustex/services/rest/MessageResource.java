package eu.europa.ec.etrustex.services.rest;

import eu.europa.ec.etrustex.services.business.MailboxService;
import eu.europa.ec.etrustex.services.business.UserService;
import eu.europa.ec.etrustex.services.converter.RestAttachmentConverter;
import eu.europa.ec.etrustex.services.model.RestAttachments;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
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
 * REST controller where all the REST services regarding the messages will be implemented.
 */

@RestController
@RequestMapping(value = "/messages")
public class MessageResource {

    @Autowired
    private MailboxService mailboxService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MessageResource.class);

    /**
     * Get message attachments metadata.
     *
     * @param messageId Message id.
     * @return List of attachments. The HTTP status code will be as follows:
     * - 200: Successful operation.
     * - 400: Invalid ID supplied.
     * - 404: Message not found.
     * - 500: Internal server error.
     */

    @GetMapping("/{messageId}/attachments")
    public ResponseEntity<RestAttachments> getAttachmentsByMessageId(@PathVariable(name = "messageId", required = true) String messageId) {
        Long messageLongId = null;

        try {
            messageLongId = Long.valueOf(messageId);
        } catch (NumberFormatException numberFormatException) {
            logger.warn(messageId + " is not a valid message id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Message message = mailboxService.getMessageByMessageId(messageLongId);
        if (message == null) {
            logger.warn("Message with id: " + messageLongId + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Attachment> attachments = mailboxService.getAttachments(messageLongId);
        RestAttachments result = RestAttachmentConverter.getInstance().convertToRestAttachments(attachments);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Report that a message was read.
     *
     * @param currentUser Logged user.
     * @param messageId   Message id.
     * @return The HTTP status code will be as follows:
     * - 200: Successful operation.
     * - 400: Invalid ID supplied.
     * - 404: Message not found.
     * - 500: Internal server error.
     */

    @PostMapping("/{messageId}/read")
    public ResponseEntity reportMessageRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable(name = "messageId", required = true) String messageId) {
        Long messageLongId = null;

        try {
            messageLongId = Long.valueOf(messageId);
        } catch (NumberFormatException numberFormatException) {
            logger.warn(messageId + " is not a valid message id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Message message = mailboxService.getMessageByMessageId(messageLongId);
        if (message == null) {
            logger.warn("Message with id: " + messageLongId + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userService.getUserDetails(currentUser.getUsername());
        mailboxService.markMessageReadByUser(messageLongId, user.getId());

        return new ResponseEntity(HttpStatus.OK);
    }

}
