package eu.europa.ec.etrustex.webaccess.webservice.provider;

import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import ec.schema.xsd.documentbundle_1.DocumentBundleType;
import ec.services.wsdl.documentbundle_2.SubmitDocumentBundleRequest;
import ec.services.wsdl.documentbundle_2.SubmitDocumentBundleResponse;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.message.MessageType;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.utils.ConcurrentHelper;
import eu.europa.ec.etrustex.webaccess.webservice.provider.business.InboxNotificationBO;
import eu.europa.ec.etrustex.webaccess.webservice.provider.convert.EtxDocumentBundleConverter;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.EndpointIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceContext;
import java.security.Principal;

import static org.mockito.Mockito.*;

@PrepareForTest(EtxDocumentBundleConverter.class)
@RunWith(PowerMockRunner.class)
public class DocumentBundleServiceTest extends AbstractTest {

    @InjectMocks
    private DocumentBundleService documentBundleService = new DocumentBundleService();

    @Mock
    private WebServiceContext context;

    @Mock
    private InboxNotificationBO inboxNotificationBO;

    @Mock
    private MailboxManager mailboxManager;

    @Mock
    private ConcurrentHelper concurrentHelper;

    @Mock
    private EtxDocumentBundleConverter etxDocumentBundleConverter;

    @Test
    public void test_submitDocumentBundle_should_handleMessage_when_notInProgress() throws Exception {
        String bundleId = "bundleId1";
        String localPartyName = "localPartyName1";
        String remotePartyName = "remotePartyName";
        long messageId = 1L;
        final String loggedInUser = "user1";

        SubmitDocumentBundleRequest submitDocumentBundleRequest = new SubmitDocumentBundleRequest();
        Holder<HeaderType> header = new Holder<>();

        DocumentBundleType documentBundle = new DocumentBundleType();
        IDType idType = new IDType();
        idType.setValue(bundleId);
        documentBundle.setID(idType);

        PartyType receiverParty = new PartyType();
        EndpointIDType endpointIDType = new EndpointIDType();
        endpointIDType.setValue(localPartyName);
        receiverParty.setEndpointID(endpointIDType);

        PartyType senderParty = new PartyType();
        EndpointIDType endpointIDRemotePartyType = new EndpointIDType();
        endpointIDRemotePartyType.setValue(remotePartyName);
        senderParty.setEndpointID(endpointIDRemotePartyType);

        documentBundle.getReceiverParty().add(receiverParty);
        documentBundle.setSenderParty(senderParty);
        submitDocumentBundleRequest.setDocumentBundle(documentBundle);

        Party localParty = new Party();
        localParty.setConsumeNodeMessages(false);

        Party remoteParty = new Party();
        remoteParty.setNodeName(remotePartyName);

        Message message = new Message();
        message.setId(messageId);
        message.setRemoteParty(remoteParty);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return loggedInUser;
            }
        };

        PowerMockito.mockStatic(EtxDocumentBundleConverter.class);
        when(etxDocumentBundleConverter.convertMessage(documentBundle)).thenReturn(message);
        when(concurrentHelper.addKey(bundleId)).thenReturn(Boolean.FALSE);
        when(context.getUserPrincipal()).thenReturn(principal);
        when(inboxNotificationBO.handleMessage(principal.getName(), message, localPartyName, remotePartyName)).thenReturn(message);

        // DO THE ACTUAL CALL
        SubmitDocumentBundleResponse response = documentBundleService.submitDocumentBundle(submitDocumentBundleRequest, header);

        assertThat(response, notNullValue());
        assertThat(response.getAck().getAckIndicator().isValue(), is(Boolean.TRUE));

        verify(concurrentHelper).addKey(bundleId);
        verify(concurrentHelper).removeKey(bundleId);
        verifyNoMoreInteractions(concurrentHelper);

        verify(mailboxManager).consumeNodeMessage(localPartyName, message.getRemoteParty().getNodeName(), message.getBundleId(), MessageType.MESSAGE_BUNDLE);
        verifyNoMoreInteractions(mailboxManager);

        verify(inboxNotificationBO).handleMessage(loggedInUser, message, localPartyName, remotePartyName);
        verifyNoMoreInteractions(inboxNotificationBO);
    }

    @Test
    public void test_submitDocumentBundle_should_notHandleMessage_when_inProgress() throws Exception {
        String bundleId = "bundleId1";
        String localPartyName = "localPartyName1";
        String senderPartyName = "senderPartyName1";
        long messageId = 1L;
        final String loggedInUser = "user1";

        SubmitDocumentBundleRequest submitDocumentBundleRequest = new SubmitDocumentBundleRequest();
        Holder<HeaderType> header = new Holder<>();

        DocumentBundleType documentBundle = new DocumentBundleType();
        IDType idType = new IDType();
        idType.setValue(bundleId);
        documentBundle.setID(idType);

        PartyType receiverParty = new PartyType();
        EndpointIDType endpointIDType = new EndpointIDType();
        endpointIDType.setValue(localPartyName);
        receiverParty.setEndpointID(endpointIDType);
        documentBundle.getReceiverParty().add(receiverParty);

        PartyType senderParty = new PartyType();
        endpointIDType = new EndpointIDType();
        endpointIDType.setValue(senderPartyName);
        senderParty.setEndpointID(endpointIDType);
        documentBundle.setSenderParty(senderParty);

        submitDocumentBundleRequest.setDocumentBundle(documentBundle);

        Party localParty = new Party();
        localParty.setConsumeNodeMessages(true);

        Party remoteParty = new Party();
        remoteParty.setNodeName(senderPartyName);

        Message message = new Message();
        message.setId(messageId);
        message.setBundleId(bundleId);
        message.setRemoteParty(remoteParty);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return loggedInUser;
            }
        };

        PowerMockito.mockStatic(EtxDocumentBundleConverter.class);
        when(etxDocumentBundleConverter.convertMessage(documentBundle)).thenReturn(message);
        when(concurrentHelper.addKey(bundleId)).thenReturn(Boolean.TRUE);
        when(context.getUserPrincipal()).thenReturn(principal);

        // DO THE ACTUAL CALL
        SubmitDocumentBundleResponse response = documentBundleService.submitDocumentBundle(submitDocumentBundleRequest, header);

        assertThat(response, notNullValue());
        assertThat(response.getAck().getAckIndicator().isValue(), is(Boolean.TRUE));

        verify(concurrentHelper).addKey(bundleId);
        verifyNoMoreInteractions(concurrentHelper);

        verifyZeroInteractions(inboxNotificationBO);
    }
}