package eu.europa.ec.etrustex.webaccess.webservice;

import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import org.junit.Test;

import javax.xml.ws.Holder;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;

public class NodeObjectBuilderTest extends AbstractTest {

    @Test
    public void test_buildHeader_should__returnHeaderWithSenderAndReceiverAndNoCreateStatusAvailable() throws Exception {
        String senderParty = "sender";
        String receiverParty = "receiver";

        // DO THE ACTUAL CALL
        Holder<HeaderType> holder = NodeObjectBuilder.buildHeader(senderParty, receiverParty);

        assertThat(holder, notNullValue());
        assertThat(holder.value, notNullValue());
        assertThat(holder.value.getBusinessHeader(), notNullValue());
        assertThat(holder.value.getBusinessHeader().getSender(), notNullValue());
        assertThat(holder.value.getBusinessHeader().getSender().isEmpty(), is(false));
        assertThat(holder.value.getBusinessHeader().getSender().get(0).getIdentifier(), notNullValue());
        assertThat(holder.value.getBusinessHeader().getSender().get(0).getIdentifier().getValue(), is(senderParty));
        assertThat(holder.value.getBusinessHeader().getReceiver(), notNullValue());
        assertThat(holder.value.getBusinessHeader().getReceiver().isEmpty(), is(false));
        assertThat(holder.value.getBusinessHeader().getReceiver().get(0).getIdentifier(), notNullValue());
        assertThat(holder.value.getBusinessHeader().getReceiver().get(0).getIdentifier().getValue(), is(receiverParty));
        assertThat(holder.value.getBusinessHeader().getBusinessScope(), nullValue());
        assertThat(holder.value.getTechnicalHeader(), notNullValue());
    }

    @Test
    public void test_buildHeaderWithStatusScope_should_returnHeaderWithCreateStatusAvailable() throws Exception {
        String senderParty = "sender";
        String receiverParty = "receiver";
        String statusScopeIdentifier = "CREATE_STATUS_AVAILABLE";

        NodeObjectBuilder spy = spy(new NodeObjectBuilder());

        // DO THE ACTUAL CALL
        Holder<HeaderType> holder = spy.buildHeaderWithStatusScope(senderParty, receiverParty);

        assertThat(holder, notNullValue());
        assertThat(holder.value, notNullValue());
        assertThat(holder.value.getBusinessHeader(), notNullValue());
        assertThat(holder.value.getBusinessHeader().getBusinessScope(), notNullValue());
        assertThat(holder.value.getBusinessHeader().getBusinessScope().getScope(), notNullValue());
        assertThat(holder.value.getBusinessHeader().getBusinessScope().getScope().isEmpty(), is(false));
        assertThat(holder.value.getBusinessHeader().getBusinessScope().getScope().get(0).getType(), is(equalTo(statusScopeIdentifier)));

        verify(spy).buildHeader(senderParty, receiverParty);
    }
}