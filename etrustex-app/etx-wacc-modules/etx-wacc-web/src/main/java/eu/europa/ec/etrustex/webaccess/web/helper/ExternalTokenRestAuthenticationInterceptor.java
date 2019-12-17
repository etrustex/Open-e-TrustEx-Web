package eu.europa.ec.etrustex.webaccess.web.helper;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("externalTokenRestAuthenticationInterceptor")
public class ExternalTokenRestAuthenticationInterceptor extends AbstractPhaseInterceptor<Message> {

    @Autowired
    private ExternalTokenAuthenticationHelper externalTokenAuthenticationHelper;

    public ExternalTokenRestAuthenticationInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        HttpServletResponse httpResponse = (HttpServletResponse) message.get(AbstractHTTPDestination.HTTP_RESPONSE);

        String connectionToken = request.getHeader("token");

        if (!externalTokenAuthenticationHelper.verifyToken(httpResponse, connectionToken)) {
            throw new Fault(new IllegalAccessException("Invalid user"));
        }
    }

}
