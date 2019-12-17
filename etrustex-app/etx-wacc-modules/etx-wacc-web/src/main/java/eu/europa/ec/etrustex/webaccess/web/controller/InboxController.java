package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import eu.europa.ec.etrustex.webaccess.model.Party;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
@RequestMapping("/inbox.do")
public class InboxController extends AbstractMessageListController {

    private static final Logger logger = Logger.getLogger(InboxController.class);

    protected WebAction getAction() {
        return WebAction.INBOX;
    }

    @Override
    protected WebAction getMessageViewAction() {
        return WebAction.MESSAGE_VIEW_RECEIVED;
    }

    @RequestMapping(method = RequestMethod.POST, params = "selectedPartyId")
    public ModelAndView changeOperatorParty(
            @RequestParam(value = "selectedPartyId") long selectedPartyId,
            HttpServletRequest request) throws Exception {

        logger.info("Change to party with id [" + selectedPartyId + "]");

        Collection<Party> operatorParties = userSessionContext.getMessageParties();
        boolean partyChanged = false;
        for (Party party : operatorParties) {
            if (party.getId().equals(selectedPartyId)) {
                //requested party found!
                pageState.setPartyId(selectedPartyId);
                partyChanged = true;
                break;
            }
        }
        if (!partyChanged) {
            User user = userSessionContext.getUser();
            logger.warn("User " + user.getId() +
                    " is willing to see the the messages of partyId " + selectedPartyId +
                    " but he is not an OPERATOR for this party! Navigation blocked!");
            return new ModelAndView(new RedirectViewWithLogging("error/error403.do", request, user.getLogin()));
        }

        ModelAndView modelAndView = doGet(request);

        return modelAndView;
    }

}
