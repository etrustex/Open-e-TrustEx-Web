package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.facade.BusinessFacade;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/messageDelete.do")
public class MessageDeleteController {

    @Autowired
    private SecurityChecker securityChecker;

    @Autowired
    private BusinessFacade businessFacade;

    @Autowired
    private MailboxManager mailboxManager;

    @Autowired
    protected PageState pageState;

    @Autowired
    protected UserSessionContext userSessionContext;

    private static final Logger logger = Logger.getLogger(MessageDeleteController.class);

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView deleteDraftMessage(@RequestParam(value = "mid", required = true) Long messageId, HttpServletRequest request) throws Exception {

        logger.info("Start deleting draft message with id " + messageId);

        Message message = mailboxManager.getMessageByMessageId(messageId);

        if (!isAuthorized(message)) {
            return new ModelAndView(new RedirectViewWithLogging("error/error403.do", request, userSessionContext.getUser().getLogin()));
        }

        pageState.setPartyId(message.getLocalParty().getId());

        businessFacade.disableMessage(message);
        logger.info("Completed disabling draft message with id " + messageId);

        //go back to draft
        ModelAndView modelAndView = new ModelAndView(new RedirectView(WebAction.DRAFTS.getDo()));
        modelAndView.addAllObjects(pageState.getParams());
        return modelAndView;
    }


    protected boolean isAuthorized(Message message) {
        return message != null
            && message.getActiveState()
            && message.getMsgState() == Message.MessageState.DRAFT
            && securityChecker.canAccessMessagesOfParty(message.getLocalParty());
    }

}
