package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;


    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "getConfigMailDistribution.do")
    protected
    @ResponseBody
    List<String> getConfigMailDistribution(){
        List<String> distribution = new ArrayList<String>();
        Configuration configurations = configurationService.getConfiguration();
        for (String str: configurations.getConfigMailDistribution().split(";")) {
            distribution.add(str);
        }
        return distribution;
    }
}
