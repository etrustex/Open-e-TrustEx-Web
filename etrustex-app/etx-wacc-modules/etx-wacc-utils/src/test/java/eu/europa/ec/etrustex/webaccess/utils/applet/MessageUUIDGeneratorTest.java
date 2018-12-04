package eu.europa.ec.etrustex.webaccess.utils.applet;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.utils.MessageUUIDGenerator;
import org.junit.Test;

public class MessageUUIDGeneratorTest extends AbstractTest {

    @Test
    public void test_randomMessageStatusUUID_should_startWithPrefix() {
        String partyNodeName = "party1";

        //DO THE ACTUAL CALL
        String uuid = MessageUUIDGenerator.randomMessageStatusUUID(partyNodeName);

        assertThat(uuid, startsWith("ETX-MSG-STATUS-"));
        assertThat(uuid, containsString(partyNodeName));
    }

    @Test
    public void test_randomMessageStatusUUID_should_returnDifferentUuid_when_successiveCalled() {
        String partyNodeName = "party1";

        //DO THE ACTUAL CALL
        String uuid1 = MessageUUIDGenerator.randomMessageStatusUUID(partyNodeName);
        String uuid2 = MessageUUIDGenerator.randomMessageStatusUUID(partyNodeName);

        assertThat(uuid1, not(uuid2));
    }

    @Test
    public void test_randomMessageBundleUUID_should_startWithPrefix() {
        String partyNodeName = "party1";

        //DO THE ACTUAL CALL
        String uuid = MessageUUIDGenerator.randomMessageBundleUUID(partyNodeName);

        assertThat(uuid, startsWith("ETX-MSG-BUNDLE-"));
        assertThat(uuid, containsString(partyNodeName));
    }

    @Test
    public void test_randomMessageBundleUUID_should_returnDifferentUuid_when_successiveCalled() {
        String partyNodeName = "party1";

        //DO THE ACTUAL CALL
        String uuid1 = MessageUUIDGenerator.randomMessageBundleUUID(partyNodeName);
        String uuid2 = MessageUUIDGenerator.randomMessageBundleUUID(partyNodeName);

        assertThat(uuid1, not(uuid2));
    }
}