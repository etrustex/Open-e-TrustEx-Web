package eu.europa.ec.etrustex.webaccess.web.payload.edma;

import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import eu.europa.ec.etrustex.webaccess.web.payload.PayloadBuilder;
import org.apache.log4j.Logger;

import javax.json.stream.JsonParsingException;

import static eu.europa.ec.etrustex.webaccess.utils.XMLHelper.jaxbToXmlString;

public class EdmaPayloadBuilder implements PayloadBuilder {

    private static final Logger logger = Logger.getLogger(EdmaPayloadBuilder.class);

    public String buildPayload(String jsonData) {
        try {
            MetadataConverter metadataConverter = new MetadataConverter();
            ETrustExEdmaMd edmaMetadata = metadataConverter.extractEdmaMessageValues(jsonData);
            return jaxbToXmlString(edmaMetadata);
        } catch (JsonParsingException e) {
            String message = "Edma metadata is not a valid json string";
            logger.error(message);
            throw new EtxException(message, e);
        } catch (Exception e) {
            throw new EtxException("Failed to load EDMA metadata", e);
        }
    }
}

