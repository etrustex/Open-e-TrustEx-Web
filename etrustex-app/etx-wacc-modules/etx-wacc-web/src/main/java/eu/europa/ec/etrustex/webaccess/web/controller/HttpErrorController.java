package eu.europa.ec.etrustex.webaccess.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error/{errorContent}.do")
public class HttpErrorController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView doError(@PathVariable("errorContent") String errorContent, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("error/" + errorContent);
        modelAndView.addObject("badUrl", request.getAttribute("javax.servlet.error.request_uri"));
        modelAndView.addObject("exception", request.getAttribute("javax.servlet.error.exception"));
        return modelAndView;
    }
}
