package eu.europa.ec.etrustex.services.business.impl;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.persistence.PartyIcaDAO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class IcaServiceImplTest extends AbstractTest {

    @InjectMocks
    private IcaServiceImpl icaService = new IcaServiceImpl();

    @Mock
    private PartyIcaDAO partyIcaDAO;
    ;

    @Test
    public void testGetIcasByParty_Success() {
        Party party = new Party();
        List<PartyIca> receiverParties = new ArrayList();

        Party remoteParty1 = new Party();
        remoteParty1.setDisplayName("partyICA1");
        PartyIca partyIca1 = new PartyIca();
        partyIca1.setRemoteParty(remoteParty1);
        receiverParties.add(partyIca1);

        Party remoteParty2 = new Party();
        remoteParty2.setDisplayName("partyICA2");
        PartyIca partyIca2 = new PartyIca();
        partyIca2.setRemoteParty(remoteParty2);
        receiverParties.add(partyIca2);

        when(partyIcaDAO.getIcasByParty(argThat(is(party)))).thenReturn(receiverParties);

        //ACTUAL CALL
        List<PartyIca> expectedResultSet = icaService.getIcasByParty(party);

        verify(partyIcaDAO).getIcasByParty(argThat(is(party)));
        assertThat(expectedResultSet, notNullValue());
        assertThat(expectedResultSet.size(), is(2));
        assertThat(expectedResultSet, hasItem(partyIca1));
        assertThat(expectedResultSet, hasItem(partyIca2));
        verifyNoMoreInteractions(partyIcaDAO);
    }


    @Test
    public void testGetIcasByParty_NullList() {
        Party party = new Party();

        when(partyIcaDAO.getIcasByParty(argThat(is(party)))).thenReturn(null);

        //ACTUAL CALL
        List<PartyIca> expectedResultSet = icaService.getIcasByParty(party);

        verify(partyIcaDAO).getIcasByParty(argThat(is(party)));
        assertThat(expectedResultSet, nullValue());
        verifyNoMoreInteractions(partyIcaDAO);
    }

    @Test
    public void testGetIcasByParty_EmptyList() {
        Party party = new Party();
        List<PartyIca> receiverParties = new ArrayList();

        when(partyIcaDAO.getIcasByParty(argThat(is(party)))).thenReturn(receiverParties);

        //ACTUAL CALL
        List<PartyIca> expectedResultSet = icaService.getIcasByParty(party);

        verify(partyIcaDAO).getIcasByParty(argThat(is(party)));
        assertThat(expectedResultSet, notNullValue());
        assertThat(expectedResultSet.size(), is(0));
        verifyNoMoreInteractions(partyIcaDAO);
    }

}
