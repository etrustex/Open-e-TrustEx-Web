package eu.europa.ec.etrustex.webaccess.webservice;

import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.RetrieveInterchangeAgreementsRequestPortType;
import eu.europa.ec.etrustex.webaccess.webservice.model.ConnectionConfiguration;
import eu.europa.ec.etrustex.webaccess.webservice.model.P2PSignatureProperties;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceFeature;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class IcaPortFactory extends BasePooledObjectFactory<RetrieveInterchangeAgreementsRequestPortType> {

    private ConnectionConfiguration connectionConfiguration;
    private P2PSignatureProperties p2PSignatureProperties;

    @Override
    public RetrieveInterchangeAgreementsRequestPortType create() throws Exception {
        return buildInterchangeAgreementPort();
    }

    @Override
    public PooledObject<RetrieveInterchangeAgreementsRequestPortType> wrap(RetrieveInterchangeAgreementsRequestPortType icaPortType) {
        return new DefaultPooledObject<>(icaPortType);
    }

    private RetrieveInterchangeAgreementsRequestPortType buildInterchangeAgreementPort() throws IOException, GeneralSecurityException {

        RetrieveInterchangeAgreementsRequestPortType localRetrieveInterchangeAgreementsRequestPortType = (RetrieveInterchangeAgreementsRequestPortType) WebServiceHelper.buildJaxWsProxy(
                RetrieveInterchangeAgreementsRequestPortType.class, Collections.<WebServiceFeature>emptyList(), connectionConfiguration.isWebserviceLoggingEnabled(), connectionConfiguration.isP2pEnabled(),
                p2PSignatureProperties, connectionConfiguration.getInterchangeAgreementUrl());


        WebServiceHelper.disableChunking((BindingProvider) localRetrieveInterchangeAgreementsRequestPortType);

        return localRetrieveInterchangeAgreementsRequestPortType;
    }

    public void setConnectionConfiguration(ConnectionConfiguration connectionConfiguration) {
        this.connectionConfiguration = connectionConfiguration;
    }

    public void setP2PSignatureProperties(P2PSignatureProperties p2PSignatureProperties) {
        this.p2PSignatureProperties = p2PSignatureProperties;
    }
}
