package eu.europa.ec.etrustex.webaccess.web.view.business.edma.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.*;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.business.util.IcaHelper;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.*;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.WebHandlerHelper;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EDMAUtils;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EdmaMessage;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler.*;
import static org.mockito.Mockito.*;

public class MessageEditHandlerTest extends AbstractTest {

    @InjectMocks
    private MessageEditHandler handler = new MessageEditHandler();

    @Mock
    protected UserSessionContext userSessionContext;

    @Mock
    private EDMAUtils edmaUtils;

    @Mock
    protected MailboxManager mailboxManager;

    @Mock
    private AttachmentHandler attachmentHandler;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private PartyManager partyManager;

    @Mock
    private IcaManager icaManager;

    @Mock
    protected WebHandlerHelper webHandlerHelper;

    @Mock
    private IcaDetailsService icaDetailsService;

    @Mock
    private IcaHelper icaHelper;

    @Test
    public void test_buildBusinessModel_should_addMandatoryObjectsToModel() throws Exception {
        Party party = new Party();
        party.setNodeName("party");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");
        Long messageId = 12345L;
        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setMessageId(messageId);
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

        User mockUser = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(mockUser);

        Message message = new Message();
        message.setId(messageId);
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.DRAFT);
        when(mailboxManager.getMessageDetailsAndFetchSignatureEagerly(messageId, mockUser)).thenReturn(message);

        Metadata metadata = new Metadata();
        when(attachmentHandler.getMetadata(messageId)).thenReturn(metadata);

        List<Attachment> binaryAttachments = new ArrayList<>();
        when(mailboxManager.getBinaryAttachments(messageId, mockUser)).thenReturn(binaryAttachments);

        EdmaMessage edmaMessage = new EdmaMessage();

        ArrayList<AttachmentMetadata> aml = new ArrayList<>();
        edmaMessage.setAttachmentMetadataList(aml);

        when(edmaUtils.fetchEdmaMessage(message, binaryAttachments, metadata)).thenReturn(edmaMessage);

