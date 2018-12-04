package eu.europa.ec.etrustex.webaccess.model.websocket;


/**
 * WebSocket base message.
 * This class represents the basics information exchanged in a WebSocket communication between the WebStart and
 * the WebPage
 */
public class Message {

    private WSConstraints.ClientType from;
    private WSConstraints.MessageType messageType;

    public WSConstraints.ClientType getFrom() {
        return from;
    }

    public void setFrom(WSConstraints.ClientType from) {
        this.from = from;
    }

    public WSConstraints.MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(WSConstraints.MessageType messageType) {
        this.messageType = messageType;
    }
}
