package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * The Spring controller for handling logout operations. Performs all the needed
 * cleanup (sessions, resources, etc).
 *
 * @author chiridl
 */
@Controller
@RequestMapping("/logout.do")
public class LogoutController {

    @Autowired
    protected UserSessionContext userSessionContext;

    private static final Logger logger = Logger.getLogger(LogoutController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView doGet(HttpSession session) throws Exception {

        User user = userSessionContext.getUser();
        logger.info("[" + user.getLogin() + "] logged out");

        ModelAndView modelAndView = new ModelAndView("logout");
        session.invalidate();
        return modelAndView;
    }
}
