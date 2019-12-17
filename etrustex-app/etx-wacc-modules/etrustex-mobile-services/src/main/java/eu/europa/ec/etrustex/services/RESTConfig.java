package eu.europa.ec.etrustex.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"eu.europa.ec.etrustex.services.rest"})
public class RESTConfig extends WebMvcConfigurerAdapter {

}
