package eu.europa.ec.etrustex.webaccess.webservice.ica;

import ec.schema.xsd.commonaggregatecomponents_2.InterchangeAgreementType;
import ec.schema.xsd.retrieveinterchangeagreementsresponse_2.RetrieveInterchangeAgreementsResponseType;
import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.RetrieveInterchangeAgreementsRequestPortType;
import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.SubmitRetrieveInterchangeAgreementsRequestRequest;
import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.SubmitRetrieveInterchangeAgreementsRequestResponse;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.ConfidentialityCode;
import eu.europa.ec.etrustex.webaccess.model.IntegrityCode;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.webservice.NodeWebServiceProvider;
import eu.europa.ec.etrustex.webaccess.webservice.converter.NodeInterchangeAgreementTypeConverter;
import eu.europa.ec.etrustex.webaccess.webservice.model.RawNodeIcaDetails;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.EndpointIDType;
import org.apache.commons.pool2.ObjectPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@PrepareForTest(value = {NodeInterchangeAgreementTypeConverter.class})
@RunWith(PowerMockRunner.class)
public class IcaServiceImplTest extends AbstractTest {

    private interface PortMockInterface extends RetrieveInterchangeAgreementsRequestPortType, BindingProvider {

    }

    @Mock
    private NodeWebServiceProvider nodeWebServiceProvider;

    @InjectMocks
    private IcaServiceImpl icaService;

    @Test
    public void test_retrieveINCADetails_should_fetchCorrectICA_when_multipleICAAvailable_SenderReceiver() throws Exception {
        String loggedInUserParty = "loggedInUserParty";
        String sender = "aaa";
        String receiver = "bbb";

        List<InterchangeAgreementType> interchangeAgreements = new ArrayList<>();

        InterchangeAgreementType interchangeAgreementType1 = new InterchangeAgreementType();
        interchangeAgreementType1.setSenderParty(getParty(sender));
        interchangeAgreementType1.setReceiverParty(getParty(receiver));
        interchangeAgreements.add(interchangeAgreementType1);

        byte[] cert = new byte[]{};

        RawNodeIcaDetails rawNodeIcaDetails = mock(RawNodeIcaDetails.class);
        when(rawNodeIcaDetails.getEncodedCertificate()).thenReturn(cert);
        when(rawNodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.LIMITED_HIGH);
        when(rawNodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.STRATEGIC);
        PowerMockito.mockStatic(NodeInterchangeAgreementTypeConverter.class);
        when(NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(interchangeAgreementType1)).thenReturn(rawNodeIcaDetails);

        IcaServiceImpl spyIcaService = spy(icaService);
        doReturn(interchangeAgreements).when(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, receiver);

        //ACTUAL CALL
        List<NodeIcaDetails> nodeIcaDetails = spyIcaService.retrieveICADetails("usr", "pass", loggedInUserParty, sender, receiver);

        assertThat(nodeIcaDetails.size(), is(1));
        assertThat(nodeIcaDetails.get(0), is(notNullValue()));

        verify(spyIcaService).retrieveICADetails("usr", "pass", loggedInUserParty, sender, receiver);
        verify(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, receiver);
        verifyNoMoreInteractions(spyIcaService);

        PowerMockito.verifyStatic();
        NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(argThat(sameInstance(interchangeAgreementType1)));
    }

    @Test
    public void test_retrieveINCADetails_should_fetchCorrectICA_when_multipleICAAvailable_Sender() throws Exception {
        String loggedInUserParty = "loggedInUserParty";
        String sender = "aaa";
        String receiver = "bbb";

        List<InterchangeAgreementType> interchangeAgreements = new ArrayList<>();

        InterchangeAgreementType interchangeAgreementType1 = new InterchangeAgreementType();
        interchangeAgreementType1.setSenderParty(getParty(sender));
        interchangeAgreementType1.setReceiverParty(getParty(receiver));
        interchangeAgreements.add(interchangeAgreementType1);

        byte[] cert = new byte[]{};

        RawNodeIcaDetails rawNodeIcaDetails = mock(RawNodeIcaDetails.class);
        when(rawNodeIcaDetails.getEncodedCertificate()).thenReturn(cert);
        when(rawNodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.LIMITED_HIGH);
        when(rawNodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.STRATEGIC);
        PowerMockito.mockStatic(NodeInterchangeAgreementTypeConverter.class);
        when(NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(interchangeAgreementType1)).thenReturn(rawNodeIcaDetails);

        IcaServiceImpl spyIcaService = spy(icaService);
        doReturn(interchangeAgreements).when(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, null);

        //ACTUAL CALL
        List<NodeIcaDetails> nodeIcaDetails = spyIcaService.retrieveICADetails("usr", "pass", loggedInUserParty, sender, null);

        assertThat(nodeIcaDetails.size(), is(1));

        verify(spyIcaService).retrieveICADetails("usr", "pass", loggedInUserParty, sender, null);
        verify(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, null);
        verifyNoMoreInteractions(spyIcaService);

        PowerMockito.verifyStatic();
        NodeInterchangeAgreementTypeConverter.toRawNodeIcaDetailsVo(argThat(sameInstance(interchangeAgreementType1)));
    }

