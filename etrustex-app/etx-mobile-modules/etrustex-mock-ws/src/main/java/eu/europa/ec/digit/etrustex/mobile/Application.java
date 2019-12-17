package eu.europa.ec.digit.etrustex.mobile;

import eu.europa.ec.digit.etrustex.mobile.api.ApiOriginFilter;
import eu.europa.ec.digit.etrustex.mobile.auth.RestAuthenticationFilter;
import eu.europa.ec.digit.etrustex.mobile.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@Configuration
@EnableSwagger2
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${allowFakeIdentity:false}")
    private boolean allowFakeIdentity;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        logger.info("Configuring e-TrustEx mock ws application ...");
        return super.configure(builder);
    }

    /**
     * Need CORS support to mobile app to be run outside the war, inside its own web server (via 'ionic serve').
     */
    @Bean
    public FilterRegistrationBean apiOriginFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ApiOriginFilter());
        registration.addUrlPatterns("/mobile/services/*");
        registration.setName("apiOriginFilter");
        return registration;
    }

    /**
     * Ensure all calls to /mobile are accessed after ECAS authentication or using accepted fake authentication.
     */
    @Bean
    public FilterRegistrationBean restAuthenticationFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RestAuthenticationFilter());
        registration.addInitParameter(RestAuthenticationFilter.ALLOW_FAKE_IDENTITY_PARAM, String.valueOf(allowFakeIdentity));
        registration.addUrlPatterns("/mobile/services/*");
        registration.setName("restAuthenticationFilter");
        return registration;
    }

    /**
     * Needed to GZIP the HTTP response content.
     * NOTE: Webgate servers are compressing content automatically.
     *
     * @Bean public FilterRegistrationBean compressingFilter() {
     * FilterRegistrationBean registration = new FilterRegistrationBean();
     * registration.setFilter(new CompressingFilter());
     * registration.addInitParameter("includeContentTypes", "text/css,text/html,text/javascript,application/javascript,application/json");
     * registration.addUrlPatterns("/*");
     * registration.setName("compressingFilter");
     * return registration;
     * }
     */

    @Bean
    @Scope(WebApplicationContext.SCOPE_SESSION)
    public User loggedInUser(HttpServletRequest request) {
        // The RestAuthenticationFilter should have already saved the 'user' in request
        return (User) request.getAttribute("user");
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
                = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(1000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }
}
