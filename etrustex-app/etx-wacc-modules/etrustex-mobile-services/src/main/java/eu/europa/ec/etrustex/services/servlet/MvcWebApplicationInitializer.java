package eu.europa.ec.etrustex.services.servlet;

import eu.europa.ec.etrustex.services.AppConfig;
import eu.europa.ec.etrustex.services.RESTConfig;
import eu.europa.ec.etrustex.services.SecurityConfig;
import eu.europa.ec.etrustex.services.listener.SessionListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * We need to directly implement WebApplicationInitializer to ensure that Weblogic picks it up.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

        servletContext.addListener(new SessionListener());
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SecurityConfig.class, AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { RESTConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/api/*" };
    }

    @Override
    protected String getServletName() {
        return "etrustex-web-servlet";
    }
}
