package eu.europa.ec.etrustex.services;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"eu.europa.ec.etrustex.services", "eu.europa.ec.etrustex.webaccess"})
@ImportResource( { "classpath*:dataAccessContext.xml" } )
@EnableTransactionManagement
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:app_server.properties"),
        @PropertySource("classpath:app_server.jboss.properties"),
        @PropertySource("classpath:app_server.weblogic.properties")
})
public class AppConfig {
    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
