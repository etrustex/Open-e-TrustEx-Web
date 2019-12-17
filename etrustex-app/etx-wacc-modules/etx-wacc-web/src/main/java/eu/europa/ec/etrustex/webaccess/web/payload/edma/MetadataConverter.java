package eu.europa.ec.etrustex.webaccess.web.payload.edma;


import org.apache.log4j.Logger;

import javax.json.*;
import javax.json.stream.JsonParsingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class MetadataConverter {

    private static final Logger logger = Logger.getLogger(MetadataConverter.class);

    public ETrustExEdmaMd extractEdmaMessageValues(String jsonData) throws Exception {

        if (jsonData == null) {
            throw new JsonParsingException("null string", null);
        }
        ETrustExEdmaMd messageParams = new ETrustExEdmaMd();
        JsonReader reader = Json.createReader(new StringReader(jsonData));
        JsonObject edmaMetadata = reader.readObject();
        reader.close();
        messageParams.setSubject(edmaMetadata.get("subject") != null ? edmaMetadata.getString("subject") : null);
        messageParams.setLanguage(edmaMetadata.get("language") != null ? edmaMetadata.getString("language") : null);
        messageParams.setFileNumber(edmaMetadata.get("fileNumber") != null ? edmaMetadata.getString("fileNumber") : null);
        messageParams.setInAttentionOf(edmaMetadata.get("inAttentionOf") != null ? edmaMetadata.getString("inAttentionOf") : null);
        messageParams.setReqForInfoNumber(edmaMetadata.get("reqForInfoNumber") != null ? edmaMetadata.getString("reqForInfoNumber") : null);
        messageParams.setOutboundMetaData(extractEdmaOriginatorValues(edmaMetadata.get("outboundMetaData")));
        messageParams.setInboundMetaData(extractEdmaRecipientValues(edmaMetadata.get("inboundMetaData")));
        messageParams.setComment(edmaMetadata.get("comment") != null ? edmaMetadata.getString("comment") : null);

        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(date.getTime());
        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        messageParams.setInboundDate(calendar);
        messageParams.setOutboundDate(calendar);
        return messageParams;

    }


    private ETrustExEdmaMdOutbound extractEdmaOriginatorValues(JsonValue jsonValue) {

        ETrustExEdmaMdOutbound edmaOriginator = new ETrustExEdmaMdOutbound();
        JsonObject jsonObject = null;
        if (jsonValue != null) {
            try {
                jsonObject = (JsonObject) jsonValue;
            } catch (ClassCastException ex) {
                jsonObject = null;
            }
        }
        edmaOriginator.setTo(jsonObject != null && jsonObject.get("registrationNumber") != null ? jsonObject.getString("registrationNumber") : null);

        return edmaOriginator;
    }

    private ETrustExEdmaMdInbound extractEdmaRecipientValues(JsonValue jsonValue) {

        ETrustExEdmaMdInbound edmaRecipient = new ETrustExEdmaMdInbound();
        JsonObject jsonObject = null;
        if (jsonValue != null) {
            try {
                jsonObject = (JsonObject) jsonValue;
            } catch (ClassCastException ex) {
                jsonObject = null;
            }
        }
        edmaRecipient.setTransferStatus("SENT");
        edmaRecipient.setDocuments(jsonObject != null ? createDocuments(jsonObject.get("documents")) : createDocuments(null));
        edmaRecipient.setOnBehalfOf(jsonObject != null ? extractEdmaCompany(jsonObject.get("onBehalfOf")) : extractEdmaCompany(null));
        edmaRecipient.setSender(jsonObject != null ? extractEdmaCompany(jsonObject.get("sender")) : extractEdmaCompany(null));
        edmaRecipient.setFrom(jsonObject != null && jsonObject.get("from") != null ? jsonObject.getString("from") : null);
        edmaRecipient.setInstrument(jsonObject != null && jsonObject.get("instrument") != null ? jsonObject.getString("instrument") : null);
        edmaRecipient.setDistributionList(jsonObject != null && jsonObject.get("distributionList") != null ? jsonObject.getString("distributionList") : null);
        edmaRecipient.setYourReference("?????");

        boolean isEmptyOnBehalfOf = edmaRecipient.getOnBehalfOf().getEMail() == null || edmaRecipient.getOnBehalfOf().getEMail().trim().isEmpty();
        edmaRecipient.setEMail(!isEmptyOnBehalfOf ? edmaRecipient.getOnBehalfOf().getEMail() : edmaRecipient.getSender().getEMail());

        return edmaRecipient;
    }

    private ETrustExEdmaMdCompany extractEdmaCompany(JsonValue jsonValue) {

        ETrustExEdmaMdCompany onBehalf = new ETrustExEdmaMdCompany();
        JsonObject jsonObject = null;
        if (jsonValue != null) {
            try {
                jsonObject = (JsonObject) jsonValue;
            } catch (ClassCastException ex) {
                jsonObject = null;
            }
        }
        onBehalf.setCity(jsonObject != null && jsonObject.get("city") != null ? jsonObject.getString("city") : null);
        onBehalf.setCompany(jsonObject != null && jsonObject.get("company") != null ? jsonObject.getString("company") : null);
        onBehalf.setContactPerson(jsonObject != null && jsonObject.get("contactPerson") != null ? jsonObject.getString("contactPerson") : null);
        onBehalf.setCountry(jsonObject != null && jsonObject.get("country") != null ? jsonObject.getString("country") : null);
        onBehalf.setEMail(jsonObject != null && jsonObject.get("eMail") != null ? jsonObject.getString("eMail") : null);
        onBehalf.setNationalRegNumber(jsonObject != null && jsonObject.get("nationalRegNumber") != null ? jsonObject.getString("nationalRegNumber") : null);
        onBehalf.setPhone(jsonObject != null && jsonObject.get("phone") != null ? jsonObject.getString("phone") : null);
        onBehalf.setPosition(jsonObject != null && jsonObject.get("position") != null ? jsonObject.getString("position") : null);
        onBehalf.setStreet(jsonObject != null && jsonObject.get("street") != null ? jsonObject.getString("street") : null);
        onBehalf.setZip(jsonObject != null && jsonObject.get("zip") != null ? jsonObject.getString("zip") : null);

        return onBehalf;
    }


    private Documents createDocuments(JsonValue jsonValue) {
        Documents documents = new Documents();
        List<ETrustExEdmaMdDocument> eTrustExEdmaMdDocuments = documents.getETrustExEdmaMdDocument();
        JsonArray documentsList;
        if (jsonValue != null) {
            try {
                documentsList = (JsonArray) jsonValue;
            } catch (ClassCastException ex) {
                documentsList = Json.createArrayBuilder().build();
            }
            for (int i = 0; i < documentsList.size(); i++) {
                JsonObject document = documentsList.getJsonObject(i);
                ETrustExEdmaMdDocument eTrustExEdmaMdDocument = new ETrustExEdmaMdDocument();
                eTrustExEdmaMdDocument.setConfidential(document.get("confidential") != null ? document.getString("confidential") : null);
                eTrustExEdmaMdDocument.setDocumentComment(document.get("documentComment") != null ? document.getString("documentComment") : null);
                eTrustExEdmaMdDocument.setOriginalFilename(document.get("originalFilename") != null ? document.getString("originalFilename") : null);
                eTrustExEdmaMdDocument.setFile(document.get("file") != null ? document.getString("file") : null);
                eTrustExEdmaMdDocument.setPath(document.get("path") != null ? document.getString("path") : null);
                eTrustExEdmaMdDocument.setFileReferenceId(document.get("fileReferenceId") != null ? document.getString("fileReferenceId") : null);
                eTrustExEdmaMdDocuments.add(eTrustExEdmaMdDocument);
            }
        }
        return documents;
    }

    public static ETrustExEdmaMd unmarshallMetadata(String metadata) {
        ETrustExEdmaMd res = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ETrustExEdmaMd.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            InputStream stream = new ByteArrayInputStream(metadata.getBytes(StandardCharsets.UTF_8));
            res = (ETrustExEdmaMd)jaxbUnmarshaller.unmarshal(stream);

        } catch (JAXBException e) {
            logger.error("Unmarshaller error :"+e);
        }

        return res;
    }
}

