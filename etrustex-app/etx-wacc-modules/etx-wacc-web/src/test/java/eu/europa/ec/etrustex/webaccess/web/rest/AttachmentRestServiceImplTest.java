package eu.europa.ec.etrustex.webaccess.web.rest;

import ec.schema.xsd.commonaggregatecomponents_2.Base64BinaryType;
import ec.schema.xsd.commonaggregatecomponents_2.EncryptionInformationType;
import ec.services.wsdl.documentwrapper_2.FaultResponse;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.workspace.WorkspaceServiceImpl;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.AttachmentType;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.rest.AttachmentRestService;
import eu.europa.ec.etrustex.webaccess.utils.TransferHelper;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.webservice.model.NodeAttachment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import un.unece.uncefact.data.specification.unqualifieddatatypesschemamodule._2.BinaryObjectType;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TransferHelper.class})
public class AttachmentRestServiceImplTest extends AbstractTest {
    private static final Long ATTACHMENT_ID = 1410L;
    private static final byte[] SESSION_KEY = "ABJSDS9847289".getBytes();

    @Mock
    private SecurityChecker securityChecker;

    @Mock
    private AttachmentHandler attachmentHandler;

    @Mock
    private WorkspaceServiceImpl workspaceService;

    @InjectMocks
    private AttachmentRestServiceImpl restController;

    private Attachment attachment;

    @Before
    public void setUp() {
        final Party localParty = new Party();

        final Message message = new Message();
        message.setId(1L);
        message.setActiveState(true);
        message.setLocalParty(localParty);

        this.attachment  = new Attachment();
        attachment.setType(AttachmentType.BINARY);
        attachment.setFileName("MyDocument.pdf");
        attachment.setTransmissionChecksum("A4E2F4B32".getBytes());
        attachment.setTransmissionChecksumMethod("SHA-256");
        attachment.setContentChecksum("26B776EFC".getBytes());
        attachment.setContentChecksumMethod("SHA-256");
        this.attachment.setMessage(message);
        when(attachmentHandler.getAttachment(ATTACHMENT_ID)).thenReturn(attachment);
        when(securityChecker.canAccessMessagesOfParty(localParty)).thenReturn(true);

        PowerMockito.mockStatic(TransferHelper.class);
    }

    @Test
    public void shouldReturnBadRequestWhenAttachmentNotFound() throws IOException {
        // when
        final Response response = restController.read(1L);

        // then
        assertThat(response.getStatus(), is(Response.Status.BAD_REQUEST.getStatusCode()));
    }

    @Test
    public void shouldReturnUnauthorizedWhenMessageIsNull() throws IOException {
        // given
        attachment.setMessage(null);

        // when
        final Response response = restController.read(ATTACHMENT_ID);

        // then
        assertThat(response.getStatus(), is(Response.Status.UNAUTHORIZED.getStatusCode()));
    }

    @Test
    public void shouldReturnUnauthorizedWhenMessageIsNotActive() throws IOException {
        // given
        attachment.getMessage().setActiveState(false);

        // when
        final Response response = restController.read(ATTACHMENT_ID);

        // then
        assertThat(response.getStatus(), is(Response.Status.UNAUTHORIZED.getStatusCode()));
    }


    @Test
    public void shouldReturnUnauthorizedWhenUserHasNoAccessToParty() throws IOException {
        // given
        attachment.getMessage().setLocalParty(new Party());

        // when
        final Response response = restController.read(ATTACHMENT_ID);

        // then
        assertThat(response.getStatus(), is(Response.Status.UNAUTHORIZED.getStatusCode()));
    }

    @Test
    public void shouldReturnBadRequestWhenNoResponseFromNode() throws IOException, FaultResponse {
        // given
        when(attachmentHandler.retrieveNodeAttachment(attachment)).thenReturn(null);

        // when
        final Response response = restController.read(ATTACHMENT_ID);

        // then
        assertThat(response.getStatus(), is(Response.Status.BAD_REQUEST.getStatusCode()));
    }

