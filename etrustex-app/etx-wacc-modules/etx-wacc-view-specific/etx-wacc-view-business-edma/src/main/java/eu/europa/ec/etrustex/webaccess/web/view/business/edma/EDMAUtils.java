package eu.europa.ec.etrustex.webaccess.web.view.business.edma;

import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Metadata;
import eu.europa.ec.etrustex.webaccess.model.vo.AttachmentMetadata;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.w3c.dom.CharacterData;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class EDMAUtils {

    private static Logger logger = Logger.getLogger(EDMAUtils.class);

    public EdmaMessage fetchEdmaMessage(Message message, List<Attachment> binaryAttachments, Metadata metadata) throws Exception {
        EdmaMessage edmaMessage = new EdmaMessage();

        message.setAttachments(binaryAttachments);
        if (metadata != null) {
            String payloadXml = metadata.getContent();
            edmaMessage = convertXMLToEdma(payloadXml);
        }
        return edmaMessage;
    }

    public EdmaMessage convertXMLToEdma(String payloadXml) throws Exception {

        EdmaMessage edmaMessage = new EdmaMessage();

        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(payloadXml));

            Document doc = db.parse(is);

            //ETrustExEdmaMd elements
            NodeList nodeETrustExEdmaMd = doc.getElementsByTagName("ETrustExEdmaMd");

            for (int i = 0; i < nodeETrustExEdmaMd.getLength(); i++) {
                Element element = (Element) nodeETrustExEdmaMd.item(i);

                NodeList reqForInfoNumber = element.getElementsByTagName("reqForInfoNumber");
                Element line = (Element) reqForInfoNumber.item(0);
                edmaMessage.setMsgInfoNo(getCharacterDataFromElement(line));

                NodeList inAttentionOf = element.getElementsByTagName("inAttentionOf");
                line = (Element) inAttentionOf.item(0);
                edmaMessage.setMsgInAttentionOf(getCharacterDataFromElement(line));

                NodeList file = element.getElementsByTagName("fileNumber");
                line = (Element) file.item(0);
                edmaMessage.setMsgFileNo(getCharacterDataFromElement(line));

                NodeList distributionList = element.getElementsByTagName("distributionList");
                line = (Element) distributionList.item(0);
                edmaMessage.setMsgDistributionList(getCharacterDataFromElement(line));


                //ETrustExEdmaMdInbound elements
                NodeList ETrustExEdmaMdInbound = doc.getElementsByTagName("inboundMetaData");

                for (int k = 0; k < ETrustExEdmaMdInbound.getLength(); k++) {
                    Element element2 = (Element) ETrustExEdmaMdInbound.item(k);

                    NodeList documents = element2.getElementsByTagName("documents");
                    Element line2 = (Element) documents.item(0);
                    //edmaMessage.setRegistrationNumber(getCharacterDataFromElement(line2));


                    //ETrustExEdmaMdDocument elements
                    NodeList ETrustExEdmaMdDocument = doc.getElementsByTagName("ETrustExEdmaMdDocument");
                    List<AttachmentMetadata> aml = new ArrayList<>();
                    for (int l = 0; l < ETrustExEdmaMdDocument.getLength(); l++) {
                        Element element3 = (Element) ETrustExEdmaMdDocument.item(l);
                        AttachmentMetadata attachmentMetadata = new AttachmentMetadata();

                        NodeList confidential = element3.getElementsByTagName("confidential");
                        Element line3 = (Element) confidential.item(0);
                        if (line3 != null) {
                            boolean isConfidential = "true".equals(getCharacterDataFromElement(line3));
                            attachmentMetadata.setConfidential(isConfidential);
                        }

                        NodeList documentComment = element3.getElementsByTagName("documentComment");
                        line3 = (Element) documentComment.item(0);
                        attachmentMetadata.setComment(getCharacterDataFromElement(line3));

                        NodeList filename = element3.getElementsByTagName("file");
                        line3 = (Element) filename.item(0);
                        attachmentMetadata.setFilename(getCharacterDataFromElement(line3));

                        NodeList path = element3.getElementsByTagName("path");
                        line3 = (Element) path.item(0);
                        attachmentMetadata.setPath(getCharacterDataFromElement(line3));

                        NodeList fileReferenceId = element3.getElementsByTagName("fileReferenceId");
                        line3 = (Element) fileReferenceId.item(0);
                        attachmentMetadata.setFileReferenceId(getCharacterDataFromElement(line3));

                        aml.add(attachmentMetadata);
                    }
                    edmaMessage.setAttachmentMetadataList(aml);


                    //ETrustExEdmaMdCompany elements
                    NodeList ETrustExEdmaMdCompanyRecipient = doc.getElementsByTagName("onBehalfOf");

                    for (int l = 0; l < ETrustExEdmaMdCompanyRecipient.getLength(); l++) {
                        Element element3 = (Element) ETrustExEdmaMdCompanyRecipient.item(l);

                        NodeList company = element3.getElementsByTagName("company");
                        Element line3 = (Element) company.item(0);
                        edmaMessage.setOnBehalfCompanyName(getCharacterDataFromElement(line3));

                        NodeList street = element3.getElementsByTagName("street");
                        line3 = (Element) street.item(0);
                        edmaMessage.setOnBehalfStreet(getCharacterDataFromElement(line3));

                        NodeList city = element3.getElementsByTagName("city");
                        line3 = (Element) city.item(0);
                        edmaMessage.setOnBehalfCity(getCharacterDataFromElement(line3));

                        NodeList phone = element3.getElementsByTagName("phone");
                        line3 = (Element) phone.item(0);
                        edmaMessage.setOnBehalfPhone(getCharacterDataFromElement(line3));

                        NodeList email = element3.getElementsByTagName("eMail");
                        line3 = (Element) email.item(0);
                        edmaMessage.setOnBehalfEmail(getCharacterDataFromElement(line3));

                        NodeList nationalRegNumber = element3.getElementsByTagName("nationalRegNumber");
                        line3 = (Element) nationalRegNumber.item(0);
                        edmaMessage.setOnBehalfRegNo(getCharacterDataFromElement(line3));

                        NodeList country = element3.getElementsByTagName("country");
                        line3 = (Element) country.item(0);
                        edmaMessage.setOnBehalfCountry(getCharacterDataFromElement(line3));

                        NodeList position = element3.getElementsByTagName("position");
                        line3 = (Element) position.item(0);
                        edmaMessage.setOnBehalfPosition(getCharacterDataFromElement(line3));

                        NodeList zip = element3.getElementsByTagName("zip");
                        line3 = (Element) zip.item(0);
                        edmaMessage.setOnBehalfZip(getCharacterDataFromElement(line3));

                        NodeList contactPerson = element3.getElementsByTagName("contactPerson");
                        line3 = (Element) contactPerson.item(0);
                        edmaMessage.setOnBehalfContactPerson(getCharacterDataFromElement(line3));
                    }


                    //ETrustExEdmaMdCompany elements
                    NodeList ETrustExEdmaMdCompanySender = doc.getElementsByTagName("sender");

                    for (int m = 0; m < ETrustExEdmaMdCompanySender.getLength(); m++) {
                        Element element4 = (Element) ETrustExEdmaMdCompanySender.item(m);

                        NodeList company = element4.getElementsByTagName("company");
                        Element line4 = (Element) company.item(0);
                        edmaMessage.setSenderOrganisationName(getCharacterDataFromElement(line4));

                        NodeList street = element4.getElementsByTagName("street");
                        line4 = (Element) street.item(0);
                        edmaMessage.setSenderStreet(getCharacterDataFromElement(line4));

                        NodeList city = element4.getElementsByTagName("city");
                        line4 = (Element) city.item(0);
                        edmaMessage.setSenderCity(getCharacterDataFromElement(line4));

                        NodeList phone = element4.getElementsByTagName("phone");
                        line4 = (Element) phone.item(0);
                        edmaMessage.setSenderPhone(getCharacterDataFromElement(line4));

                        NodeList email = element4.getElementsByTagName("eMail");
                        line4 = (Element) email.item(0);
                        edmaMessage.setSenderEmail(getCharacterDataFromElement(line4));

                        NodeList nationalRegNumber = element4.getElementsByTagName("nationalRegNumber");
                        line4 = (Element) nationalRegNumber.item(0);
                        edmaMessage.setSenderRegNumber(getCharacterDataFromElement(line4));

                        NodeList country = element4.getElementsByTagName("country");
                        line4 = (Element) country.item(0);
                        edmaMessage.setSenderCountry(getCharacterDataFromElement(line4));

                        NodeList position = element4.getElementsByTagName("position");
                        line4 = (Element) position.item(0);
                        edmaMessage.setSenderPosition(getCharacterDataFromElement(line4));

                        NodeList zip = element4.getElementsByTagName("zip");
                        line4 = (Element) zip.item(0);
                        edmaMessage.setSenderZip(getCharacterDataFromElement(line4));

                        NodeList contactPerson = element4.getElementsByTagName("contactPerson");
                        line4 = (Element) contactPerson.item(0);
                        edmaMessage.setSenderContactPerson(getCharacterDataFromElement(line4));
                    }

                    NodeList instrument = element2.getElementsByTagName("instrument");
                    line2 = (Element) instrument.item(0);
                    edmaMessage.setMsgInstrument(getCharacterDataFromElement(line2));

                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("Exception occurred while reading the payload xml with error cause: " + e.getCause());
            throw new Exception(e);
        }

        return edmaMessage;
    }


    private String getCharacterDataFromElement(Element e) {
        if (e != null) {
            Node child = e.getFirstChild();
            if (child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                return cd.getData();
            }
        }
        return "";
    }
}
 