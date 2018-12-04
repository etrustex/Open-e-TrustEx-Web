package eu.europa.ec.etrustex.webaccess.webservice.status;

import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import ec.services.wsdl.applicationresponse_2.ApplicationResponsePortType;
import ec.services.wsdl.applicationresponse_2.FaultResponse;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseRequest;
import ec.services.wsdl.applicationresponse_2.SubmitApplicationResponseResponse;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.webservice.NodeObjectBuilder;
import eu.europa.ec.etrustex.webaccess.webservice.NodeWebServiceProvider;
import oasis.names.specification.ubl.schema.xsd.applicationresponse_2.ApplicationResponseType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.ws.Holder;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NodeObjectBuilder.class})
public class ApplicationResponseClientServiceImplTest extends AbstractTest {

    @Mock
    private NodeWebServiceProvider nodeWebServiceProvider;

    @InjectMocks
    private ApplicationResponseClientServiceImpl applicationResponseClientService;

    @Test
    public void test_sendMessageStatus_should_callBuildHeaderWithStatusScope() throws Exception {
        SubmitApplicationResponseRequest request = new SubmitApplicationResponseRequest();
        request.setApplicationResponse(new ApplicationResponseType());
        request.getApplicationResponse().setID(new IDType());
        request.getApplicationResponse().getID().setValue("blabla");
        String senderParty = "sender";
        String receiverParty = "receiver";
        Holder<HeaderType> authorisationHeaderHolder = new Holder<>();
        final SubmitApplicationResponseResponse expected = new SubmitApplicationResponseResponse();
        ApplicationResponsePortType port = new ApplicationResponsePortType() {
            @Override
            public SubmitApplicationResponseResponse submitApplicationResponse(SubmitApplicationResponseRequest submitApplicationResponseRequest, Holder<HeaderType> header) throws FaultResponse {
                return expected;
            }
        };

        PowerMockito.mockStatic(NodeObjectBuilder.class);
        when(NodeObjectBuilder.buildHeaderWithStatusScope(senderParty, receiverParty)).thenReturn(authorisationHeaderHolder);

        when(nodeWebServiceProvider.getApplicationResponsePort()).thenReturn(port);

        // DO THE ACTUAL CALL
        SubmitApplicationResponseResponse response = applicationResponseClientService.sendMessageStatus("usr", "pass", request, senderParty, receiverParty);

        assertThat(expected, is(sameInstance(response)));

        PowerMockito.verifyStatic();
        NodeObjectBuilder.buildHeaderWithStatusScope(senderParty, receiverParty);

        PowerMockito.verifyStatic(never());
        NodeObjectBuilder.buildHeader(senderParty, receiverParty);
    }
}