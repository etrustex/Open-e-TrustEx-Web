package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.model.Party;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

/**
 * @author: micleva
 * @date: 10/2/13 11:18 AM
 * @project: ETX
 */
public class PartyPropertyEditorTest extends AbstractControllerTest {

    @Mock
    private PartyManager partyManager;

    @InjectMocks
    private PartyPropertyEditor partyPropertyEditor = new PartyPropertyEditor();

    @Test
    public void test_getAsText_should_returnRefId_when_partyExists() {
        Party party = new Party();
        party.setNodeName("ref__");

        PartyPropertyEditor localPropertyEditor = spy(partyPropertyEditor);
        when(localPropertyEditor.getValue()).thenReturn(party);

        //DO THE ACTUAL CALL
        String text = localPropertyEditor.getAsText();

        assertThat(text, is(notNullValue()));
        assertThat(text, is("ref__"));

        verify(localPropertyEditor).getAsText();
        verify(localPropertyEditor).getValue();
        verifyNoMoreInteractions(localPropertyEditor, partyManager);
    }

    @Test
    public void test_setAsText_should_setCorrectValue_when_partyFound() {
        Party party = new Party();
        String partyRefId = "a ref id";

        when(partyManager.getWebManagedPartyByNodeName(partyRefId)).thenReturn(party);
        PartyPropertyEditor localPropertyEditor = spy(partyPropertyEditor);
        doNothing().when(localPropertyEditor).setValue(party);

        //DO THE ACTUAL CALL
        localPropertyEditor.setAsText(partyRefId);

        verify(localPropertyEditor).setAsText(partyRefId);
        verify(localPropertyEditor).setValue(argThat(sameInstance(party)));
        verify(partyManager).getWebManagedPartyByNodeName(partyRefId);
        verifyNoMoreInteractions(localPropertyEditor, partyManager);
    }
}
