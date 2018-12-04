package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/switchVersion")
public class SwitchVersionController {

    @Autowired
    protected UserSessionContext userSessionContext;

    //private static final Logger logger = Logger.getLogger(SwitchVersionController.class);

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void doGet() {
        userSessionContext.setUsingWebStart(!userSessionContext.isUsingWebStart());
    }
}