        List<Party> remoteParties = new ArrayList();
        remoteParties.add(remoteParty);
        when(partyManager.getRemoteParties(party)).thenReturn(remoteParties);

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        when(icaManager.getActiveIcaBySenderReceiver(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName())).thenReturn(partyIca);
        when(icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, false)).thenReturn(nodeIcaDetails);

        // DO THE ACTUAL CALL
        Map<String, Object> result = handler.buildBusinessModel(queryParams);

        assertThat((Message) result.get(MESSAGE_ATTR), is(sameInstance(message)));
        assertThat((EdmaMessage) result.get(EDMA_MESSAGE_ATTR), is(sameInstance(edmaMessage)));
        assertThat(result.size(), is(7));

        verify(mailboxManager).getMessageDetailsAndFetchSignatureEagerly(messageId, mockUser);
        verify(mailboxManager).getBinaryAttachments(messageId, mockUser);
        verify(mailboxManager).getAttachments(messageId, mockUser);
        verifyNoMoreInteractions(mailboxManager);

        verify(attachmentHandler).getMetadata(messageId);
        verifyNoMoreInteractions(attachmentHandler);

        List<Party> remotePartiesFromModel = (List<Party>) result.get(REMOTE_PARTIES_ATTR);
        assertThat(remotePartiesFromModel, hasSize(1));
        assertThat(remotePartiesFromModel, equalTo(remoteParties));

        verify(partyManager).getRemoteParties(party);
        verifyNoMoreInteractions(partyManager);

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);

        IcaDetailsVO icaDetailsVO = (IcaDetailsVO) result.get(MESSAGE_ICA);
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
        verify(icaManager).getActiveIcaBySenderReceiver(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName());
        verifyNoMoreInteractions(icaManager);

        verify(icaDetailsService).getNodeIcaDetailsBySenderReceiver(partyIca, false);
        verifyNoMoreInteractions(icaDetailsService);
    }

    @Test
    public void test_buildBusinessModel_should_addMandatoryObjectsToModel_sentMessage() throws Exception {
        Party party = new Party();
        party.setNodeName("party");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");
        Long messageId = 12345L;
        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setMessageId(messageId);
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

        User mockUser = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(mockUser);

        Message message = new Message();
        message.setId(messageId);
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.SENT);
        when(mailboxManager.getMessageDetailsAndFetchSignatureEagerly(messageId, mockUser)).thenReturn(message);

        Metadata metadata = new Metadata();
        when(attachmentHandler.getMetadata(messageId)).thenReturn(metadata);

        List<Attachment> binaryAttachments = new ArrayList<>();
        when(mailboxManager.getBinaryAttachments(messageId, mockUser)).thenReturn(binaryAttachments);

        EdmaMessage edmaMessage = new EdmaMessage();

        ArrayList<AttachmentMetadata> aml = new ArrayList<>();
        edmaMessage.setAttachmentMetadataList(aml);

        when(edmaUtils.fetchEdmaMessage(message, binaryAttachments, metadata)).thenReturn(edmaMessage);

        List<Party> remoteParties = new ArrayList();
        remoteParties.add(remoteParty);
        when(partyManager.getRemoteParties(party)).thenReturn(remoteParties);

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        when(icaManager.getActiveIcaBySenderReceiver(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName())).thenReturn(partyIca);
        when(icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, false)).thenReturn(nodeIcaDetails);

        // DO THE ACTUAL CALL
        Map<String, Object> result = handler.buildBusinessModel(queryParams);

        assertThat((Message) result.get(MESSAGE_ATTR), is(sameInstance(message)));
        assertThat((EdmaMessage) result.get(EDMA_MESSAGE_ATTR), is(sameInstance(edmaMessage)));
        assertThat(result.size(), is(7));

        verify(mailboxManager).getMessageDetailsAndFetchSignatureEagerly(messageId, mockUser);
        verify(mailboxManager).getBinaryAttachments(messageId, mockUser);
        verify(mailboxManager).getAttachments(messageId, mockUser);
        verifyNoMoreInteractions(mailboxManager);

        verify(attachmentHandler).getMetadata(messageId);
        verifyNoMoreInteractions(attachmentHandler);

        List<Party> remotePartiesFromModel = (List<Party>) result.get(REMOTE_PARTIES_ATTR);
        assertThat(remotePartiesFromModel, hasSize(1));
        assertThat(remotePartiesFromModel, equalTo(remoteParties));

        verify(partyManager).getRemoteParties(party);
        verifyNoMoreInteractions(partyManager);

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);

        IcaDetailsVO icaDetailsVO = (IcaDetailsVO) result.get(MESSAGE_ICA);
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
        verify(icaManager).getActiveIcaBySenderReceiver(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName());
        verifyNoMoreInteractions(icaManager);

        verify(icaDetailsService).getNodeIcaDetailsBySenderReceiver(partyIca, false);
        verifyNoMoreInteractions(icaDetailsService);
    }

    @Test
    public void test_buildBusinessModel_should_addRetentionMetadataToModel() throws Exception {
        Party party = new Party();
        party.setNodeName("party");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");

        Long messageId = 12345L;
        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setParty(party);
        queryParams.setMessageId(messageId);

        User mockUser = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(mockUser);

        Message message = new Message();
        message.setId(messageId);
        message.setMsgState(Message.MessageState.DRAFT);
        message.setCreatedOn(new Date());
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);

        when(mailboxManager.getMessageDetailsAndFetchSignatureEagerly(messageId, mockUser)).thenReturn(message);

        Metadata metadata = new Metadata();
        when(attachmentHandler.getMetadata(messageId)).thenReturn(metadata);

        List<Attachment> binaryAttachments = new ArrayList<>();
        when(mailboxManager.getBinaryAttachments(messageId, mockUser)).thenReturn(binaryAttachments);

        EdmaMessage edmaMessage = new EdmaMessage();

        ArrayList<AttachmentMetadata> aml = new ArrayList<>();
        edmaMessage.setAttachmentMetadataList(aml);
        when(edmaUtils.fetchEdmaMessage(message, binaryAttachments, metadata)).thenReturn(edmaMessage);

        Configuration configuration = new Configuration();
        RetentionMetadata retentionMetadata = new RetentionMetadata();

        when(configurationService.getConfiguration()).thenReturn(configuration);
        when(configurationService.isRetentionPolicyValid(configuration)).thenReturn(true);
        when(configurationService.computeRetentionMetadata(configuration, message.getCreatedOn())).thenReturn(retentionMetadata);

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = handler.buildBusinessModel(queryParams);

        assertThat((RetentionMetadata) result.get(RETENTION_METADATA_ATTR), is(sameInstance(retentionMetadata)));

        verify(edmaUtils).fetchEdmaMessage(message, binaryAttachments, metadata);
        verifyNoMoreInteractions(edmaUtils);

        verify(mailboxManager).getMessageDetailsAndFetchSignatureEagerly(messageId, mockUser);
        verify(mailboxManager).getBinaryAttachments(messageId, mockUser);
        verify(mailboxManager).getAttachments(messageId, mockUser);
        verifyNoMoreInteractions(mailboxManager);

        verify(attachmentHandler).getMetadata(messageId);
        verifyNoMoreInteractions(attachmentHandler);

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);

        verify(icaManager).getActiveIcaBySenderReceiver(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName());
        verifyNoMoreInteractions(icaManager);
    }
}
