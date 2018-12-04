package eu.europa.ec.etrustex.webaccess.web.websocket;

import eu.europa.ec.etrustex.webaccess.model.websocket.Message;
import eu.europa.ec.etrustex.webaccess.model.websocket.WSConstraints;
import eu.europa.ec.etrustex.webaccess.web.controller.WebSocketController;
import eu.europa.ec.etrustex.webaccess.web.model.WSSessionClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * Managing connected Websocket peers.
 * This class manages the connected WebSocket peers
 */
@Service
public class WebSocketConnectionEvent implements ApplicationListener<SessionConnectEvent> {

    private static Logger logger = Logger.getLogger(WebSocketConnectionEvent.class);

    @Autowired
    private WebSocketController webSocketController;

    @Autowired
    private WebSocketConnections webSocketConnections;

    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());

        if(sha.getCommand() == null) {
            return;
        }
        String sessionId = sha.getSessionId();

        if(sha.getNativeHeader("connectionId")!=null) {
            switch (sha.getCommand()) {
                case CONNECT:
                    logger.debug("STOMP Connect [sessionId: " + sessionId + "]");
                    String connectionId = sha.getNativeHeader("connectionId").get(0);
                    WSConstraints.ClientType client_type = WSConstraints.ClientType.valueOf(sha.getNativeHeader("client_type").get(0).toUpperCase());
                    WSSessionClient wsSessionClient = new WSSessionClient();
                    wsSessionClient.setWsSession(sessionId);
                    wsSessionClient.setClientType(client_type);
                    webSocketConnections.addSessionId(connectionId, wsSessionClient);
                    if(client_type==WSConstraints.ClientType.WEBSTART){
                        Message message= new Message();
                        message.setFrom(WSConstraints.ClientType.SERVER);
                        message.setMessageType(WSConstraints.MessageType.WEBSTART_CONNECTED);
                        webSocketController.notifyConnection(connectionId,message);

                    }
                    break;
                default:
                    break;

            }
        }

    }
}
