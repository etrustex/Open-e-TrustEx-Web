/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.util.ExternalAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author apladap
 *
 */
@Controller
@RequestMapping("/ping.do")
public class PingController {

	@Autowired
	private ExternalAuthenticationService externalAuthenticationService;
	
	 @RequestMapping(method = RequestMethod.GET)
	 public @ResponseBody String ping(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 externalAuthenticationService.extendExternalTokenValidity();
		 return "isAlive";
	 }
}