    @Test
    public void shouldDownloadStream() throws IOException, FaultResponse {
        // given
        long documentSize = 67782L;
        InputStream inputStream = Mockito.mock(InputStream.class);
        DataHandler input = Mockito.mock(DataHandler.class);

        when(input.getInputStream()).thenReturn(inputStream);

        Base64BinaryType streamBase64Binary = new Base64BinaryType();
        streamBase64Binary.setValue(input);

        BinaryObjectType sessionKey = new BinaryObjectType();
        sessionKey.setValue(SESSION_KEY);

        EncryptionInformationType encryptionInformation = new EncryptionInformationType();
        encryptionInformation.setSessionKey(sessionKey);

        NodeAttachment nodeAttachment = new NodeAttachment(inputStream, documentSize);
        nodeAttachment.setSessionKey(SESSION_KEY);

        when(attachmentHandler.retrieveNodeAttachment(attachment)).thenReturn(nodeAttachment);

        // when
        final Response response = restController.read(ATTACHMENT_ID);

        // then
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(response.getEntity() instanceof InputStream, is(true));

        assertThat(response.getMediaType().getType(), is(MediaType.APPLICATION_OCTET_STREAM.getType()));
        assertThat(response.getMediaType().getSubtype(), is(MediaType.APPLICATION_OCTET_STREAM.getSubtype()));

        assertThat(response.getHeaderString("Content-Disposition"), is("attachment; filename="+attachment.getFileName()));
        assertThat(response.getHeaderString(AttachmentRestService.CONTENT_LENGTH_HEADER), is(Long.toString(documentSize)));
        assertThat(response.getHeaderString(AttachmentRestService.TRANSMISSION_CHECKSUM_HEADER), is(DatatypeConverter.printBase64Binary(attachment.getTransmissionChecksum())));
        assertThat(response.getHeaderString(AttachmentRestService.TRANSMISSION_CHECKSUM_METHOD_HEADER), is(attachment.getTransmissionChecksumMethod()));
        assertThat(response.getHeaderString(AttachmentRestService.CONTENT_CHECKSUM_HEADER), is(DatatypeConverter.printBase64Binary(attachment.getContentChecksum())));
        assertThat(response.getHeaderString(AttachmentRestService.CONTENT_CHECKSUM_METHOD_HEADER), is(attachment.getContentChecksumMethod()));
        assertThat(response.getHeaderString(AttachmentRestService.SESSION_KEY_HEADER), is(DatatypeConverter.printBase64Binary(SESSION_KEY)));
    }

    @Test
    public void shouldUploadFile() throws Exception {
        // given
        long localPartyId = 1L;
        String referenceId = "reference id";
        String name = "name";
        String path = "/";
        long size = 2L;
        String mimeType = "mimeType";
        String attachmentType = "attachmentType";
        byte[] contentChecksum = "contentChecksum".getBytes();
        String contentChecksumMethod = "contentChecksumMethod";
        byte[] transmissionChecksum = "transmissionChecksum".getBytes();
        String transmissionChecksumMethod = "transmissionChecksumMethod";
        byte[] encryptedKey = "encryptedKey".getBytes();
        String encryptionCertificateX509SubjectName = "encryptionCertificateX509SubjectName";

        DataSource dataSource = mock(DataSource.class);
        InputStream inputStream = mock(InputStream.class);

        Long attId = 123L;

        when(attachmentHandler.uploadAttachmentToNode(eq(localPartyId), eq(referenceId), eq(name), eq(path),
                eq(size), eq(mimeType), eq(attachmentType),
                argThat(is(equalTo(contentChecksum))), eq(contentChecksumMethod), argThat(is(equalTo(transmissionChecksum))),
                eq(transmissionChecksumMethod), argThat(is(equalTo(encryptedKey))),
                eq(encryptionCertificateX509SubjectName), argThat(is(any(DataSource.class))))).thenReturn(attId);

        Path tmpPath = Files.createTempDirectory(this.getClass().toString());
        when(workspaceService.getWebserviceFolder()).thenReturn(tmpPath);

        // when
        long uploadedAttId = restController.upload(localPartyId, referenceId, name, size, mimeType, attachmentType,
                contentChecksum, contentChecksumMethod, transmissionChecksum, transmissionChecksumMethod, encryptedKey,
                encryptionCertificateX509SubjectName, inputStream);

        assertThat(uploadedAttId, is(attId));

        verify(attachmentHandler).uploadAttachmentToNode(eq(localPartyId), eq(referenceId), eq(name), eq(path),
                eq(size), eq(mimeType), eq(attachmentType),
                argThat(is(equalTo(contentChecksum))), eq(contentChecksumMethod), argThat(is(equalTo(transmissionChecksum))),
                eq(transmissionChecksumMethod), argThat(is(equalTo(encryptedKey))),
                eq(encryptionCertificateX509SubjectName), argThat(is(any(DataSource.class))));
        verifyNoMoreInteractions(attachmentHandler);

        verify(workspaceService).getWebserviceFolder();
        verifyNoMoreInteractions(workspaceService);
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenUploadingFile() throws Exception {
        // given
        long localPartyId = 1L;
        String referenceId = "reference id";
        String name = "name";
        String path = "/";
        long size = 2L;
        String mimeType = "mimeType";
        String attachmentType = "attachmentType";
        byte[] contentChecksum = "contentChecksum".getBytes();
        String contentChecksumMethod = "contentChecksumMethod";
        byte[] transmissionChecksum = "transmissionChecksum".getBytes();
        String transmissionChecksumMethod = "transmissionChecksumMethod";
        byte[] encryptedKey = "encryptedKey".getBytes();
        String encryptionCertificateX509SubjectName = "encryptionCertificateX509SubjectName";

        DataSource dataSource = mock(DataSource.class);
        InputStream inputStream = mock(InputStream.class);

        doThrow(new Exception()).when(attachmentHandler).uploadAttachmentToNode(localPartyId, referenceId, name, eq(path), size, mimeType, attachmentType,
                contentChecksum, contentChecksumMethod, transmissionChecksum, transmissionChecksumMethod, encryptedKey,
                encryptionCertificateX509SubjectName, dataSource);

        // when
        restController.upload(localPartyId, referenceId, name, size, mimeType, attachmentType,
                contentChecksum, contentChecksumMethod, transmissionChecksum, transmissionChecksumMethod, encryptedKey,
                encryptionCertificateX509SubjectName, inputStream);
    }
}