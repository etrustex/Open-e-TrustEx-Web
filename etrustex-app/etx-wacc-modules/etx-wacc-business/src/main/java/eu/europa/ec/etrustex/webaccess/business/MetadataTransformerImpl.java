/**
 *
 */
package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.webaccess.business.api.MetadataTransformer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * @author apladap
 */
@Service
public class MetadataTransformerImpl implements MetadataTransformer {

    Logger logger = Logger.getLogger(MetadataTransformerImpl.class);

    /* (non-Javadoc)
      * @see MetadataTransformer#transform(javax.xml.transform.Source, javax.xml.transform.Source)
      */
    @Override
    public String transform(Source xmlSource, Source xsltSource) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(xsltSource);

            StringWriter writer = new StringWriter();
            transformer.transform(xmlSource, new StreamResult(writer));

            return writer.toString();
        } catch (Exception e) {
            logger.error("Error executing XSLT transformation!", e);
            return "";
        }
    }

}
