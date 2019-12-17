package eu.europa.ec.etrustex.webaccess.web.view.business.api;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConfigurationProviderTest extends AbstractTest{
    @InjectMocks
    ConfigurationProvider configurationProvider;

    @Test
    public void test_isGenericViewEnabled_should_returnTrue_when_configurationAvailableAndGenericViewEnabled(){

        String viewName = "name";
        WebAction action = WebAction.MESSAGE_CREATE;

        Configuration configurationMock = mock(Configuration.class);
        when(configurationMock.isGenericViewEnabled(action)).thenReturn(true);

        HashMap<String, Configuration> configurations = new HashMap<>();
        configurations.put(viewName, configurationMock);
        configurationProvider.setConfigurations(configurations);

        //ACTUAL CALL
        boolean result = configurationProvider.isGenericViewEnabled(viewName, action);

        assertThat(result, is(true));
    }

    @Test
    public void test_isGenericViewEnabled_should_returnFalse_when_configurationAvailableAndGenericViewDisabled(){

        String viewName = "name";
        WebAction action = WebAction.MESSAGE_CREATE;

        Configuration configurationMock = mock(Configuration.class);
        when(configurationMock.isGenericViewEnabled(action)).thenReturn(false);

        HashMap<String, Configuration> configurations = new HashMap<>();
        configurations.put(viewName, configurationMock);
        configurationProvider.setConfigurations(configurations);

        //ACTUAL CALL
        boolean result = configurationProvider.isGenericViewEnabled(viewName, action);

        assertThat(result, is(false));
    }

    @Test
    public void test_isGenericViewEnabled_should_returnTrue_when_configurationIsNotAvailable(){

        String viewName = "name";
        WebAction action = WebAction.MESSAGE_CREATE;

        //ACTUAL CALL
        boolean result = configurationProvider.isGenericViewEnabled(viewName, action);

        assertThat(result, is(true));
    }

}
