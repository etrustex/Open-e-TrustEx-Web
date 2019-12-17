package eu.europa.ec.etrustex.webaccess.webservice;

import ec.services.wsdl.applicationresponse_2.ApplicationResponsePortType;
import ec.services.wsdl.documentbundle_2.DocumentBundlePortType;
import ec.services.wsdl.documentwrapper_2.DocumentWrapperPortType;
import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.RetrieveInterchangeAgreementsRequestPortType;
import ec.services.wsdl.retrieverequest_2.RetrieveRequestPortType;
import eu.europa.ec.etrustex.webaccess.webservice.model.ConnectionConfiguration;
import eu.europa.ec.etrustex.webaccess.webservice.model.P2PSignatureProperties;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.soap.MTOMFeature;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class NodeWebServiceProvider {

    private static final Logger logger = Logger.getLogger(NodeWebServiceProvider.class.getName());

    private ConnectionConfiguration connectionConfiguration;
    private P2PSignatureProperties p2PSignatureProperties;

    private IcaPortFactory icaPortFactory;

    public void setConnectionConfiguration(ConnectionConfiguration connectionConfiguration) {
        this.connectionConfiguration = connectionConfiguration;
        icaPortFactory.setConnectionConfiguration(connectionConfiguration);
    }

    public void setP2PSignatureProperties(P2PSignatureProperties p2PSignatureProperties) {
        this.p2PSignatureProperties = p2PSignatureProperties;
        icaPortFactory.setP2PSignatureProperties(p2PSignatureProperties);
    }

    @PostConstruct
    private void initPortFactoryObjects() {
        icaPortFactory = new IcaPortFactory();
    }

    public DocumentWrapperPortType getDocumentWrapperServicePort() {
        try {
            return buildDocumentWrapperPort();
        } catch (Exception e) {
            //just ignore it. If initialization fails, it will be thrown an IllegalStateException when the getter is called
            logger.warn("Init of DocumentWrapperPort failed", e);
        }
        return null;
    }

    private DocumentWrapperPortType buildDocumentWrapperPort() throws IOException, GeneralSecurityException {

        MTOMFeature mtomFeature = new MTOMFeature(connectionConfiguration.getChunkSize());
        List<WebServiceFeature> featureList = new ArrayList<>();
        featureList.add(mtomFeature);


        DocumentWrapperPortType localDocumentWrapperPort = (DocumentWrapperPortType) WebServiceHelper.buildJaxWsProxy(
                DocumentWrapperPortType.class, featureList, connectionConfiguration.isWebserviceLoggingEnabled(), connectionConfiguration.isP2pEnabled(),
                p2PSignatureProperties, connectionConfiguration.getDocumentWrapperServiceUrl());

        WebServiceHelper.enableChunking((BindingProvider) localDocumentWrapperPort, connectionConfiguration.getChunkSize());

        WebServiceHelper.enableAppletUserAgent((BindingProvider) localDocumentWrapperPort);

        return localDocumentWrapperPort;
    }

    public DocumentBundlePortType getDocumentBundleServicePort() {
        try {
            return buildDocumentBundlePort();
        } catch (Exception e) {
            //just ignore it. If initialization fails, it will be thrown an IllegalStateException when the getter is called
            logger.warn("Init of DocumentBundlePort failed", e);
        }
        return null;
    }

    private DocumentBundlePortType buildDocumentBundlePort() throws IOException, GeneralSecurityException {


        DocumentBundlePortType localDocumentBundlePort = (DocumentBundlePortType) WebServiceHelper.buildJaxWsProxy(
                DocumentBundlePortType.class, Collections.<WebServiceFeature>emptyList(), connectionConfiguration.isWebserviceLoggingEnabled(), connectionConfiguration.isP2pEnabled(),
                p2PSignatureProperties, connectionConfiguration.getDocumentBundleServiceUrl());

        WebServiceHelper.disableChunking((BindingProvider) localDocumentBundlePort);

        WebServiceHelper.enableAppletUserAgent((BindingProvider) localDocumentBundlePort);

        return localDocumentBundlePort;
    }

    public IcaPortFactory getIcaPortFactory() {
        return icaPortFactory;
    }

    public RetrieveRequestPortType buildRetrieveRequestPort() throws IOException, GeneralSecurityException {

        RetrieveRequestPortType retrieveRequestPortType = (RetrieveRequestPortType) WebServiceHelper.buildJaxWsProxy(
                RetrieveRequestPortType.class, Collections.<WebServiceFeature>emptyList(), connectionConfiguration.isWebserviceLoggingEnabled(), connectionConfiguration.isP2pEnabled(),
                p2PSignatureProperties, connectionConfiguration.getRetrieveRequestUrl());

        WebServiceHelper.disableChunking((BindingProvider) retrieveRequestPortType);

        return retrieveRequestPortType;
    }

    public ApplicationResponsePortType getApplicationResponsePort() {
        try {
            return buildApplicationResponsePort();
        } catch (Exception e) {
            //just ignore it. If initialization fails, it will be thrown an IllegalStateException when the getter is called
            logger.warn("Init of ApplicationResponsePort failed", e);
        }
        return null;
    }

    private ApplicationResponsePortType buildApplicationResponsePort() throws IOException, GeneralSecurityException {

        ApplicationResponsePortType localApplicationResponsePortType = (ApplicationResponsePortType) WebServiceHelper.buildJaxWsProxy(
                ApplicationResponsePortType.class, Collections.<WebServiceFeature>emptyList(), connectionConfiguration.isWebserviceLoggingEnabled(), connectionConfiguration.isP2pEnabled(),
                p2PSignatureProperties, connectionConfiguration.getApplicationResponseUrl());

        WebServiceHelper.disableChunking((BindingProvider) localApplicationResponsePortType);

        return localApplicationResponsePortType;
    }

    public void setupConnectionCredentials(DocumentWrapperPortType documentWrapperPortType, String nodeUserName, String nodePassword) {
        setupConnectionCredentials((BindingProvider)documentWrapperPortType, nodeUserName, nodePassword);
    }

    public void setupConnectionCredentials(DocumentBundlePortType documentBundlePortType, String nodeUserName, String nodePassword) {
        setupConnectionCredentials((BindingProvider)documentBundlePortType, nodeUserName, nodePassword);
    }

    public void setupConnectionCredentials(RetrieveInterchangeAgreementsRequestPortType retrieveInterchangeAgreementsRequestPortType, String nodeUserName, String nodePassword) {
        setupConnectionCredentials((BindingProvider)retrieveInterchangeAgreementsRequestPortType, nodeUserName, nodePassword);
    }

    public void setupConnectionCredentials(RetrieveRequestPortType retrieveRequestPortType, String nodeUserName, String nodePassword) {
        setupConnectionCredentials((BindingProvider)retrieveRequestPortType, nodeUserName, nodePassword);
    }

    public void setupConnectionCredentials(ApplicationResponsePortType applicationResponsePortType, String nodeUserName, String nodePassword) {
        setupConnectionCredentials((BindingProvider)applicationResponsePortType, nodeUserName, nodePassword);
    }

    private void setupConnectionCredentials(BindingProvider bindingProvider, String nodeUserName, String nodePassword) {
        Map<String, Object> contextMap = bindingProvider.getRequestContext();
        contextMap.put(BindingProvider.USERNAME_PROPERTY, nodeUserName);
        contextMap.put(BindingProvider.PASSWORD_PROPERTY, nodePassword);
    }
}
