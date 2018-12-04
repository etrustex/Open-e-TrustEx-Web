package eu.europa.ec.etrustex.webaccess.webservice;

import eu.europa.ec.etrustex.webaccess.security.CertificateService;
import eu.europa.ec.etrustex.webaccess.webservice.model.P2PSignatureProperties;
import eu.europa.ec.etrustex.webaccess.webservice.security.ClientPasswordCallback;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.ConnectionType;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.cxf.ws.policy.PolicyBuilder;
import org.apache.cxf.ws.policy.WSPolicyFeature;
import org.apache.cxf.ws.security.SecurityConstants;
import org.apache.log4j.Logger;
import org.apache.neethi.Policy;
import org.apache.wss4j.common.crypto.Merlin;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceFeature;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;
import java.util.*;

class WebServiceHelper {

    private static final Logger logger = Logger.getLogger(WebServiceHelper.class.getName());

    private static final String P2P_POLICY_FILE = "/p2p-policy.xml";

    private static final String CALLBACK_HANDLER_PROPERTIES_FILE = "/callback.properties";

    private static final String USER_AGENT = "eTrustEx Applet";

    public static Object buildJaxWsProxy(Class portType, List<WebServiceFeature> wsFeatures, boolean loggingEnabled,
                                         boolean p2pEnabled, P2PSignatureProperties p2PSignatureProperties, String serviceEndpoint)
            throws MalformedURLException {

        Object proxyObject = EtxJaxWsProxyFactoryBean.build(portType, wsFeatures, loggingEnabled, p2pEnabled, p2PSignatureProperties, serviceEndpoint);
        Client wsClient = ClientProxy.getClient(proxyObject);
        disableKeepAllive(wsClient);

        return proxyObject;
   }

    private static void disableKeepAllive(Client wsClient) {
        HTTPClientPolicy httpClientPolicy = getHttpClient(wsClient);
        httpClientPolicy.setConnection(ConnectionType.CLOSE);
    }

    public static void enableChunking(BindingProvider bindingProvider, int chunkSize) throws GeneralSecurityException, IOException {
        Client endpointClient = ClientProxy.getClient(bindingProvider);

        HTTPClientPolicy httpClientPolicy = getHttpClient(endpointClient);
        httpClientPolicy.setAllowChunking(true);
        httpClientPolicy.setChunkingThreshold(chunkSize);
        httpClientPolicy.setChunkLength(chunkSize);
    }

    public static void disableChunking(BindingProvider bindingProvider) throws GeneralSecurityException, IOException {
        Client endpointClient = ClientProxy.getClient(bindingProvider);

        HTTPClientPolicy httpClientPolicy = getHttpClient(endpointClient);
        httpClientPolicy.setAllowChunking(false);
    }

    public static void enableAppletUserAgent(BindingProvider bindingProvider) {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("User-Agent", Collections.singletonList(USER_AGENT));
        bindingProvider.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
    }

    public static void enableP2p(BindingProvider bindingProvider, P2PSignatureProperties signatureProperties) {
        addWSSecurityParamsToContext(bindingProvider, signatureProperties);
        bindingProvider.getRequestContext().put(SecurityConstants.ENABLE_STREAMING_SECURITY, "true");
        try {
            initializedP2pPolicyFeature(ClientProxy.getClient(bindingProvider));
        } catch (Exception e) {
            logger.error("cannot initialize p2p policy: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void addWSSecurityParamsToContext(BindingProvider proxyObject, P2PSignatureProperties signatureProperties) {
        ClientPasswordCallback passwordCallback;
        if (CertificateService.P2P_CRYPTO_KEYSTORE_ALIAS.equals(signatureProperties.getKeystoreAlias())) {
            passwordCallback = new ClientPasswordCallback(buildAppletPrivateKeyProperties(signatureProperties.getKeystoreAlias(),
                                                                                          signatureProperties.getKeystorePrivatePassword()));
        } else {
            passwordCallback = new ClientPasswordCallback(CALLBACK_HANDLER_PROPERTIES_FILE);
        }
        proxyObject.getRequestContext().put(SecurityConstants.CALLBACK_HANDLER, passwordCallback);
        Properties properties = buildP2pProperties(signatureProperties);
        proxyObject.getRequestContext().put(SecurityConstants.SIGNATURE_PROPERTIES, properties);
    }

    private static Properties buildAppletPrivateKeyProperties(String keystoreAlias, String keystorePrivatePassword) {
        Properties properties = new Properties();
        properties.setProperty(ClientPasswordCallback.SIGNATURE_USERNAME_KEY, keystoreAlias);
        properties.setProperty(ClientPasswordCallback.SIGNATURE_PASSWORD_KEY, keystorePrivatePassword);
        return properties;
    }

    private static Properties buildP2pProperties(P2PSignatureProperties signatureProperties) {
        Properties properties = new Properties();
        properties.put("org.apache.wss4j.crypto.provider", Merlin.class.getCanonicalName());
        properties.put(addMerlinCryptoProviderPrefix(Merlin.KEYSTORE_ALIAS), signatureProperties.getKeystoreAlias());
        properties.put(addMerlinCryptoProviderPrefix(Merlin.KEYSTORE_TYPE), "jks");
        properties.put(addMerlinCryptoProviderPrefix(Merlin.KEYSTORE_PASSWORD), signatureProperties.getKeystorePassword());
        properties.put(addMerlinCryptoProviderPrefix(Merlin.KEYSTORE_PRIVATE_PASSWORD), signatureProperties.getKeystorePrivatePassword());
        properties.put(addMerlinCryptoProviderPrefix(Merlin.KEYSTORE_FILE), signatureProperties.getKeystoreFile());
        properties.put(addMerlinCryptoProviderPrefix(Merlin.TRUSTSTORE_TYPE), "jks");
        properties.put(addMerlinCryptoProviderPrefix(Merlin.TRUSTSTORE_PASSWORD), signatureProperties.getTruststorePassword());
        properties.put(addMerlinCryptoProviderPrefix(Merlin.TRUSTSTORE_FILE), signatureProperties.getTruststoreFile());
        return properties;
    }

    private static String addMerlinCryptoProviderPrefix(String property) {
        return Merlin.PREFIX + property;
    }

    private static void initializedP2pPolicyFeature(Client wsClient) throws ParserConfigurationException, SAXException, IOException {
        PolicyBuilder builder = wsClient.getBus().getExtension(PolicyBuilder.class);
        Policy policy = builder.getPolicy(NodeWebServiceProvider.class.getResourceAsStream(P2P_POLICY_FILE));
        WSPolicyFeature wsPolicyFeature = new WSPolicyFeature(policy);
        wsClient.getBus().getFeatures().add(wsPolicyFeature);
        wsPolicyFeature.initialize(wsClient, wsClient.getBus());
    }

    private static HTTPClientPolicy getHttpClient(Client endpointClient) {
        HTTPConduit bundleHttp = (HTTPConduit) endpointClient.getConduit();
        HTTPClientPolicy httpClientPolicy = bundleHttp.getClient();
        if (httpClientPolicy == null) {
            httpClientPolicy = new HTTPClientPolicy();
            bundleHttp.setClient(httpClientPolicy);
        }
        return httpClientPolicy;
    }
}
