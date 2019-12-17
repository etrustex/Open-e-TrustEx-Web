package eu.europa.ec.etrustex.webaccess.business.util;


import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.webservice.NodeWebServiceProvider;
import eu.europa.ec.etrustex.webaccess.webservice.model.ConnectionConfiguration;
import eu.europa.ec.etrustex.webaccess.webservice.model.P2PSignatureProperties;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class NodeConfigurationServiceTest extends AbstractTest {

    @Mock
    protected ConfigurationService configurationService;

    @Mock
    protected NodeWebServiceProvider nodeWebServiceProvider;

    @InjectMocks
    private NodeConfigurationService nodeConfigurationService;

    @Test
    public void testBuildNodeConfiguration_success() throws Exception {

        Configuration configuration = new Configuration();
        configuration.setDocumentWrapperServiceURI("Test DocumentWrapperServiceURL");
        configuration.setDocumentBundleServiceURI("Test DocumentBundleServiceURL");
        configuration.setInterchangeAgreementURI("Test DocumentICAServiceURL");
        configuration.setWebserviceLoggingEnabled(true);
        configuration.setNodeSecurityP2pEnabled(true);

        when(configurationService.getConfiguration()).thenReturn(configuration);

        ConnectionConfiguration nodeConnectionConfiguration = nodeConfigurationService.buildNodeConfiguration();

        verify(configurationService).getConfiguration();
        assertThat(nodeConnectionConfiguration, notNullValue());
        assertThat(nodeConnectionConfiguration.getDocumentWrapperServiceUrl(), is(equalTo("Test DocumentWrapperServiceURL")));
        assertThat(nodeConnectionConfiguration.getDocumentBundleServiceUrl(), is(equalTo("Test DocumentBundleServiceURL")));
        assertThat(nodeConnectionConfiguration.getInterchangeAgreementUrl(), is(equalTo("Test DocumentICAServiceURL")));
        assertThat(nodeConnectionConfiguration.isWebserviceLoggingEnabled(), is(true));
        assertThat(nodeConnectionConfiguration.isP2pEnabled(), is(true));
        verifyNoMoreInteractions(configurationService);

    }

    @Test
    public void testInitTransmissionConfigurations_success() throws Exception {

        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
        P2PSignatureProperties p2PSignatureProperties = mock(P2PSignatureProperties.class);

        NodeConfigurationService nodeConfigurationServiceSpy = spy(nodeConfigurationService);
        doReturn(connectionConfiguration).when(nodeConfigurationServiceSpy).buildNodeConfiguration();
        doReturn(p2PSignatureProperties).when(nodeConfigurationServiceSpy).buildP2PSignatureProperties();

        nodeConfigurationServiceSpy.initTransmissionConfigurations();

        verify(nodeWebServiceProvider).setConnectionConfiguration(argThat(sameInstance(connectionConfiguration)));
        verify(nodeWebServiceProvider).setP2PSignatureProperties(argThat(sameInstance(p2PSignatureProperties)));
        verifyNoMoreInteractions(nodeWebServiceProvider);

    }
}