package eu.europa.ec.etrustex.webaccess.web.view.business.common;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfigurationImplTest extends AbstractTest {

    @InjectMocks
    ConfigurationImpl configurationImpl = new ConfigurationImpl();

    @Test
    public void test_isGenericViewEnabled_should_returnTrue_when_suchActionHasGenericViewRegistered(){

        WebAction action = WebAction.MESSAGE_CREATE_WS;

        ArrayList<String> genericViews = new ArrayList<>();
        genericViews.add(action.getName());

        configurationImpl.setGenericViews(genericViews);

        //ACTUAL CALL
        boolean result = configurationImpl.isGenericViewEnabled(action);

        assertThat(result, is(true));
    }

    @Test
    public void test_isGenericViewEnabled_should_returnFalse_when_suchActionHasNoGenericViewRegistered(){

        WebAction action = WebAction.MESSAGE_CREATE_WS;

        ArrayList<String> genericViews = new ArrayList<>();

        configurationImpl.setGenericViews(genericViews);

        //ACTUAL CALL
        boolean result = configurationImpl.isGenericViewEnabled(action);

        assertThat(result, is(false));
    }


    @Test
    public void test_getCustomViewLabel_should_returnLabel_when_labelAvailable(){

        WebAction action = WebAction.MESSAGE_CREATE_WS;
        String label = "label";

        HashMap<String, String> customViewLabels = new HashMap<>();
        customViewLabels.put(action.getName(), label);
        configurationImpl.setCustomViewLabels(customViewLabels);

        //ACTUAL CALL
        String result = configurationImpl.getCustomViewLabel(action);

        assertThat(result, is(label));
    }

    @Test
    public void test_getCustomViewLabel_should_returnNull_when_labelIsNotAvailable(){

        WebAction action = WebAction.MESSAGE_CREATE_WS;

        //ACTUAL CALL
        String result = configurationImpl.getCustomViewLabel(action);

        assertThat(result, is(nullValue()));
    }
}
