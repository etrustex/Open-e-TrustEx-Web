package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.web.model.MessageFormBean;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EdmaMessage;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.ModelAndView;

import static eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler.EDMA_MESSAGE_ATTR;
import static eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler.MESSAGE_ATTR;
import static org.mockito.Mockito.*;


public class MessageCreateControllerTest extends AbstractControllerTest {
    @InjectMocks
    private MessageCreateController controller;

    @Mock
    private PartyPropertyEditor partyPropertyEditor;

    @Test
    public void test_getAction_should_returnMessageCreateAction() throws Exception {
        // DO THE ACTUAL CALL
        assertThat(controller.getAction(), is(equalTo(WebAction.MESSAGE_CREATE)));
    }

    @Test
    public void test_postProcessGet_should_addMandatoryObjectsToModel() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Message message = new Message();
        EdmaMessage edmaMessage = new EdmaMessage();
        modelAndView.addObject(MESSAGE_ATTR, message);
        modelAndView.addObject(EDMA_MESSAGE_ATTR, edmaMessage);

        // DO THE ACTUAL CALL
        controller.postProcessGet(modelAndView, null);

        assertThat(modelAndView.getModel().size(), is(3));
        MessageFormBean formBean = (MessageFormBean) modelAndView.getModelMap().get("formBean");
        assertThat(formBean, is(notNullValue()));
        assertThat(formBean.getMessage(), is(sameInstance(message)));
    }

    @Test
    public void test_initBinderAll_should_registerPartyCustomEditor() {
        WebDataBinder binder = mock(WebDataBinder.class);

        //DO THE ACTUAL CALL
        controller.initBinderAll(binder);

        verify(binder).registerCustomEditor(argThat(is(equalTo(Party.class))), argThat(is(sameInstance(partyPropertyEditor))));
        verifyNoMoreInteractions(binder);
    }
}
