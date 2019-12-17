package eu.europa.ec.etrustex.webaccess.business.security;


import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaKey;
import eu.europa.ec.etrustex.webaccess.model.vo.PartyIcaVO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.PartyDAO;
import eu.europa.ec.etrustex.webaccess.persistence.UserDAO;
import eu.europa.ec.etrustex.webaccess.webservice.ica.IcaService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import sun.security.x509.X509CertImpl;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.*;

public class IcaDetailsServiceImplTest extends AbstractTest {

    @Mock
    private IcaService icaService;

    @Mock
    private PartyDAO partyDAO;

    @Mock
    private MessageDAO messageDAO;

    @Mock
    private PartyManager partyManager;

    @Mock
    private IcaManager icaManager;

    @InjectMocks
    private IcaDetailsServiceImpl icaDetailsService = new IcaDetailsServiceImpl();

    @Mock
    private UserSessionContext userSessionContext;

    @Mock
    private UserDAO userDAO;

    @Override
    protected void onSetUp() {
        super.onSetUp();
        icaDetailsService.executor = Executors.newFixedThreadPool(IcaService.MAX_CONCURRENT_TRANSMISSIONS);
        icaDetailsService.completionService = new ExecutorCompletionService<>(icaDetailsService.executor);
    }

    @Test
    public void test_getNodeIcaDetails_should_retrieveIcaFromNode_when_DDBBEmpty() {
        String partySender = "Asender";

        Party aParty = new Party();
        Business business = new Business();
        aParty.setBusiness(business);
        aParty.setNodeName(partySender);
        aParty.setNodeUserPass("userName");
        aParty.setNodeUserPass("pass");

        String partyReceiver = "Breceiver";

        List<NodeIcaDetails> mockedNodeIcaDetailsList = new ArrayList<>();
        NodeIcaDetails mockedNodeIcaDetails = mock(NodeIcaDetails.class);
        when(mockedNodeIcaDetails.getLocalParty()).thenReturn(partySender);
        when(mockedNodeIcaDetails.getRemoteParty()).thenReturn(partyReceiver);
        when(mockedNodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.CRITICAL);
        when(mockedNodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.LIMITED_BASIC);
        mockedNodeIcaDetailsList.add(mockedNodeIcaDetails);
        when(icaService.retrieveICADetails(aParty.getNodeUserName(), aParty.getNodeUserPass(), "Asender", "Asender", "Breceiver")).thenReturn(mockedNodeIcaDetailsList);

        when(icaManager.getIcaBySenderReceiver(partySender, partyReceiver)).thenReturn(null);
        when(partyManager.getWebManagedPartyByNodeName(partySender)).thenReturn(aParty);

        //DO THE ACTUAL CALL
        NodeIcaDetails nodeIcaDetails = icaDetailsService.getIcaDetails(aParty, partyReceiver);

        assertThat(nodeIcaDetails, is(notNullValue()));
        assertThat(nodeIcaDetails.getLocalParty(), is(partySender));
        assertThat(nodeIcaDetails.getRemoteParty(), is(partyReceiver));
        assertThat(nodeIcaDetails.getIntegrityCode(), is(IntegrityCode.CRITICAL));
        assertThat(nodeIcaDetails.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_BASIC));

