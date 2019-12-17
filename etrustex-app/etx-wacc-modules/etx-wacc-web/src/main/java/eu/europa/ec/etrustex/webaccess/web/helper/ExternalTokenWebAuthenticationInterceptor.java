package eu.europa.ec.etrustex.webaccess.web.helper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExternalTokenWebAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ExternalTokenAuthenticationHelper externalTokenAuthenticationHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String connectionToken = request.getParameter("token");

        return externalTokenAuthenticationHelper.verifyToken(response, connectionToken);
    }
}
