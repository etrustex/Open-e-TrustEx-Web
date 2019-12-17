package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.web.model.FilterFormBean;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/outbox.do")
public class OutboxController extends AbstractMessageListController {

    protected WebAction getAction() {
        return WebAction.OUTBOX;
    }

    @Override
    protected WebAction getMessageViewAction() {
        return WebAction.MESSAGE_VIEW_SENT;
    }

}
