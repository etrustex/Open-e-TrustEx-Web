package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.IcaDetailsService;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.ExtendedCertificateData;
import eu.europa.ec.etrustex.webaccess.model.vo.IcaDetailsVO;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler.*;
import static org.mockito.Mockito.*;

public class CommonMessageCreateHandlerTest extends AbstractTest {

    @InjectMocks
    private CommonMessageCreateHandler handler = new CommonMessageCreateHandler();

    @Mock
    private PartyManager partyManager;

    @Mock
    private IcaManager icaManager;

    @Mock
    protected WebHandlerHelper webHandlerHelper;

    @Mock
    private IcaDetailsService icaDetailsService;

    @Test
    public void test_buildBusinessModel_should_addMandatoryObjectsToModel() throws Exception {
        Party party = new Party();
        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());
        party.setBusiness(business);

        List<Party> remoteParties = new ArrayList();
        Party remoteParty = new Party();
        remoteParties.add(remoteParty);

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setParty(party);

        Date date = new Date();
        ExtendedCertificateData extendedCertificateData = new ExtendedCertificateData(null,
                "subject DN",
                "issuer DN",
                date,
                date,
                "serial NR",
                "a sig ALG",
                "a version");
        PartyIca partyIca = new PartyIca();
        NodeIcaDetails nodeIcaDetails = mock(NodeIcaDetails.class);
        when(nodeIcaDetails.getLocalParty()).thenReturn(party.getNodeName());
        when(nodeIcaDetails.getRemoteParty()).thenReturn(remoteParty.getNodeName());
        when(nodeIcaDetails.getExtendedCertificateData()).thenReturn(extendedCertificateData);
        when(nodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.PUBLIC);
        when(nodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.MODERATE);
        when(nodeIcaDetails.getCreationDate()).thenReturn(date);
        when(nodeIcaDetails.isActiveState()).thenReturn(true);

        when(partyManager.getRemoteParties(party)).thenReturn(remoteParties);
        List<PartyIca> partyIcas = new ArrayList();
        when(icaManager.getIcasByParty(party)).thenReturn(partyIcas);

        when(icaManager.getActiveIcaBySenderReceiver(party.getNodeName(), remoteParty.getNodeName())).thenReturn(partyIca);
        when(icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, false)).thenReturn(nodeIcaDetails);

        // DO THE ACTUAL CALL
        Map<String, Object> model = handler.buildBusinessModel(queryParams);

        Message message = (Message) model.get(MESSAGE_ATTR);
        assertThat(message, is(notNullValue()));

        assertThat(message.getContent(), is(equalTo("")));
        assertThat(message.getMsgState(), is(equalTo(Message.MessageState.DRAFT)));
        assertThat(message.getLocalParty(), is(sameInstance(party)));

        List<Party> remotePartiesFromModel = (List<Party>) model.get(REMOTE_PARTIES_ATTR);
        assertThat(remotePartiesFromModel, hasSize(1));
        assertThat(remotePartiesFromModel, equalTo(remoteParties));
        assertThat((int) model.get("hasLinkedIcas"), is(0));

        IcaDetailsVO icaDetailsVO = (IcaDetailsVO) model.get(MESSAGE_ICA);
        assertThat(icaDetailsVO.getSenderParty(), is(nodeIcaDetails.getLocalParty()));
        assertThat(icaDetailsVO.getReceiverParty(), is(nodeIcaDetails.getRemoteParty()));
        assertThat(icaDetailsVO.isHasCertificate(), is(true));
        assertThat(icaDetailsVO.getVersion(), is(nodeIcaDetails.getExtendedCertificateData().getVersion()));
        assertThat(icaDetailsVO.getSerialNumber(), is(nodeIcaDetails.getExtendedCertificateData().getSerialNumber()));
        assertThat(icaDetailsVO.getIssuerDN(), is(nodeIcaDetails.getExtendedCertificateData().getIssuerDN()));
        assertThat(icaDetailsVO.getSubjectDN(), is(nodeIcaDetails.getExtendedCertificateData().getSubjectDN()));
        assertThat(icaDetailsVO.getSignatureAlgorithm(), is(nodeIcaDetails.getExtendedCertificateData().getSignatureAlgorithm()));
        assertThat(icaDetailsVO.getConfidentialityCode(), is(nodeIcaDetails.getConfidentialityCode().toString()));
        assertThat(icaDetailsVO.getIntegrityCode(), is(nodeIcaDetails.getIntegrityCode().toString()));
        assertThat(icaDetailsVO.getIcaStatus(), is(IcaDetailsVO.IcaStatus.OK));
        assertThat(icaDetailsVO.isActiveState(), is(nodeIcaDetails.isActiveState()));

        verify(icaManager).getActiveIcaBySenderReceiver(party.getNodeName(), remoteParty.getNodeName());
        verifyNoMoreInteractions(icaManager);

        verify(icaDetailsService).getNodeIcaDetailsBySenderReceiver(partyIca, false);
        verifyNoMoreInteractions(icaDetailsService);
    }
}

