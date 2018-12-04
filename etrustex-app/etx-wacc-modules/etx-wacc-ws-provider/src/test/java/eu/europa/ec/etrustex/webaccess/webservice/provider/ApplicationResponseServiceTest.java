package eu.europa.ec.etrustex.webaccess.webservice.provider;

import ec.schema.xsd.commonaggregatecomponents_2.BusinessHeaderType;
import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseRequest;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseResponse;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.api.NotifyService;
import eu.europa.ec.etrustex.webaccess.business.message.MessageType;
import eu.europa.ec.etrustex.webaccess.model.MessageStatus;
import oasis.names.specification.ubl.schema.xsd.applicationresponse_2.ApplicationResponseType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentResponseType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ResponseType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ReferenceIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ResponseCodeType;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.Partner;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.PartnerIdentification;

import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceContext;
import java.security.Principal;

import static org.mockito.Mockito.*;

public class ApplicationResponseServiceTest extends AbstractTest {

    @InjectMocks
    private ApplicationResponseService applicationResponseService = new ApplicationResponseService();

    @Mock
    private MailboxManager mailboxManager;

    @Mock
    private NotifyService notifyService;

    @Mock
    private WebServiceContext context;

    @Test
    public void test_submitApplicationResponse_should_createMessageStatusAndNotify() throws Exception {

        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String statusUuid = "statusUuid1";
        String referenceUuid = "referenceUuid1";
        String statusCode = "statusCode1";
        MessageStatus.Status status = MessageStatus.Status.UNKNOWN;
        String referenceType = "referenceType1";

        final String loggedInUser = "userA";

        SubmitApplicationResponseRequest submitApplicationResponseRequest = new SubmitApplicationResponseRequest();
        ApplicationResponseType applicationResponse = new ApplicationResponseType();
        DocumentResponseType documentResponse = new DocumentResponseType();
        ResponseType responseType = new ResponseType();
        DocumentTypeCodeType documentTypeCode = new DocumentTypeCodeType();
        DocumentReferenceType documentReferenceType = new DocumentReferenceType();

        IDType idType = new IDType();
        idType.setValue(statusUuid);
        applicationResponse.setID(idType);

        idType = new IDType();
        idType.setValue(referenceUuid);
        documentReferenceType.setID(idType);

        documentResponse.setDocumentReference(documentReferenceType);

        ResponseCodeType responseCodeType = new ResponseCodeType();
        responseCodeType.setValue(statusCode);
        responseType.setResponseCode(responseCodeType);

        ReferenceIDType referenceIDType = new ReferenceIDType();
        referenceIDType.setValue("referenceId");
        responseType.setReferenceID(referenceIDType);

        documentTypeCode.setValue(referenceType);

        documentReferenceType.setDocumentTypeCode(documentTypeCode);
        documentResponse.setResponse(responseType);
        applicationResponse.setDocumentResponse(documentResponse);
        submitApplicationResponseRequest.setApplicationResponse(applicationResponse);

        Holder<HeaderType> header = new Holder<>();
        header.value = new HeaderType();
        header.value.setBusinessHeader(new BusinessHeaderType());

        Partner partner = new Partner();
        PartnerIdentification partnerIdentification = new PartnerIdentification();
        partnerIdentification.setValue(localPartyName);
        partner.setIdentifier(partnerIdentification);
        header.value.getBusinessHeader().getReceiver().add(partner);

        partner = new Partner();
        partnerIdentification = new PartnerIdentification();
        partnerIdentification.setValue(remotePartyName);
        partner.setIdentifier(partnerIdentification);
        header.value.getBusinessHeader().getSender().add(partner);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return loggedInUser;
            }
        };

        Long messageStatusId = 1L;
        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setId(messageStatusId);

        when(context.getUserPrincipal()).thenReturn(principal);
        when(mailboxManager.createMessageStatus(localPartyName, remotePartyName, statusUuid, referenceUuid, referenceType, status, statusCode, loggedInUser)).thenReturn(messageStatus);

        // DO THE ACTUAL CALL
        SubmitApplicationResponseResponse response = applicationResponseService.submitApplicationResponse(submitApplicationResponseRequest, header);

        assertThat(response, notNullValue());
        assertThat(response.getAck().getAckIndicator().isValue(), is(true));

        verify(mailboxManager).createMessageStatus(localPartyName, remotePartyName, statusUuid, referenceUuid, referenceType, status, statusCode, loggedInUser);
        verify(mailboxManager).consumeNodeMessage(localPartyName, remotePartyName, statusUuid, MessageType.MESSAGE_STATUS);
        verifyNoMoreInteractions(mailboxManager);

        verify(notifyService).notify(messageStatus);
        verifyNoMoreInteractions(mailboxManager);
    }

    @Test
    public void test_submitApplicationResponse_should_notNotify_when_messageStatusNull() throws Exception {

        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String statusUuid = "statusUuid1";
        String referenceUuid = "referenceUuid1";
        String statusCode = "statusCode1";
        MessageStatus.Status status = MessageStatus.Status.UNKNOWN;
        String referenceType = "referenceType1";

        final String loggedInUser = "userA";

        SubmitApplicationResponseRequest submitApplicationResponseRequest = new SubmitApplicationResponseRequest();
        ApplicationResponseType applicationResponse = new ApplicationResponseType();
        DocumentResponseType documentResponse = new DocumentResponseType();
        ResponseType responseType = new ResponseType();
        DocumentTypeCodeType documentTypeCode = new DocumentTypeCodeType();
        DocumentReferenceType documentReferenceType = new DocumentReferenceType();

        IDType idType = new IDType();
        idType.setValue(statusUuid);
        applicationResponse.setID(idType);

        idType = new IDType();
        idType.setValue(referenceUuid);
        documentReferenceType.setID(idType);

        documentResponse.setDocumentReference(documentReferenceType);

        ResponseCodeType responseCodeType = new ResponseCodeType();
        responseCodeType.setValue(statusCode);
        responseType.setResponseCode(responseCodeType);

        ReferenceIDType referenceIDType = new ReferenceIDType();
        referenceIDType.setValue("referenceId");
        responseType.setReferenceID(referenceIDType);

        documentTypeCode.setValue(referenceType);

        documentReferenceType.setDocumentTypeCode(documentTypeCode);
        documentResponse.setResponse(responseType);
        applicationResponse.setDocumentResponse(documentResponse);
        submitApplicationResponseRequest.setApplicationResponse(applicationResponse);

        Holder<HeaderType> header = new Holder<>();
        header.value = new HeaderType();
        header.value.setBusinessHeader(new BusinessHeaderType());

        Partner partner = new Partner();
        PartnerIdentification partnerIdentification = new PartnerIdentification();
        partnerIdentification.setValue(localPartyName);
        partner.setIdentifier(partnerIdentification);
        header.value.getBusinessHeader().getReceiver().add(partner);

        partner = new Partner();
        partnerIdentification = new PartnerIdentification();
        partnerIdentification.setValue(remotePartyName);
        partner.setIdentifier(partnerIdentification);
        header.value.getBusinessHeader().getSender().add(partner);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return loggedInUser;
            }
        };

        when(context.getUserPrincipal()).thenReturn(principal);
        when(mailboxManager.createMessageStatus(localPartyName, remotePartyName, statusUuid, referenceUuid, referenceType, status, statusCode, loggedInUser)).thenReturn(null);

        // DO THE ACTUAL CALL
        SubmitApplicationResponseResponse response = applicationResponseService.submitApplicationResponse(submitApplicationResponseRequest, header);

        assertThat(response, notNullValue());
        assertThat(response.getAck().getAckIndicator().isValue(), is(true));

        verify(mailboxManager).createMessageStatus(localPartyName, remotePartyName, statusUuid, referenceUuid, referenceType, status, statusCode, loggedInUser);
        verifyNoMoreInteractions(mailboxManager);

        verifyZeroInteractions(notifyService);
    }

}