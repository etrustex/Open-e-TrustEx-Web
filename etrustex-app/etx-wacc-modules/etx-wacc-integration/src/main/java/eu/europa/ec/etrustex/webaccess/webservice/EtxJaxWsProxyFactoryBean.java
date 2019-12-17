package eu.europa.ec.etrustex.webaccess.webservice;

import eu.europa.ec.etrustex.webaccess.webservice.model.P2PSignatureProperties;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.support.JaxWsServiceFactoryBean;
import org.apache.log4j.Logger;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceFeature;
import java.util.List;


class EtxJaxWsProxyFactoryBean {

    private static final Logger logger = Logger.getLogger(EtxJaxWsProxyFactoryBean.class);
    private static final Logger cxfLogger = Logger.getLogger(LoggingOutInterceptor.class);

    public static Object build(Class portType, List<WebServiceFeature> wsFeatures, boolean loggingEnabled,
                               boolean p2pEnabled, P2PSignatureProperties p2PSignatureProperties, String serviceEndpoint) {

        logger.debug("Build the JAXWS proxy: service [" + portType.toString());


        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setServiceClass(portType);
        proxyFactory.setAddress(serviceEndpoint);

        if (loggingEnabled && cxfLogger.isInfoEnabled()) {
            final LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
            loggingOutInterceptor.setShowMultipartContent(false);
            proxyFactory.getOutInterceptors().add(loggingOutInterceptor);

            LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
            loggingInInterceptor.setShowMultipartContent(false);
            proxyFactory.getInInterceptors().add(loggingInInterceptor);
        }

        if (wsFeatures != null) {
            JaxWsServiceFactoryBean serviceFactoryBean = (JaxWsServiceFactoryBean) proxyFactory.getServiceFactory();
            serviceFactoryBean.setWsFeatures(wsFeatures);
        }

        Object proxyObject = proxyFactory.create();

        // for info read: http://cxf.apache.org/faq.html#FAQ-AreJAX-WSclientproxiesthreadsafe?
        // ICA service has different user/pass for parties
        ((BindingProvider) proxyObject).getRequestContext().put("thread.local.request.context", "true");
        ((BindingProvider) proxyObject).getRequestContext().put("schema-validation-enabled", "false");
        ((BindingProvider) proxyObject).getRequestContext().put("set-jaxb-validation-event-handler", "false");

        if (p2pEnabled) {
            WebServiceHelper.enableP2p((BindingProvider) proxyObject, p2PSignatureProperties);
        }

        return proxyObject;
    }
}
