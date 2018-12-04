/**
 *
 */
package eu.europa.ec.etrustex.webaccess.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author apladap
 */
public class XMLHelper {

    private final static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    static {
        builderFactory.setNamespaceAware(true);
    }

    public static String jaxbToXmlString(Object jaxbObject) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(jaxbObject.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        // uncomment if you need to debug and to see the XML result
        //		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        marshaller.marshal(jaxbObject, sw);

        return sw.toString();
    }

    public static Document jaxbToXmlDocument(Object jaxbObject) throws JAXBException, ParserConfigurationException {
        JAXBContext context = JAXBContext.newInstance(jaxbObject.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        // uncomment if you need to debug and to see the XML result
        //        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Document doc = builderFactory.newDocumentBuilder().newDocument();

        marshaller.marshal(jaxbObject, doc);

        return doc;
    }

    /**
     * Unmarshaller.
     * @param xmlString XML as string.
     * @param c Destination type.
     * @return XML as object.
     * @throws JAXBException
     */
    public static Object xmlToJaxbObject(String xmlString, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(new InputSource(new StringReader(xmlString)));
    }

    public static String nodeElementToXmlString(Node node) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();

        StringWriter writer = new StringWriter();
        tf.newTransformer().transform(
                new DOMSource(node),
                new StreamResult(writer));

        return writer.toString();
    }

    public static Document getEmptyDocument() throws ParserConfigurationException {
        DocumentBuilder db = builderFactory.newDocumentBuilder();
        return db.newDocument();
    }

    public static Document stringToDocument(String xmlString) throws Exception {
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        return documentBuilder.parse(new InputSource(new StringReader(xmlString)));
    }

    public static String formatXml(Source source, boolean pretty) {
        if (source == null) {
            return "";
        }
        try {
            //set-up the transformer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            if (pretty) {
                transformerFactory.setAttribute("indent-number", 4);
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            }

            StreamResult xmlOutput = new StreamResult(new StringWriter());
            transformer.transform(source, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }

    public static boolean isElementValueAvailable(Element el) {
        boolean isValueAttributeSet = false;
        if (el != null) {
            String valueAttribute = el.getAttribute("value");
            if (valueAttribute != null && !valueAttribute.isEmpty()) {
                isValueAttributeSet = true;
            }
        }
        return isValueAttributeSet;
    }
}
