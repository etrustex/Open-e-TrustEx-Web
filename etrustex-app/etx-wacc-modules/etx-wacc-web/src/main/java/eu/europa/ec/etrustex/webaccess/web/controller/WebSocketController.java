/**
 *
 */
package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.model.websocket.Message;
import eu.europa.ec.etrustex.webaccess.model.websocket.UploadedFilesMessage;
import eu.europa.ec.etrustex.webaccess.model.websocket.WSConstraints;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


/**
 * WebSocket Controller.
 * This class exposes the path to be used to send and receive the WebSocket messages
 */

@Controller
public class WebSocketController {

	private static Logger logger = Logger.getLogger(WebSocketController.class);

	@Autowired
	private SimpMessagingTemplate template;


	/**
	 * This method exposes the path to be called in order to send a generic WebSocket message
	 */
	@MessageMapping("/notifications/{connectionId}")
	@SendTo("/receive/notifications/{connectionId}")
	public Message sendMessage(Message message) throws Exception {
		logger.debug("message received from:+ "+message.getFrom());
		return message;
	}

	/**
	 * This method exposes the path to be called in order to send a WebSocket message with the uploading information
	 */
	@MessageMapping("/uploadFiles/{connectionId}")
	@SendTo("/receive/uploadedFiles/{connectionId}")
	public UploadedFilesMessage sendUploadedFiles(UploadedFilesMessage uploadedFilesMessage) throws Exception {
		logger.debug("message received from:+ "+uploadedFilesMessage.getFrom());
		return uploadedFilesMessage;
	}

	/**
	 * This method send a disconnect notification message to the proper WebSocket Session (based on the connectionId)
	 */
	public void notifyDisconnection(String connectionId) {
		Message message= new Message();
		message.setFrom(WSConstraints.ClientType.SERVER);
		message.setMessageType(WSConstraints.MessageType.DISCONNECT);
		logger.info("disconnected notification sent with c:");
		this.template.convertAndSend(""+WSConstraints.Endpoints.RECEIVER_PREFIX+WSConstraints.Endpoints.SIMPLE_MESSAGE+"/"+connectionId, message);

	}

	/**
	 * This method send a connect notification message to the proper WebSocket Session (based on the connectionId)
	 */
	public void notifyConnection(String connectionId,Message message) {
		logger.info("disconnected notification sent with c:");
		this.template.convertAndSend(""+WSConstraints.Endpoints.RECEIVER_PREFIX+WSConstraints.Endpoints.SIMPLE_MESSAGE+"/"+connectionId, message);

	}
}
