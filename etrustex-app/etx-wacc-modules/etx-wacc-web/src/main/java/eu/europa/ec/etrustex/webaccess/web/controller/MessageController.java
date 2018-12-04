package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Metadata;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller related to message's logic.
 */

@Controller
public class MessageController {

    private static final Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    protected MailboxManager mailboxManager;

    @Autowired
    private AttachmentHandler attachmentHandler;

    @Autowired
    protected UserSessionContext userSessionContext;

    /**
     * Exports the summary of the message in PDF format.
     *
     * @param request   Request.
     * @param messageId Message id.
     * @return Summary of the message.
     */

    @RequestMapping(method = RequestMethod.GET, value = "/exportMessage.do")
    @ResponseBody
    public ModelAndView exportMessage(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "messageId", required = true) String messageId) {
        ModelAndView modelAndView = new ModelAndView("MessagePdfView");
        Long messageIdLong = null;

        try {
            messageIdLong = Long.parseLong(messageId);
        } catch (NumberFormatException numberFormatExecption) {
            logger.warn(messageId + " is not a valid message id");
            return new ModelAndView(new RedirectViewWithLogging("error/error403.do", request, userSessionContext.getUser().getLogin()));
        }

        Message message = mailboxManager.getMessageByMessageId(messageIdLong);

        if (message == null) {
            logger.warn("Message with id: " + messageId + " not found");
            return new ModelAndView(new RedirectViewWithLogging("error/error404.do", request, userSessionContext.getUser().getLogin()));
        }

        Metadata metadata = attachmentHandler.getMetadata(messageIdLong);
        List<Attachment> attachments = mailboxManager.getAttachments(messageIdLong, null);

        response.setHeader("Content-Disposition", "attachment;filename=MessageSummary.pdf");

        modelAndView.addObject("message", message);
        modelAndView.addObject("attachments", attachments);
        modelAndView.addObject("metadata", metadata);

        return modelAndView;
    }

}
