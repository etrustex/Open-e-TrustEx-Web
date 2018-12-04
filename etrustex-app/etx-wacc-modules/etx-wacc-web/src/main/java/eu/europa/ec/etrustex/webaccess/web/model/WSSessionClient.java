package eu.europa.ec.etrustex.webaccess.web.model;

import eu.europa.ec.etrustex.webaccess.model.websocket.WSConstraints;

/**
 * WebSocket session client information.
 * This class keep the client infomation during the websocket session
 */
public class WSSessionClient {

    private String wsSession;
    private WSConstraints.ClientType clientType;

    public String getWsSession() {
        return wsSession;
    }

    public void setWsSession(String wsSession) {
        this.wsSession = wsSession;
    }

    public WSConstraints.ClientType getClientType() {
        return clientType;
    }

    public void setClientType(WSConstraints.ClientType clientType) {
        this.clientType = clientType;
    }

    /**
     * Overriden because the instances of this class are used in a SET Collection
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof WSSessionClient)) {
            return false;
        }
        WSSessionClient wsSessionClient = (WSSessionClient) o;
        return (wsSession.equals(wsSessionClient.getWsSession()));
    }

    /**
     * Overriden because the instances of this class are used in a SET Collection
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + wsSession.hashCode();
        return result;
    }
}