        verify(icaService).retrieveICADetails(aParty.getNodeUserName(), aParty.getNodeUserPass(), "Asender", "Asender", "Breceiver");
        verifyNoMoreInteractions(icaService);
    }

    @Test
    public void test_getNodeIcaDetails_should_retrieveFromDDBB_when_DDBBExists() {
        String partySender = "Breceiver";

        Party aParty = new Party();
        aParty.setNodeName(partySender);
        aParty.setNodeUserPass("userName");
        aParty.setNodeUserPass("pass");

        String partyReceiver = "Asender";

        Party localParty = new Party();
        localParty.setNodeName(partySender);
        Party remoteParty = new Party();
        remoteParty.setNodeName(partyReceiver);
        PartyIca partyIca = new PartyIca();
        partyIca.setParty(localParty);
        partyIca.setRemoteParty(remoteParty);
        partyIca.setIntegrityCode(IntegrityCode.CRITICAL.getCode());
        partyIca.setConfidentialityCode(ConfidentialityCode.LIMITED_BASIC.getCode());

        when(icaManager.getIcaBySenderReceiver(aParty.getNodeName(), partyReceiver)).thenReturn(partyIca);

        // DO THE ACTUAL CALL
        NodeIcaDetails nodeIcaDetails = icaDetailsService.getIcaDetails(aParty, partyReceiver);

        verify(icaManager).getIcaBySenderReceiver(aParty.getNodeName(), partyReceiver);
        verifyNoMoreInteractions(icaManager);

        assertThat(nodeIcaDetails, is(notNullValue()));
        assertThat(nodeIcaDetails.getLocalParty(), is(partySender));
        assertThat(nodeIcaDetails.getRemoteParty(), is(partyReceiver));
        assertThat(nodeIcaDetails.getIntegrityCode(), is(IntegrityCode.CRITICAL));
        assertThat(nodeIcaDetails.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_BASIC));

        verifyZeroInteractions(icaService);
    }

    @Test
    public void testLoadAllIcaDetails_should_not_update_icas() {
        String receiverPartyName = "Breceiver";
        String senderPartyName = "Asender";

        Party party = new Party();
        party.setNodeName(senderPartyName);
        party.setNodeUserName("userName");
        party.setNodeUserPass("pass");

        List<Party> parties = new ArrayList<>();
        parties.add(party);

        when(partyDAO.getAllParties()).thenReturn(parties);

        List<NodeIcaDetails> mockedNodeIcaDetailsList = new ArrayList<>();
        NodeIcaDetails mockedNodeIcaDetails = mock(NodeIcaDetails.class);
        when(mockedNodeIcaDetails.getLocalParty()).thenReturn(senderPartyName);
        when(mockedNodeIcaDetails.getRemoteParty()).thenReturn(receiverPartyName);
        when(mockedNodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.CRITICAL);
        when(mockedNodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.LIMITED_BASIC);
        mockedNodeIcaDetailsList.add(mockedNodeIcaDetails);

        when(icaService.retrieveICADetails(party.getNodeUserName(), party.getNodeUserPass(), senderPartyName, senderPartyName, null)).thenReturn(mockedNodeIcaDetailsList);

        PartyIca partyIca = new PartyIca();
        partyIca.setIntegrityCode(IntegrityCode.CRITICAL.getCode());
        partyIca.setConfidentialityCode(ConfidentialityCode.LIMITED_BASIC.getCode());
        partyIca.setCertificate(null);
        when(icaManager.getIcaBySenderReceiver(senderPartyName, receiverPartyName)).thenReturn(partyIca);

        // DO THE ACTUAL CALL
        Map<NodeIcaKey, NodeIcaDetails> map = icaDetailsService.loadAllIcaDetails();

        verify(icaManager).getIcaBySenderReceiver(senderPartyName, receiverPartyName);
        verify(icaManager).getAllIcasSenderReceiver();
        verify(icaManager).getIcasByActiveParties();
        verifyNoMoreInteractions(icaManager);

        verify(icaService).retrieveICADetails(party.getNodeUserName(), party.getNodeUserPass(), senderPartyName, senderPartyName, null);
        verifyNoMoreInteractions(icaService);
    }

    @Test
    public void testLoadAllIcaDetails_should_update_icas() {
        String receiverPartyName = "Breceiver";
        String senderPartyName = "Asender";

        Party party = new Party();
        party.setNodeName(senderPartyName);
        party.setNodeUserName("userName");
        party.setNodeUserPass("pass");

        List<Party> parties = new ArrayList<>();
        parties.add(party);

        when(partyDAO.getAllParties()).thenReturn(parties);

        List<NodeIcaDetails> mockedNodeIcaDetailsList = new ArrayList<>();
        NodeIcaDetails mockedNodeIcaDetails = mock(NodeIcaDetails.class);
        when(mockedNodeIcaDetails.getLocalParty()).thenReturn(senderPartyName);
        when(mockedNodeIcaDetails.getRemoteParty()).thenReturn(receiverPartyName);
        when(mockedNodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.CRITICAL);
        when(mockedNodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.LIMITED_BASIC);
        mockedNodeIcaDetailsList.add(mockedNodeIcaDetails);

        when(icaService.retrieveICADetails(party.getNodeUserName(), party.getNodeUserPass(), senderPartyName, senderPartyName, null)).thenReturn(mockedNodeIcaDetailsList);

        PartyIca partyIca = new PartyIca();
        partyIca.setIntegrityCode(IntegrityCode.CRITICAL.getCode());
        partyIca.setConfidentialityCode(ConfidentialityCode.LIMITED_HIGH.getCode());
        partyIca.setCertificate(null);
        when(icaManager.getIcaBySenderReceiver(senderPartyName, receiverPartyName)).thenReturn(partyIca);

        // DO THE ACTUAL CALL
        Map<NodeIcaKey, NodeIcaDetails> map = icaDetailsService.loadAllIcaDetails();

        verify(icaManager).getIcaBySenderReceiver(senderPartyName, receiverPartyName);

        ArgumentCaptor<PartyIca> argumentCaptor = ArgumentCaptor.forClass(PartyIca.class);
        verify(icaManager).saveOrUpdate(argumentCaptor.capture());
        PartyIca argument = argumentCaptor.getValue();
        assertThat(argument.getIntegrityCode(), is(IntegrityCode.CRITICAL.getCode()));
        assertThat(argument.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_BASIC.getCode()));
        assertThat(argument.getCertificate(), is(nullValue()));

        verify(icaManager).getAllIcasSenderReceiver();
        verify(icaManager).getIcasByActiveParties();
        verifyNoMoreInteractions(icaManager);

        verify(icaService).retrieveICADetails(party.getNodeUserName(), party.getNodeUserPass(), senderPartyName, senderPartyName, null);
        verifyNoMoreInteractions(icaService);
    }

    @Test
    public void testLoadAllIcaDetails_should_insert_icas() {
        String receiverPartyName = "Breceiver";
        String senderPartyName = "Asender";

        Business business = new Business();
        business.setId(1L);
        Party party = new Party();
        party.setNodeName(senderPartyName);
        party.setNodeUserName("userName");
        party.setNodeUserPass("pass");
        party.setBusiness(business);

        Party party2 = new Party();
        party2.setNodeName(receiverPartyName);
        party2.setDisplayName(receiverPartyName);

        List<Party> parties = new ArrayList<>();
        parties.add(party);

        when(partyDAO.getAllParties()).thenReturn(parties);

        List<NodeIcaDetails> mockedNodeIcaDetailsList = new ArrayList<>();
        NodeIcaDetails mockedNodeIcaDetails = mock(NodeIcaDetails.class);
        when(mockedNodeIcaDetails.getLocalParty()).thenReturn(senderPartyName);
        when(mockedNodeIcaDetails.getRemoteParty()).thenReturn(receiverPartyName);
        when(mockedNodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.CRITICAL);
        when(mockedNodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.LIMITED_BASIC);
        mockedNodeIcaDetailsList.add(mockedNodeIcaDetails);

        when(icaService.retrieveICADetails(party.getNodeUserName(), party.getNodeUserPass(), senderPartyName, senderPartyName, null)).thenReturn(mockedNodeIcaDetailsList);

        when(icaManager.getIcaBySenderReceiver(senderPartyName, receiverPartyName)).thenReturn(null);
        when(partyManager.getWebManagedPartyByNodeName(senderPartyName)).thenReturn(party);
        when(partyManager.getRemotePartyByNodeNameBusId(receiverPartyName, business.getId())).thenReturn(party2);


        // DO THE ACTUAL CALL
        Map<NodeIcaKey, NodeIcaDetails> map = icaDetailsService.loadAllIcaDetails();

        verify(icaManager).getIcaBySenderReceiver(senderPartyName, receiverPartyName);

        ArgumentCaptor<PartyIca> argumentCaptor = ArgumentCaptor.forClass(PartyIca.class);
        verify(icaManager).saveOrUpdate(argumentCaptor.capture());
        PartyIca argument = argumentCaptor.getValue();
        assertThat(argument.getRemoteParty().getNodeName(), is(receiverPartyName));
        assertThat(argument.getRemoteParty().getDisplayName(), is(receiverPartyName));
        assertThat(argument.getIntegrityCode(), is(IntegrityCode.CRITICAL.getCode()));
        assertThat(argument.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_BASIC.getCode()));
        assertThat(argument.getCertificate(), is(nullValue()));

        verify(icaManager).getAllIcasSenderReceiver();
        verify(icaManager).getIcasByActiveParties();
        verifyNoMoreInteractions(icaManager);

        verify(partyManager).getWebManagedPartyByNodeName(senderPartyName);
        verify(partyManager).getRemotePartyByNodeNameBusId(receiverPartyName, business.getId());
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void testLoadAllIcaDetails_should_disable_ica() {
        String receiverPartyName = "Breceiver";
        String receiverPartyName2 = "Breceiver2";
        String senderPartyName = "Asender";

        Party party = new Party();
        party.setNodeName(senderPartyName);
        party.setNodeUserName("userName");
        party.setNodeUserPass("pass");

        List<Party> parties = new ArrayList<>();
        parties.add(party);

        when(partyDAO.getAllParties()).thenReturn(parties);

        List<NodeIcaDetails> mockedNodeIcaDetailsList = new ArrayList<>();
        NodeIcaDetails mockedNodeIcaDetails = mock(NodeIcaDetails.class);
        when(mockedNodeIcaDetails.getLocalParty()).thenReturn(senderPartyName);
        when(mockedNodeIcaDetails.getRemoteParty()).thenReturn(receiverPartyName);
        when(mockedNodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.CRITICAL);
        when(mockedNodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.LIMITED_BASIC);
        mockedNodeIcaDetailsList.add(mockedNodeIcaDetails);

        when(icaService.retrieveICADetails(party.getNodeUserName(), party.getNodeUserPass(), senderPartyName, senderPartyName, null)).thenReturn(mockedNodeIcaDetailsList);

        Party remoteParty1 = new Party();
        remoteParty1.setNodeName(receiverPartyName);
        PartyIca partyIca1 = new PartyIca();
        partyIca1.setParty(party);
        partyIca1.setRemoteParty(remoteParty1);
        partyIca1.setIntegrityCode(IntegrityCode.CRITICAL.getCode());
        partyIca1.setConfidentialityCode(ConfidentialityCode.LIMITED_BASIC.getCode());
        partyIca1.setCertificate(null);

        Party remoteParty2 = new Party();
        remoteParty2.setNodeName(receiverPartyName2);
        PartyIca partyIca2 = new PartyIca();
        partyIca2.setParty(party);
        partyIca2.setRemoteParty(remoteParty2);
        partyIca2.setIntegrityCode(IntegrityCode.CRITICAL.getCode());
        partyIca2.setConfidentialityCode(ConfidentialityCode.LIMITED_BASIC.getCode());
        partyIca2.setCertificate(null);

        List<PartyIcaVO> partyIcasVO = new ArrayList<>();
        PartyIcaVO partyIcaVO1 = new PartyIcaVO(1L, senderPartyName, receiverPartyName);
        partyIcasVO.add(partyIcaVO1);
        PartyIcaVO partyIcaVO2 = new PartyIcaVO(2L, senderPartyName, receiverPartyName2);
        partyIcasVO.add(partyIcaVO2);

        when(icaManager.getIcaBySenderReceiver(senderPartyName, receiverPartyName)).thenReturn(partyIca1);
        when(icaManager.getAllIcasSenderReceiver()).thenReturn(partyIcasVO);
        when(icaManager.findById(2L)).thenReturn(partyIca2);

        // DO THE ACTUAL CALL
        Map<NodeIcaKey, NodeIcaDetails> map = icaDetailsService.loadAllIcaDetails();

        verify(icaManager).getIcaBySenderReceiver(senderPartyName, receiverPartyName);
        verify(icaManager).getAllIcasSenderReceiver();
        verify(icaManager).findById(2L);
        verify(icaManager).getIcasByActiveParties();

        ArgumentCaptor<PartyIca> argumentCaptor = ArgumentCaptor.forClass(PartyIca.class);
        verify(icaManager).saveOrUpdate(argumentCaptor.capture());
        PartyIca argument = argumentCaptor.getValue();
        assertThat(argument.getRemoteParty().getNodeName(), is(receiverPartyName2));
        assertThat(argument.getIntegrityCode(), is(IntegrityCode.CRITICAL.getCode()));
        assertThat(argument.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_BASIC.getCode()));
        assertThat(argument.getCertificate(), is(nullValue()));
        assertThat(argument.getActiveState(), is(false));

        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void testLoadIcaDetailsSenderReceiver_should_not_update_ica() {
        String senderPartyName = "Asender";
        String receiverPartyName = "Breceiver";

        Party party = new Party();
        party.setNodeName(senderPartyName);
        party.setNodeUserName("userName");
        party.setNodeUserPass("pass");

        List<NodeIcaDetails> mockedNodeIcaDetailsList = new ArrayList<>();
        NodeIcaDetails mockedNodeIcaDetails = mock(NodeIcaDetails.class);
        when(mockedNodeIcaDetails.getLocalParty()).thenReturn(senderPartyName);
        when(mockedNodeIcaDetails.getRemoteParty()).thenReturn(receiverPartyName);
        when(mockedNodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.CRITICAL);
        when(mockedNodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.LIMITED_BASIC);
        mockedNodeIcaDetailsList.add(mockedNodeIcaDetails);

        when(icaService.retrieveICADetails(party.getNodeUserName(), party.getNodeUserPass(), party.getNodeName(), senderPartyName, receiverPartyName)).thenReturn(mockedNodeIcaDetailsList);
        when(partyDAO.getWebManagedPartyByNodeName(senderPartyName)).thenReturn(party);

        Party localParty = new Party();
        localParty.setNodeName(senderPartyName);
        Party remoteParty = new Party();
        remoteParty.setNodeName(receiverPartyName);
        PartyIca partyIca = new PartyIca();
        partyIca.setParty(localParty);
        partyIca.setRemoteParty(remoteParty);
        partyIca.setIntegrityCode(IntegrityCode.CRITICAL.getCode());
        partyIca.setConfidentialityCode(ConfidentialityCode.LIMITED_BASIC.getCode());
        partyIca.setCertificate(null);
        when(icaManager.getIcaBySenderReceiver(senderPartyName, receiverPartyName)).thenReturn(partyIca);

        // DO THE ACTUAL CALL
        NodeIcaDetails nodeIcaDetails = icaDetailsService.loadIcaDetails(senderPartyName, receiverPartyName);

        verify(icaManager, times(2)).getIcaBySenderReceiver(senderPartyName, receiverPartyName);
        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void testLoadIcaDetailsSenderReceiver_should_update_ica() {
        String senderPartyName = "Asender";
        String receiverPartyName = "Breceiver";
        X509Certificate cert = new X509CertImpl();

        Party party = new Party();
        party.setNodeName(senderPartyName);
        party.setNodeUserName("userName");
        party.setNodeUserPass("pass");

        List<NodeIcaDetails> mockedNodeIcaDetailsList = new ArrayList<>();
        NodeIcaDetails mockedNodeIcaDetails = mock(NodeIcaDetails.class);
        when(mockedNodeIcaDetails.getLocalParty()).thenReturn(senderPartyName);
        when(mockedNodeIcaDetails.getRemoteParty()).thenReturn(receiverPartyName);
        when(mockedNodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.CRITICAL);
        when(mockedNodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.LIMITED_BASIC);
        mockedNodeIcaDetailsList.add(mockedNodeIcaDetails);

        when(icaService.retrieveICADetails(party.getNodeUserName(), party.getNodeUserPass(), party.getNodeName(), senderPartyName, receiverPartyName)).thenReturn(mockedNodeIcaDetailsList);
        when(partyDAO.getWebManagedPartyByNodeName(senderPartyName)).thenReturn(party);

        Party localParty = new Party();
        localParty.setNodeName(senderPartyName);
        Party remoteParty = new Party();
        remoteParty.setNodeName(receiverPartyName);
        PartyIca partyIca = new PartyIca();
        partyIca.setParty(localParty);
        partyIca.setRemoteParty(remoteParty);
        partyIca.setIntegrityCode(IntegrityCode.CRITICAL.getCode());
        partyIca.setConfidentialityCode(ConfidentialityCode.LIMITED_HIGH.getCode());
        partyIca.setCertificate(null);
        when(icaManager.getIcaBySenderReceiver(senderPartyName, receiverPartyName)).thenReturn(partyIca);

        // DO THE ACTUAL CALL
        NodeIcaDetails nodeIcaDetails = icaDetailsService.loadIcaDetails(senderPartyName, receiverPartyName);

        verify(icaManager, times(2)).getIcaBySenderReceiver(senderPartyName, receiverPartyName);

        ArgumentCaptor<PartyIca> argumentCaptor = ArgumentCaptor.forClass(PartyIca.class);
        verify(icaManager).saveOrUpdate(argumentCaptor.capture());
        PartyIca argument = argumentCaptor.getValue();
        assertThat(argument.getIntegrityCode(), is(IntegrityCode.CRITICAL.getCode()));
        assertThat(argument.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_BASIC.getCode()));
        assertThat(argument.getCertificate(), is(nullValue()));

        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void testLoadIcaDetailsSenderReceiver_should_insert_ica() {
        String senderPartyName = "Asender";
        String receiverPartyName = "Breceiver";
        X509Certificate cert = new X509CertImpl();

        Business business = new Business();
        business.setId(1L);
        Party party = new Party();
        party.setNodeName(senderPartyName);
        party.setNodeUserName("userName");
        party.setNodeUserPass("pass");
        party.setBusiness(business);

        List<NodeIcaDetails> mockedNodeIcaDetailsList = new ArrayList<>();
        NodeIcaDetails mockedNodeIcaDetails = mock(NodeIcaDetails.class);
        when(mockedNodeIcaDetails.getLocalParty()).thenReturn(senderPartyName);
        when(mockedNodeIcaDetails.getRemoteParty()).thenReturn(receiverPartyName);
        when(mockedNodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.CRITICAL);
        when(mockedNodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.LIMITED_BASIC);
        mockedNodeIcaDetailsList.add(mockedNodeIcaDetails);

        when(icaService.retrieveICADetails(party.getNodeUserName(), party.getNodeUserPass(), party.getNodeName(), senderPartyName, receiverPartyName)).thenReturn(mockedNodeIcaDetailsList);
        when(partyDAO.getWebManagedPartyByNodeName(senderPartyName)).thenReturn(party);

        when(icaManager.getIcaBySenderReceiver(senderPartyName, receiverPartyName)).thenReturn(null);

        Party remoteParty = new Party();
        remoteParty.setNodeName(receiverPartyName);
        remoteParty.setDisplayName(receiverPartyName);

        PartyIca expectedICA = new PartyIca();
        expectedICA.setRemoteParty(remoteParty);
        expectedICA.setIntegrityCode(IntegrityCode.CRITICAL.getCode());
        expectedICA.setConfidentialityCode(ConfidentialityCode.LIMITED_BASIC.getCode());

        when(partyManager.getWebManagedPartyByNodeName(senderPartyName)).thenReturn(party);
        when(partyManager.getRemotePartyByNodeNameBusId(receiverPartyName, business.getId())).thenReturn(remoteParty);

        // DO THE ACTUAL CALL
        NodeIcaDetails nodeIcaDetails = icaDetailsService.loadIcaDetails(senderPartyName, receiverPartyName);

        verify(icaManager, times(2)).getIcaBySenderReceiver(senderPartyName, receiverPartyName);

        ArgumentCaptor<PartyIca> argumentCaptor = ArgumentCaptor.forClass(PartyIca.class);
        verify(icaManager).saveOrUpdate(argumentCaptor.capture());
        PartyIca argument = argumentCaptor.getValue();
        assertThat(argument.getRemoteParty().getNodeName(), is(receiverPartyName));
        assertThat(argument.getRemoteParty().getDisplayName(), is(receiverPartyName));
        assertThat(argument.getIntegrityCode(), is(IntegrityCode.CRITICAL.getCode()));
        assertThat(argument.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_BASIC.getCode()));
        assertThat(argument.getCertificate(), is(nullValue()));

        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void testLoadIcaDetailsSenderReceiver_should_disable_icas() {
        String senderPartyName = "Asender";
        String receiverPartyName = "Breceiver";

        Party party = new Party();
        party.setNodeName(senderPartyName);
        party.setNodeUserName("userName");
        party.setNodeUserPass("pass");

        List<NodeIcaDetails> nodeIcaDetailsList = new ArrayList<>();

        when(icaService.retrieveICADetails(party.getNodeUserName(), party.getNodeUserPass(), party.getNodeName(), senderPartyName, receiverPartyName)).thenReturn(nodeIcaDetailsList);
        when(partyDAO.getWebManagedPartyByNodeName(senderPartyName)).thenReturn(party);

        Party localParty = new Party();
        localParty.setNodeName(senderPartyName);
        Party remoteParty = new Party();
        remoteParty.setNodeName(receiverPartyName);
        PartyIca partyIca = new PartyIca();
        partyIca.setParty(localParty);
        partyIca.setRemoteParty(remoteParty);
        partyIca.setIntegrityCode(IntegrityCode.CRITICAL.getCode());
        partyIca.setConfidentialityCode(ConfidentialityCode.LIMITED_BASIC.getCode());
        partyIca.setCertificate(null);
        when(icaManager.getIcaBySenderReceiver(senderPartyName, receiverPartyName)).thenReturn(partyIca);
        when(icaManager.getActiveIcaBySenderReceiver(senderPartyName, receiverPartyName)).thenReturn(partyIca);

        // DO THE ACTUAL CALL
        NodeIcaDetails nodeIcaDetails = icaDetailsService.loadIcaDetails(senderPartyName, receiverPartyName);

        verify(icaManager).getIcaBySenderReceiver(senderPartyName, receiverPartyName);
        verify(icaManager).getActiveIcaBySenderReceiver(senderPartyName, receiverPartyName);

        ArgumentCaptor<PartyIca> argumentCaptor = ArgumentCaptor.forClass(PartyIca.class);
        verify(icaManager).saveOrUpdate(argumentCaptor.capture());
        PartyIca argument = argumentCaptor.getValue();
        assertThat(argument.getParty().getNodeName(), is(senderPartyName));
        assertThat(argument.getRemoteParty().getNodeName(), is(receiverPartyName));
        assertThat(argument.getIntegrityCode(), is(IntegrityCode.CRITICAL.getCode()));
        assertThat(argument.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_BASIC.getCode()));
        assertThat(argument.getCertificate(), is(nullValue()));

        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void testgetNodeIcaDetailsBySenderReceiver_not_extended_certificate() {
        String dbCertificateData = "{" +
                "   \"publicKey\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2LgOwEk0H6IWxaoJqCwVYgbU4585Y8B7uTrSZjtSDwUBcVTNMdMI8OoRe+ZlJvkhYnIc8777iqgZx3UMl/7TRj4SKg21ND4gUc3o3k74lmpNLWT9bASrCMSezjNgphd3wxkKeNbzkZNAtZmmV3FknfwZzAyxZudMoGSyly2TVwhcqCMinTpUCJJgksC2nJ8Swbh17Wy1esofi/tqBk5vZKoeWxk0HXJA0idQ4MDm4rlJpczcqVOZcOEKmrcjMmC8LM0FFMktr/qYUz9NfkfKV0XrffQOtRXFf0GyQQUYN9sA97bV+Hg3bF6CIv//e86BWUgUFB2P6gqrx5nHKpeL5wIDAQAB\",\n" +
                "   \"publicKeyAlgorithm\":\"RSA\"," +
                "   \"startDate\":\"13-03-2017 02:42:09 PM +0100\"," +
                "   \"endDate\":\"13-03-2017 02:42:09 PM +0100\"," +
                "   \"subjectDn\":\"subject DN\"," +
                "   \"signatureAlgorithm\":\"a sig ALG\"," +
                "   \"issuerDn\":\"issuer DN\"," +
                "   \"version\":\"a version\"," +
                "   \"serialNumber\":\"serial NR\"" +
                "}";

        Party localParty = new Party();
        localParty.setNodeName("localParty");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");

        PartyIca partyIca = new PartyIca();
        partyIca.setParty(localParty);
        partyIca.setRemoteParty(remoteParty);
        partyIca.setIntegrityCode(IntegrityCode.MODERATE.getCode());
        partyIca.setConfidentialityCode(ConfidentialityCode.LIMITED_HIGH.getCode());
        Date date = new Date();
        partyIca.setUpdatedOn(date);
        partyIca.setActiveState(true);
        partyIca.setCertificate(dbCertificateData);

        // DO THE ACTUAL CALL
        NodeIcaDetails nodeIcaDetails = icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, false);

        assertThat(nodeIcaDetails.getLocalParty(), is(partyIca.getParty().getNodeName()));
        assertThat(nodeIcaDetails.getRemoteParty(), is(partyIca.getRemoteParty().getNodeName()));
        assertThat(nodeIcaDetails.getIntegrityCode(), is(IntegrityCode.MODERATE));
        assertThat(nodeIcaDetails.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_HIGH));
        assertThat(nodeIcaDetails.getCreationDate(), is(partyIca.getUpdatedOn()));
        assertThat(nodeIcaDetails.isActiveState(), is(partyIca.getActiveState()));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getPublicKey(), nullValue());
        assertThat(nodeIcaDetails.getExtendedCertificateData().getIssuerDN(), is("issuer DN"));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getSubjectDN(), is("subject DN"));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getSignatureAlgorithm(), is("a sig ALG"));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getVersion(), is("a version"));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getSerialNumber(), is("serial NR"));
    }

    @Test
    public void testgetNodeIcaDetailsBySenderReceiver_extended_certificate() throws Exception {
        byte[] keyValue = new byte[]{48, -126, 1, 34, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1,
                15, 0, 48, -126, 1, 10, 2, -126, 1, 1, 0, -40, -72, 14, -64, 73, 52, 31, -94, 22, -59, -86, 9, -88, 44, 21,
                98, 6, -44, -29, -97, 57, 99, -64, 123, -71, 58, -46, 102, 59, 82, 15, 5, 1, 113, 84, -51, 49, -45, 8, -16,
                -22, 17, 123, -26, 101, 38, -7, 33, 98, 114, 28, -13, -66, -5, -118, -88, 25, -57, 117, 12, -105, -2, -45, 70,
                62, 18, 42, 13, -75, 52, 62, 32, 81, -51, -24, -34, 78, -8, -106, 106, 77, 45, 100, -3, 108, 4, -85, 8,
                -60, -98, -50, 51, 96, -90, 23, 119, -61, 25, 10, 120, -42, -13, -111, -109, 64, -75, -103, -90, 87, 113,
                100, -99, -4, 25, -52, 12, -79, 102, -25, 76, -96, 100, -78, -105, 45, -109, 87, 8, 92, -88, 35, 34, -99,
                58, 84, 8, -110, 96, -110, -64, -74, -100, -97, 18, -63, -72, 117, -19, 108, -75, 122, -54, 31, -117, -5,
                106, 6, 78, 111, 100, -86, 30, 91, 25, 52, 29, 114, 64, -46, 39, 80, -32, -64, -26, -30, -71, 73, -91,
                -52, -36, -87, 83, -103, 112, -31, 10, -102, -73, 35, 50, 96, -68, 44, -51, 5, 20, -55, 45, -81, -6, -104,
                83, 63, 77, 126, 71, -54, 87, 69, -21, 125, -12, 14, -75, 21, -59, 127, 65, -78, 65, 5, 24, 55, -37, 0, -9,
                -74, -43, -8, 120, 55, 108, 94, -126, 34, -1, -1, 123, -50, -127, 89, 72, 20, 20, 29, -113, -22, 10, -85,
                -57, -103, -57, 42, -105, -117, -25, 2, 3, 1, 0, 1};

        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(keyValue);
        PublicKey publicKey = kf.generatePublic(x509Spec);

        String dbCertificateData = "{" +
                "   \"publicKey\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2LgOwEk0H6IWxaoJqCwVYgbU4585Y8B7uTrSZjtSDwUBcVTNMdMI8OoRe+ZlJvkhYnIc8777iqgZx3UMl/7TRj4SKg21ND4gUc3o3k74lmpNLWT9bASrCMSezjNgphd3wxkKeNbzkZNAtZmmV3FknfwZzAyxZudMoGSyly2TVwhcqCMinTpUCJJgksC2nJ8Swbh17Wy1esofi/tqBk5vZKoeWxk0HXJA0idQ4MDm4rlJpczcqVOZcOEKmrcjMmC8LM0FFMktr/qYUz9NfkfKV0XrffQOtRXFf0GyQQUYN9sA97bV+Hg3bF6CIv//e86BWUgUFB2P6gqrx5nHKpeL5wIDAQAB\",\n" +
                "   \"publicKeyAlgorithm\":\"RSA\"," +
                "   \"startDate\":\"13-03-2017 02:42:09 PM +0100\"," +
                "   \"endDate\":\"13-03-2017 02:42:09 PM +0100\"," +
                "   \"subjectDn\":\"subject DN\"," +
                "   \"signatureAlgorithm\":\"a sig ALG\"," +
                "   \"issuerDn\":\"issuer DN\"," +
                "   \"version\":\"a version\"," +
                "   \"serialNumber\":\"serial NR\"" +
                "}";

        Party localParty = new Party();
        localParty.setNodeName("localParty");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");

        PartyIca partyIca = new PartyIca();
        partyIca.setParty(localParty);
        partyIca.setRemoteParty(remoteParty);
        partyIca.setIntegrityCode(IntegrityCode.MODERATE.getCode());
        partyIca.setConfidentialityCode(ConfidentialityCode.LIMITED_HIGH.getCode());
        Date date = new Date();
        partyIca.setUpdatedOn(date);
        partyIca.setActiveState(true);
        partyIca.setCertificate(dbCertificateData);

        // DO THE ACTUAL CALL
        NodeIcaDetails nodeIcaDetails = icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, true);

        assertThat(nodeIcaDetails.getLocalParty(), is(partyIca.getParty().getNodeName()));
        assertThat(nodeIcaDetails.getRemoteParty(), is(partyIca.getRemoteParty().getNodeName()));
        assertThat(nodeIcaDetails.getIntegrityCode(), is(IntegrityCode.MODERATE));
        assertThat(nodeIcaDetails.getConfidentialityCode(), is(ConfidentialityCode.LIMITED_HIGH));
        assertThat(nodeIcaDetails.getCreationDate(), is(partyIca.getUpdatedOn()));
        assertThat(nodeIcaDetails.isActiveState(), is(partyIca.getActiveState()));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getPublicKey(), is(equalTo(publicKey)));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getIssuerDN(), is("issuer DN"));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getSubjectDN(), is("subject DN"));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getSignatureAlgorithm(), is("a sig ALG"));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getVersion(), is("a version"));
        assertThat(nodeIcaDetails.getExtendedCertificateData().getSerialNumber(), is("serial NR"));
    }

}