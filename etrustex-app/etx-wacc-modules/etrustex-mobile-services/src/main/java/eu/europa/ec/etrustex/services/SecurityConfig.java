package eu.europa.ec.etrustex.services;

import eu.europa.ec.etrustex.services.filter.CustomJ2eePreAuthenticatedProcessingFilter;
import eu.europa.ec.etrustex.services.security.RestAccessDeniedHandler;
import eu.europa.ec.etrustex.services.security.RestAuthenticationEntryPoint;
import eu.europa.ec.etrustex.services.security.UserDetailsServiceCustom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${disable.ecas}")
    private boolean disableEcas;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = new UserDetailsByNameServiceWrapper<>(userSecurityService());

        http
                .cors().and() // Spring Security will use CORS configuration provided to Spring MVC in RESTConfig
                .sessionManagement()
                .sessionFixation().none()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler())
                .and().csrf().disable();
        // CSRF prevention is added by default in Spring
        // https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html#csrf-include-csrf-token
        // and in angular
        // https://github.com/angular/angular/blob/54e02449549448ebab6f255f2da0b4396665c6f0/packages/common/http/src/xsrf.ts#L76

        if(disableEcas) {
            http.jee().j2eePreAuthenticatedProcessingFilter(j2eePreAuthenticatedProcessingFilter()).authenticatedUserDetailsService(wrapper);
        } else {
            http.jee().authenticatedUserDetailsService(wrapper);
        }
    }


    /*
     * This is only needed for the mobile team to bypass EU Login
     */
    @Bean
    public CustomJ2eePreAuthenticatedProcessingFilter j2eePreAuthenticatedProcessingFilter() throws Exception {
        CustomJ2eePreAuthenticatedProcessingFilter j2eePreAuthenticatedProcessingFilter = new CustomJ2eePreAuthenticatedProcessingFilter();
        j2eePreAuthenticatedProcessingFilter.setAuthenticationManager(authenticationManagerBean());

        return j2eePreAuthenticatedProcessingFilter;
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
        if(disableEcas) {
            configuration.addAllowedOrigin("*");
        } else {
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:8100", "http://127.0.0.1:8100"));
        }

		configuration.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "OPTIONS", "PATCH"));
		configuration.setAllowedHeaders(Arrays.asList("Accept", "Accept-Encoding", "Accept-Language", "Authorization", "Cache-Control", "Connection", "Content-Type", "Cookie", "DNT", "Host", "Origin", "Referer", "User-Agent", "X-Requested-With"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


    @Bean
    UserDetailsService userSecurityService() {
        return new UserDetailsServiceCustom();
    }

    @Bean
    RestAuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    RestAccessDeniedHandler accessDeniedHandler() {
        return new RestAccessDeniedHandler();
    }
}