    @Test
    public void testRetrieveICADetails_should_returnEmpty_when_noMatchingICA_SenderReceiver() throws Exception {
        String loggedInUserParty = "loggedInUserParty";
        String sender = "sender";
        String receiver = "receiver";

        List<InterchangeAgreementType> interchangeAgreements = new ArrayList<>();

        IcaServiceImpl spyIcaService = spy(icaService);
        doReturn(interchangeAgreements).when(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, receiver);

        List<NodeIcaDetails> nodeIcaDetails = spyIcaService.retrieveICADetails("usr", "pass", loggedInUserParty, sender, receiver);

        assertThat(nodeIcaDetails.size(), is(0));

        verify(spyIcaService).retrieveICADetails("usr", "pass", loggedInUserParty, sender, receiver);
        verify(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, receiver);
        verifyNoMoreInteractions(spyIcaService);
    }

    @Test
    public void testRetrieveICADetails_should_returnEmpty_when_noMatchingICA_Sender() throws Exception {
        String loggedInUserParty = "loggedInUserParty";
        String sender = "sender";
        String receiver = "receiver";

        List<InterchangeAgreementType> interchangeAgreements = new ArrayList<>();

        IcaServiceImpl spyIcaService = spy(icaService);
        doReturn(interchangeAgreements).when(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, null);

        List<NodeIcaDetails> nodeIcaDetailsList = spyIcaService.retrieveICADetails("usr", "pass", loggedInUserParty, sender, null);

        assertThat(nodeIcaDetailsList, is(notNullValue()));
        assertThat(nodeIcaDetailsList.size(), is(0));

        verify(spyIcaService).retrieveICADetails("usr", "pass", loggedInUserParty, sender, null);
        verify(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, null);
        verifyNoMoreInteractions(spyIcaService);
    }

    @Test
    public void testRetrieveINCADetails_should_returnEmpty_when_exceptionOnNodeConnection_SenderReceiver() throws Exception {
        String loggedInUserParty = "loggedInUserParty";
        String sender = "aaa";
        String receiver = "bbb";

        IcaServiceImpl spyIcaService = spy(icaService);
        doThrow(IllegalAccessException.class).when(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, receiver);

        List<NodeIcaDetails> nodeIcaDetails = spyIcaService.retrieveICADetails("usr", "pass", loggedInUserParty, sender, receiver);

        assertThat(nodeIcaDetails.size(), is(0));

        verify(spyIcaService).retrieveICADetails("usr", "pass", loggedInUserParty, sender, receiver);
        verify(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, receiver);
        verifyNoMoreInteractions(spyIcaService);
    }

