package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.model.Privilege;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/welcome.do")
public class WelcomeController {

    @Autowired
    protected SecurityChecker securityChecker;

    @Autowired
    protected UserSessionContext userSessionContext;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView doGet(HttpServletRequest request) throws Exception {
        if (securityChecker.hasPrivilege(Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)) {
            return new ModelAndView(new RedirectView("inbox.do"));
        } else if (securityChecker.hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)) {
            return new ModelAndView(new RedirectView("admin.do"));
        } else {
            return new ModelAndView(new RedirectViewWithLogging("error/error403.do", request, userSessionContext.getUser().getLogin()));
        }
    }
}
