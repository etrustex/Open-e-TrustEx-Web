package eu.europa.ec.etrustex.webaccess.web.websocket;

import eu.europa.ec.etrustex.webaccess.web.controller.WebSocketController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Managing connected Websocket peers.
 * This class manages the disconnected WebSocket peers
 */
@Service
public class WebSocketDisconnectionEvent implements ApplicationListener<SessionDisconnectEvent> {

        @Autowired
        private WebSocketController webSocketController;

        @Autowired
        private WebSocketConnections webSocketConnections;

        @Override
        public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
                StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
                String  sessionId = headerAccessor.getSessionId();
                String connectionId=webSocketConnections.getConnectionId(sessionId);
                if(connectionId!=null) {
                        webSocketConnections.removeSessionId(connectionId, sessionId);
                        webSocketController.notifyDisconnection(connectionId);
                }
        }
}