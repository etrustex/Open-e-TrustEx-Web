package eu.europa.ec.digit.etrustex.mobile.api;

import eu.europa.ec.digit.etrustex.mobile.model.Attachment;
import eu.europa.ec.digit.etrustex.mobile.model.Attachments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.*;
import java.util.Random;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-29T09:50:38.249+02:00")
@Controller
@RequestMapping("/mobile/services")
public class MessagesApiController implements MessagesApi {

    private Random rand = new Random();




    @Override
    @GetMapping("/messages/{messageId}/attachments")
    public ResponseEntity<Attachments> getAttachmentsByMessageId(@PathVariable("messageId") String messageId) {
        return ResponseEntity.ok(getAttachments(messageId));
    }

    private Attachments getAttachments(String messageId) {
        Attachments attachments = new Attachments();
        for (int i = 0; i < 200; i++) {
            boolean isPdf = rand.nextBoolean();
            Attachment attachment = new Attachment();
            attachment
                .id(rand.nextInt() + "")
                .messageId(messageId)
                .name("COM(2017)45/eGREFFe/Official/Append. - " + (Math.abs(rand.nextInt()) % 1000))
                .mimeType(isPdf ? "application/pdf" : "application/xls");
            attachments.addAttachmentListItem(attachment);
        }
        return attachments;
    }

    @Override
    @PostMapping("/messages/{messageId}/read")
    public ResponseEntity<Void> reportMessageRead(@PathVariable("messageId") String messageId) {
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
