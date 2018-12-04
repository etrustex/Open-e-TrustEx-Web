package eu.europa.ec.etrustex.webaccess.business.util;

import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
class ExternalTokenBuilder {

    private static final Logger logger = Logger.getLogger(ExternalTokenBuilder.class);

    @Autowired
    protected HttpServletRequest request;

    public ExternalToken extractToken(String clientToken) {

        try {
            byte[] parsedClientToken = Hex.decodeHex(clientToken.toCharArray());

            return new ExternalToken(parsedClientToken);
        } catch (Exception e) {
            throw new EtxException("Unable to extract the External Token!", e);
        }
    }

    public ExternalToken buildRequestToken() {
        String userName = request.getUserPrincipal().getName();
        String sessionId = request.getSession().getId();

        return new ExternalToken(userName, sessionId);
    }

    public String buildClientToken(ExternalToken requestToken) {
        try {
            return Hex.encodeHexString(requestToken.toToken());
        } catch (Exception e) {
            throw new EtxException("Unable to build the client Token!", e);
        }
    }
}
