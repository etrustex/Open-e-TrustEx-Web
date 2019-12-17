package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.model.vo.PartyIcaVO;
import eu.europa.ec.etrustex.webaccess.persistence.PartyIcaDAO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class IcaManagerImplTest extends AbstractTest {

    @InjectMocks
    private IcaManagerImpl icaManager = new IcaManagerImpl();

    @Mock
    private PartyIcaDAO partyIcaDAO;

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
        List<PartyIca> expectedResultSet = icaManager.getIcasByParty(party);

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
        List<PartyIca> expectedResultSet = icaManager.getIcasByParty(party);

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
        List<PartyIca> expectedResultSet = icaManager.getIcasByParty(party);

        verify(partyIcaDAO).getIcasByParty(argThat(is(party)));
        assertThat(expectedResultSet, notNullValue());
        assertThat(expectedResultSet.size(), is(0));
        verifyNoMoreInteractions(partyIcaDAO);
    }

    @Test
    public void test_getIcasByActiveParties_should_callPartyIcaDAO() {
        //ACTUAL CALL
        icaManager.getIcasByActiveParties();

        verify(partyIcaDAO).getIcasByActiveParties();
    }

    @Test
    public void test_saveOrUpdate_when_noIdSet() {
        PartyIca partyIca = new PartyIca();
        Party party = new Party();
        partyIca.setParty(party);

        //ACTUAL CALL
        icaManager.saveOrUpdate(partyIca);

        verify(partyIcaDAO).save(partyIca);
        verifyNoMoreInteractions(partyIcaDAO);
    }

    @Test
    public void test_saveOrUpdate_when_idSet() {
        PartyIca partyIca = new PartyIca();
        partyIca.setId(1L);
        Party party = new Party();
        partyIca.setParty(party);

        //ACTUAL CALL
        icaManager.saveOrUpdate(partyIca);

        verify(partyIcaDAO).update(partyIca);
        verifyNoMoreInteractions(partyIcaDAO);
    }

    @Test
    public void testGetIcaBySenderReceiver_Success() {
        String sender = "sender";
        String receiver = "receiver";

        Party localParty = new Party();
        Party remoteParty = new Party();
        remoteParty.setNodeName(receiver);

        PartyIca partyIca = new PartyIca();
        partyIca.setParty(localParty);
        partyIca.setRemoteParty(remoteParty);
        when(partyIcaDAO.getIcaBySenderReceiver(argThat(is(sender)), argThat(is(receiver)))).thenReturn(partyIca);

        //ACTUAL CALL
        PartyIca expectedResult = icaManager.getIcaBySenderReceiver(sender, receiver);

        verify(partyIcaDAO).getIcaBySenderReceiver(argThat(is(sender)), argThat(is(receiver)));
        assertThat(expectedResult, notNullValue());
        assertThat(expectedResult.getRemoteParty().getNodeName(), is(receiver));
        assertThat(expectedResult.getParty(), is(localParty));
        verifyNoMoreInteractions(partyIcaDAO);
    }

    @Test
    public void testGetActiveIcaBySenderReceiver_Success() {
        String sender = "sender";
        String receiver = "receiver";

        Party localParty = new Party();
        Party remoteParty = new Party();
        remoteParty.setNodeName(receiver);

        PartyIca partyIca = new PartyIca();
        partyIca.setParty(localParty);
        partyIca.setRemoteParty(remoteParty);
        when(partyIcaDAO.getActiveIcaBySenderReceiver(argThat(is(sender)), argThat(is(receiver)))).thenReturn(partyIca);

        //ACTUAL CALL
        PartyIca expectedResult = icaManager.getActiveIcaBySenderReceiver(sender, receiver);

        verify(partyIcaDAO).getActiveIcaBySenderReceiver(argThat(is(sender)), argThat(is(receiver)));
        assertThat(expectedResult, notNullValue());
        assertThat(expectedResult.getRemoteParty().getNodeName(), is(receiver));
        assertThat(expectedResult.getParty(), is(localParty));
        verifyNoMoreInteractions(partyIcaDAO);
    }

    @Test
    public void test_getAllIcasSenderReceiver_Success() {
        String sender = "sender";
        String receiver = "receiver";

        PartyIcaVO partyIcaVO = new PartyIcaVO(1L, sender, receiver);
        List<PartyIcaVO> icas = new ArrayList();
        icas.add(partyIcaVO);

        when(partyIcaDAO.getAllIcasSenderReceiver()).thenReturn(icas);

        //ACTUAL CALL
        List<PartyIcaVO> expectedResult = icaManager.getAllIcasSenderReceiver();

        verify(partyIcaDAO).getAllIcasSenderReceiver();
        assertThat(expectedResult, notNullValue());
        assertThat(expectedResult.size(), is(1));
        assertThat(expectedResult, hasItem(partyIcaVO));
        verifyNoMoreInteractions(partyIcaDAO);
    }

    @Test
    public void testfindById_Success() {
        String receiver = "receiver";

        Party remoteParty = new Party();
        remoteParty.setNodeName(receiver);

        PartyIca partyIca = new PartyIca();
        partyIca.setRemoteParty(remoteParty);

        when(partyIcaDAO.findById(1L)).thenReturn(partyIca);

        //ACTUAL CALL
        PartyIca expectedResult = icaManager.findById(1L);

        verify(partyIcaDAO).findById(argThat(is(1L)));
        assertThat(expectedResult, notNullValue());
        assertThat(expectedResult.getRemoteParty().getNodeName(), is(receiver));
        verifyNoMoreInteractions(partyIcaDAO);
    }

    @Test
    public void testfindById_Null() {
        when(partyIcaDAO.findById(1L)).thenReturn(null);

        //ACTUAL CALL
        PartyIca expectedResult = icaManager.findById(1L);

        verify(partyIcaDAO).findById(argThat(is(1L)));
        assertThat(expectedResult, nullValue());
        verifyNoMoreInteractions(partyIcaDAO);
    }

    @Test
    public void testIcaExists_Success() {
        String sender = "sender";
        String receiver = "receiver";

        Party localParty = new Party();
        Party remoteParty = new Party();
        remoteParty.setNodeName(receiver);

        PartyIca partyIca = new PartyIca();
        partyIca.setParty(localParty);
        partyIca.setRemoteParty(remoteParty);
        when(partyIcaDAO.icaExists(argThat(is(sender)), argThat(is(receiver)))).thenReturn(Boolean.TRUE);

        //ACTUAL CALL
        boolean result = icaManager.icaExists(sender, receiver);

        verify(partyIcaDAO).icaExists(argThat(is(sender)), argThat(is(receiver)));
        assertThat(result, is(true));
        verifyNoMoreInteractions(partyIcaDAO);
    }

}
