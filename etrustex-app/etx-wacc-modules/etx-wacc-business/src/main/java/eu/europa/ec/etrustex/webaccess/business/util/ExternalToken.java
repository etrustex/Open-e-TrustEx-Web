package eu.europa.ec.etrustex.webaccess.business.util;

import java.nio.charset.Charset;

public class ExternalToken {

    private final static Charset DEFAULT_CHARSET = Charset.defaultCharset();

    private final String userName;
    private final String browserSessionId;

    public ExternalToken(byte[] token) {
        String strToken = new String(token, DEFAULT_CHARSET);
        int separatorPosition = strToken.indexOf(':');
        if (separatorPosition <= 0) {
            throw new IllegalArgumentException("The given token could not be parsed");
        }
        userName = strToken.substring(0, separatorPosition);
        browserSessionId = strToken.substring(separatorPosition + 1);
    }

    public ExternalToken(String userName, String browserSessionId) {
        this.userName = userName;
        this.browserSessionId = browserSessionId;
    }

    public String getUserName() {
        return userName;
    }

    public String getBrowserSessionId() {
        return browserSessionId;
    }

    public byte[] toToken() {
        return toTokenString().getBytes(DEFAULT_CHARSET);
    }

    public String toTokenString() {
        return userName + ":" + browserSessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExternalToken externalToken = (ExternalToken) o;

        if (userName != null ? !userName.equals(externalToken.userName) : externalToken.userName != null) return false;
        return !(browserSessionId != null ? !browserSessionId.equals(externalToken.browserSessionId) : externalToken.browserSessionId != null);

    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (browserSessionId != null ? browserSessionId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExternalToken{" +
                "userName='" + userName + '\'' +
                ", browserSessionId='" + browserSessionId + '\'' +
                '}';
    }
}
