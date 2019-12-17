package eu.europa.ec.etrustex.webaccess.web.model;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.vo.BusinessCustomViewName;
import eu.europa.ec.etrustex.webaccess.model.vo.IcaDetailsVO;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EdmaMessage;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.ModelMap;

import java.io.IOException;

import static eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler.*;

public class MessageFormBeanFactory {

    private static final Logger logger = Logger.getLogger(MessageFormBeanFactory.class);

    public static MessageFormBean create(BusinessCustomViewName customView, ModelMap modelMap) {
        MessageFormBean formBean;
        if (customView != null && customView.equals(BusinessCustomViewName.EDMA)) {
            formBean = new EdmaMessageFormBean((EdmaMessage) modelMap.get(EDMA_MESSAGE_ATTR));
        } else {
            formBean = new MessageFormBean();
        }

        Message message = (Message) modelMap.get(MESSAGE_ATTR);
        formBean.setMessage(message);

        IcaDetailsVO icaDetailsVO = (IcaDetailsVO) modelMap.get(MESSAGE_ICA);
        formBean.setIcaDetailsVO(icaDetailsVOToJson(icaDetailsVO));

        //formBean.setEncodedSignature((String) modelMap.get("encodedSignature"));
        formBean.setXmlDataToSign((String) modelMap.get("xmlDataToSign"));
        formBean.setXmlExtractedSignature((String) modelMap.get("xmlExtractedSignature"));
        formBean.setXmlSignedBundle((String) modelMap.get("xmlSignedBundle"));

        return formBean;
    }

    private static String icaDetailsVOToJson(IcaDetailsVO icaDetailsVO) {
        String result = "";

        if (icaDetailsVO != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                result = mapper.writeValueAsString(icaDetailsVO).replace("\"", "\\\"");
            } catch (IOException e) {
                logger.warn("Unable to convert to JSON ICA details for sender: " + icaDetailsVO.getSenderParty() + ", receiver: " + icaDetailsVO.getReceiverParty());
            }
        }

        return result;
    }
}
