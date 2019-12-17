package eu.europa.ec.etrustex.webaccess.web.utils;

import eu.europa.ec.etrustex.webaccess.model.User;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RedirectViewWithLogging extends org.springframework.web.servlet.view.RedirectView {


    public RedirectViewWithLogging(String url, HttpServletRequest request, String userLogin) {
        super(url);
        String uri = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        logger.info("Redirecting user " + userLogin + " from URL " + uri + " to " + url);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss_SSS");
        this.getAttributesMap().put("d", dateFormatter.format(new Date()));

    }
}
