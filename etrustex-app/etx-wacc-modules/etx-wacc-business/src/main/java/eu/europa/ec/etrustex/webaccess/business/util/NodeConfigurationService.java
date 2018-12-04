package eu.europa.ec.etrustex.webaccess.business.util;

import eu.europa.ec.etrustex.webaccess.business.administration.CacheService;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.business.facade.administration.CacheResetListener;
import eu.europa.ec.etrustex.webaccess.webservice.NodeWebServiceProvider;
import eu.europa.ec.etrustex.webaccess.webservice.model.ConnectionConfiguration;
import eu.europa.ec.etrustex.webaccess.webservice.model.P2PSignatureProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Service("nodeConfigurationService")
public class NodeConfigurationService implements CacheResetListener {

    private static final Logger logger = Logger.getLogger(NodeConfigurationService.class.getName());

    @Value("${org.apache.wss4j.crypto.merlin.keystore.alias}")
    private String p2pKeystoreAlias;

    @Value("${org.apache.wss4j.crypto.merlin.keystore.password}")
    private String p2pKeystorePassword;

    @Value("${org.apache.wss4j.crypto.merlin.keystore.private.password}")
    private String p2pKeystorePrivatePassword;

    @Value("${org.apache.wss4j.crypto.merlin.keystore.file}")
    private String p2pKeystoreFile;

    @Value("${org.apache.wss4j.crypto.merlin.truststore.password}")
    private String p2pTruststorePassword;

    @Value("${org.apache.wss4j.crypto.merlin.truststore.file}")
    private String p2pTruststoreFile;

    @Autowired
    protected ConfigurationService configurationService;

    @Autowired
    protected NodeWebServiceProvider nodeWebServiceProvider;

    @Autowired
    private CacheService cacheService;

    @PostConstruct
    private void init() {
        initTransmissionConfigurations();
        cacheService.registerCacheResetListener(this);
    }

    @Override
    public void onCacheReset() {
        initTransmissionConfigurations();
    }

    protected void initTransmissionConfigurations() {
        ConnectionConfiguration connectionConfiguration = buildNodeConfiguration();
        P2PSignatureProperties signatureProperties = buildP2PSignatureProperties();

        nodeWebServiceProvider.setConnectionConfiguration(connectionConfiguration);
        nodeWebServiceProvider.setP2PSignatureProperties(signatureProperties);
    }

    protected P2PSignatureProperties buildP2PSignatureProperties() {
        return new P2PSignatureProperties(p2pKeystoreAlias, p2pKeystorePassword, p2pKeystorePrivatePassword, p2pKeystoreFile,
                        p2pTruststorePassword, p2pTruststoreFile);
    }

    protected ConnectionConfiguration buildNodeConfiguration() {

        Configuration config = configurationService.getConfiguration();
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
        connectionConfiguration.setDocumentWrapperServiceUrl(config.getDocumentWrapperServiceURI());
        connectionConfiguration.setDocumentBundleServiceUrl(config.getDocumentBundleServiceURI());
        connectionConfiguration.setInterchangeAgreementUrl(config.getInterchangeAgreementURI());
        connectionConfiguration.setWebserviceLoggingEnabled(config.isWebserviceLoggingEnabled());
        connectionConfiguration.setRetrieveRequestUrl(config.getRetrieveRequestURI());
        connectionConfiguration.setApplicationResponseUrl(config.getApplicationResponseURI());
        connectionConfiguration.setP2pEnabled(config.isNodeSecurityP2pEnabled());

        return connectionConfiguration;
    }
}
