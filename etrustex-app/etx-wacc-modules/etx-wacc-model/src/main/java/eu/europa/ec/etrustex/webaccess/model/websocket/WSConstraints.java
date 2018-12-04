package eu.europa.ec.etrustex.webaccess.model.websocket;

/**
 * WebSocket constraints.
 * This class hosts the main constraints related to the WebSocket Communication
 */
public  class WSConstraints {

    /**
     * WebSocket Endpoints
     * This enum hosts the endpoints and path constraints used for the WebSocket Communication
     */
    public enum Endpoints {
        ENDPOINT("/wschannel"),
        DESTINATION_PREFIX("/send"),
        SIMPLE_MESSAGE("/notifications"),
        UPLOAD_FILES("/uploadFiles"),
        RECEIVER_PREFIX("/receive");

        private final String text;

        Endpoints(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    /**
     * WebSocket sender category
     * This enum hosts the type of senders who can send a WebSocket message
     */
    public enum ClientType {
        WEBSTART("webstart"),
        BROWSER("browser"),
        SERVER("server");

        private final String text;

        ClientType(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    /**
     * WebSocket message type
     * This enum hosts the message type exchanged during the websocket session
     */
    public enum MessageType {
        UPLOADED("uploaded"),
        WEBSTART_CONNECTED("webstart_connected"),
        DISCONNECT("disconnect");

        private final String text;

        MessageType(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
