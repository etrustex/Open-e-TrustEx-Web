
package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.business.mail.PayloadMailContentData;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;

public interface PayloadMailContentExtractor {

    /**
     * The extractPayloadMailContentDataFromPayload will extract specific data from the payload to be used
     * in the payload templates used for the emails to be send
     *
     * @param payload
     * @return The payloadMailContentData object containing the information retrieved from the payload
     */
    PayloadMailContentData extractPayloadMailContentDataFromPayload(String payload);
}
