package eu.europa.ec.etrustex.webaccess.business.security;

import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component("securityProvider")
public class SecurityProvider implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        EtxSecurityProvider.init();
    }

    @PreDestroy
    public void destroyed() {
        EtxSecurityProvider.destroy();
    }
}
