/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.web.utils;

import eu.europa.ec.etrustex.webaccess.model.Message;
import org.springframework.stereotype.Service;

/**
 * @author apladap
 *
 */
@Service
public class MessageUtils {
	
	public void limitToMessageMaxSize(Message draftMessage){
		if(draftMessage.getContent().length() > 4000){
		  String content = draftMessage.getContent().substring(0, 4000);
		  draftMessage.setContent(content);
		}

		if (draftMessage.getSubject().length() > 4000) {
			draftMessage.setSubject(draftMessage.getSubject().substring(0, 4000));
		}
	}

}
