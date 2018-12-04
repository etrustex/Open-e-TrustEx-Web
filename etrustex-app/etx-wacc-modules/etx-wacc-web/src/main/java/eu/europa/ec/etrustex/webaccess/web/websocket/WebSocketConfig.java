package eu.europa.ec.etrustex.webaccess.web.websocket;

import eu.europa.ec.etrustex.webaccess.model.websocket.WSConstraints;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * WebSocket configuration class.
 * This class setup the WebSocket environment
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * This method declares which is the base endpoint for the websocket sessions
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {

        // With setWebSocketEnabled(false) it means that the websocket will be implemented with long polling instead of websocket protocol
        stompEndpointRegistry.addEndpoint(""+ WSConstraints.Endpoints.ENDPOINT).setAllowedOrigins("*").withSockJS().setWebSocketEnabled(false);

    }

    /**
     * This method declares which are the path for sending and receiving WebSocket messages
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config.enableSimpleBroker(""+ WSConstraints.Endpoints.RECEIVER_PREFIX);
        config.setApplicationDestinationPrefixes(""+ WSConstraints.Endpoints.DESTINATION_PREFIX);
    }


    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration){
        registration.setMessageSizeLimit(1024 * 1024);
        registration.setSendBufferSizeLimit(1024 * 1024);
        registration.setSendTimeLimit(120 * 1000);
    }



}