    @Test
    public void testRetrieveINCADetails_should_returnEmpty_when_exceptionOnNodeConnection_Sender() throws Exception {
        String loggedInUserParty = "loggedInUserParty";
        String sender = "aaa";
        String receiver = "bbb";

        IcaServiceImpl spyIcaService = spy(icaService);
        doThrow(IllegalAccessException.class).when(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, null);

        List<NodeIcaDetails> nodeIcaDetailsList = spyIcaService.retrieveICADetails("usr", "pass", loggedInUserParty, sender, null);

        assertThat(nodeIcaDetailsList, is(notNullValue()));
        assertThat(nodeIcaDetailsList.size(), is(0));

        verify(spyIcaService).retrieveICADetails("usr", "pass", loggedInUserParty, sender, null);
        verify(spyIcaService).retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, null);
        verifyNoMoreInteractions(spyIcaService);
    }

    @Test
    public void testRetrieveICA_SenderReceiver_success() throws Exception {

        String loggedInUserParty = "loggedInUserParty";
        String sender = "sender";
        String receiver = "receiver";

        final PortMockInterface port = mock(PortMockInterface.class);

        ObjectPool icaWrapperPool = mock(ObjectPool.class);
        icaService.icaWrapperPool = icaWrapperPool;

        when(icaWrapperPool.borrowObject()).thenReturn(port);

        SubmitRetrieveInterchangeAgreementsRequestResponse response = mock(SubmitRetrieveInterchangeAgreementsRequestResponse.class);

        RetrieveInterchangeAgreementsResponseType retrieveInterchangeAgreementsResponseType = mock(RetrieveInterchangeAgreementsResponseType.class);
        InterchangeAgreementType interchangeAgreementType = new InterchangeAgreementType();

        interchangeAgreementType.setSenderParty(getPartyType(sender));
        interchangeAgreementType.setReceiverParty(getPartyType("RECIPIENT_PARTY"));

        retrieveInterchangeAgreementsResponseType.getInterchangeAgreement().add(interchangeAgreementType);
        response.setRetrieveInterchangeAgreementsResponse(retrieveInterchangeAgreementsResponseType);

        when(port.submitRetrieveInterchangeAgreementsRequest(argThat(any(SubmitRetrieveInterchangeAgreementsRequestRequest.class)), argThat(any(Holder.class)))).thenReturn(response);
        when(response.getRetrieveInterchangeAgreementsResponse()).thenReturn(retrieveInterchangeAgreementsResponseType);

        List<InterchangeAgreementType> agreementsList = new ArrayList<>();
        when(retrieveInterchangeAgreementsResponseType.getInterchangeAgreement()).thenReturn(agreementsList);

        List<InterchangeAgreementType> interchangeAgreementTypeList = icaService.retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, receiver);

        assertThat(interchangeAgreementTypeList, is(sameInstance(agreementsList)));
        verify(port, times(1)).submitRetrieveInterchangeAgreementsRequest(argThat(any(SubmitRetrieveInterchangeAgreementsRequestRequest.class)), argThat(any(Holder.class)));
        verifyNoMoreInteractions(port);

        verify(icaWrapperPool).borrowObject();
        verify(icaWrapperPool).returnObject(port);
        verifyNoMoreInteractions(icaWrapperPool);
    }

    @Test
    public void testRetrieveICA_Sender_success() throws Exception {
        String loggedInUserParty = "loggedInUserParty";
        String sender = "sender";
        String receiver = "receiver";

        final PortMockInterface port = mock(PortMockInterface.class);

        ObjectPool icaWrapperPool = mock(ObjectPool.class);
        icaService.icaWrapperPool = icaWrapperPool;

        when(icaWrapperPool.borrowObject()).thenReturn(port);

        SubmitRetrieveInterchangeAgreementsRequestResponse response = mock(SubmitRetrieveInterchangeAgreementsRequestResponse.class);

        RetrieveInterchangeAgreementsResponseType retrieveInterchangeAgreementsResponseType = mock(RetrieveInterchangeAgreementsResponseType.class);
        InterchangeAgreementType interchangeAgreementType = new InterchangeAgreementType();

        interchangeAgreementType.setSenderParty(getPartyType(sender));
        interchangeAgreementType.setReceiverParty(getPartyType("RECIPIENT_PARTY"));

        retrieveInterchangeAgreementsResponseType.getInterchangeAgreement().add(interchangeAgreementType);
        response.setRetrieveInterchangeAgreementsResponse(retrieveInterchangeAgreementsResponseType);

        when(port.submitRetrieveInterchangeAgreementsRequest(argThat(any(SubmitRetrieveInterchangeAgreementsRequestRequest.class)), argThat(any(Holder.class)))).thenReturn(response);
        when(response.getRetrieveInterchangeAgreementsResponse()).thenReturn(retrieveInterchangeAgreementsResponseType);

        List<InterchangeAgreementType> agreementsList = new ArrayList<>();
        when(retrieveInterchangeAgreementsResponseType.getInterchangeAgreement()).thenReturn(agreementsList);

        List<InterchangeAgreementType> interchangeAgreementTypeList = icaService.retrieveInterchangeAgreement("usr", "pass", loggedInUserParty, sender, null);

        assertThat(interchangeAgreementTypeList, is(sameInstance(agreementsList)));
        verify(port, times(1)).submitRetrieveInterchangeAgreementsRequest(argThat(any(SubmitRetrieveInterchangeAgreementsRequestRequest.class)), argThat(any(Holder.class)));
        verifyNoMoreInteractions(port);

        verify(icaWrapperPool).borrowObject();
        verify(icaWrapperPool).returnObject(port);
        verifyNoMoreInteractions(icaWrapperPool);
    }

    private PartyType getParty(String sender) {
        PartyType party = new PartyType();
        party.setEndpointID(new EndpointIDType());
        party.getEndpointID().setValue(sender);
        return party;
    }

    private PartyType getPartyType(String sender) {
        PartyType party = new PartyType();
        party.setEndpointID(new EndpointIDType());
        party.getEndpointID().setValue(sender);
        return party;
    }
}
