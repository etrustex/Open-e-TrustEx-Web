package eu.europa.ec.etrustex.webaccess.webservice;

import ec.schema.xsd.documentbundle_1.DocumentBundleType;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.AttachmentType;
import eu.europa.ec.etrustex.webaccess.model.vo.SignatureVO;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import eu.europa.ec.etrustex.webaccess.utils.MessageUUIDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@PrepareForTest({EtxSecurityProvider.class})
@RunWith(PowerMockRunner.class)
public class MessageBundleHandlerTest extends AbstractTest {

    private MessageBundleHandler messageBundleHandler = new MessageBundleHandler();

    @Override
    protected void onSetUp() {
        PowerMockito.mockStatic(EtxSecurityProvider.class);
        EtxSecurityProvider etxSecurityProviderMock = mock(EtxSecurityProvider.class);
        when(EtxSecurityProvider.getInstance()).thenReturn(etxSecurityProviderMock);
    }

    @Test
    public void test_sendMessage() throws Exception {
        Attachment file1 = new Attachment();
        file1.setFileName("File1");
        file1.setType(AttachmentType.BINARY);
        file1.setFileSize(0L);

        Attachment file2 = new Attachment();
        file2.setFileName("File2");
        file2.setType(AttachmentType.BINARY);
        file2.setFileSize(0L);

        List<Attachment> fileList = Arrays.asList(file1, file2);

        String sender = "apladap";
        String receiver = "receiver";

        String subject = "subject";
        String content = "content";
        String bundleId = MessageUUIDGenerator.randomMessageBundleUUID(sender);
        SignatureVO signature = null;
        DocumentBundleType documentBundleType = messageBundleHandler.buildDocumentBundle(sender, receiver, subject, content, fileList, signature, bundleId);

        assertThat(documentBundleType, is(notNullValue()));
        assertThat(documentBundleType.getID().getValue(), startsWith("ETX-MSG-BUNDLE-" + sender));
        assertThat(documentBundleType.getProfileID().getValue(), is(subject));
        assertThat(documentBundleType.getNote().size(), is(1));
        assertThat(documentBundleType.getNote().get(0).getValue(), is(content));
        assertThat(documentBundleType.getSenderParty().getEndpointID().getValue(), is(sender));
        assertThat(documentBundleType.getReceiverParty().size(), is(1));
        assertThat(documentBundleType.getReceiverParty().get(0).getEndpointID().getValue(), is(receiver));
        assertThat(documentBundleType.getDocumentWrapperReference().size(), is(2));
        assertThat(documentBundleType.getDocumentWrapperReference().get(0).getID().getValue(), is(file1.getWrapperId()));
        assertThat(documentBundleType.getDocumentWrapperReference().get(1).getID().getValue(), is(file2.getWrapperId()));
    }
}
