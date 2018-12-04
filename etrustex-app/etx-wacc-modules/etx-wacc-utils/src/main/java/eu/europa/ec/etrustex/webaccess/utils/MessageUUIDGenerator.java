package eu.europa.ec.etrustex.webaccess.utils;

import java.util.Random;
import java.util.UUID;

public class MessageUUIDGenerator {
    public static String randomMessageStatusUUID(String partyNodeName) {
        return "ETX-MSG-STATUS-" + partyNodeName + UUID.randomUUID().toString();
    }

    public static String randomMessageBundleUUID(String partyNodeName) {
        return "ETX-MSG-BUNDLE-" + partyNodeName + Long.toString(new Random().nextLong());
    }
}
