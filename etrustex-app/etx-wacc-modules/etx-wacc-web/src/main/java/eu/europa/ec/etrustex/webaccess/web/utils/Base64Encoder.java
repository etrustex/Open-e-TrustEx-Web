package eu.europa.ec.etrustex.webaccess.web.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Encoder {
    public static String encode(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }
}
