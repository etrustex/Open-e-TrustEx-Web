package eu.europa.ec.etrustex.webaccess.model.converters;

import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonObjectBuilderDecorator;
import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonUtil;
import eu.europa.ec.etrustex.webaccess.model.vo.ExtendedCertificateData;
import org.apache.log4j.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//TODO: implement AttributeConverter<CertificateData, String> when JPA2.1 is available
//@Converter
public class ExtendedCertificateDataConverter {

    private static final Logger logger = Logger.getLogger(ExtendedCertificateDataConverter.class);
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a Z");
        }
    };

    private static final String PUBLIC_KEY = "publicKey";
    private static final String PUBLIC_KEY_ALGORITHM = "publicKeyAlgorithm";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String SUBJECT_DN = "subjectDn";
    private static final String SIGNATURE_ALGORITHM = "signatureAlgorithm";
    private static final String ISSUER_DN = "issuerDn";
    private static final String VERSION = "version";
    private static final String SERIAL_NUMBER = "serialNumber";

    private static final ExtendedCertificateDataConverter INSTANCE = new ExtendedCertificateDataConverter();

    public static ExtendedCertificateDataConverter getInstance() {
        return INSTANCE;
    }

    private ExtendedCertificateDataConverter() {
    }

    public String convertToDatabaseColumn(ExtendedCertificateData ecd) {
        String jsonEncoded = null;
        if (ecd != null) {
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilderDecorator();
            jsonObjectBuilder.add(START_DATE, DATE_FORMATTER.get().format(ecd.getStartDate()))
                    .add(END_DATE, DATE_FORMATTER.get().format(ecd.getEndDate()))
                    .add(SUBJECT_DN, ecd.getSubjectDN())
                    .add(SIGNATURE_ALGORITHM, ecd.getSignatureAlgorithm())
                    .add(ISSUER_DN, ecd.getIssuerDN())
                    .add(VERSION, ecd.getVersion())
                    .add(SERIAL_NUMBER, ecd.getSerialNumber())
                    .add(PUBLIC_KEY_ALGORITHM, ecd.getPublicKey().getAlgorithm())
                    .add(PUBLIC_KEY, JsonUtil.toBase64(ecd.getPublicKey().getEncoded()));
            jsonEncoded = JsonUtil.jsonStructureToString(jsonObjectBuilder.build());
        }
        return jsonEncoded;
    }

    public ExtendedCertificateData convertToEntityAttribute(String jsonData) {
        ExtendedCertificateData certificateData = null;
        if (jsonData != null) {
            JsonObject jsonObject;
            try (JsonReader reader = Json.createReader(new StringReader(jsonData))) {
                jsonObject = reader.readObject();
            }

            String publicKeyAlgorithm = jsonObject.getString(PUBLIC_KEY_ALGORITHM);
            byte[] encodedPublicKey = JsonUtil.fromBase64(jsonObject.getString(PUBLIC_KEY));

            PublicKey publicKey = null;
            try {
                KeyFactory keyFactory = KeyFactory.getInstance(publicKeyAlgorithm);
                X509EncodedKeySpec KeySpec = new X509EncodedKeySpec(encodedPublicKey);
                publicKey = keyFactory.generatePublic(KeySpec);
            } catch (Exception e) {
                logger.error("Unable to read the public key " + e, e);
            }

            certificateData = populateCommonAttributes(publicKey, jsonObject);
        }

        return certificateData;
    }

    public ExtendedCertificateData convertToLightEntityAttribute(String jsonData) {
        ExtendedCertificateData certificateData = null;
        if (jsonData != null) {
            JsonObject jsonObject;
            try (JsonReader reader = Json.createReader(new StringReader(jsonData))) {
                jsonObject = reader.readObject();
            }

            certificateData = populateCommonAttributes(null, jsonObject);
        }

        return certificateData;
    }

    private ExtendedCertificateData populateCommonAttributes(PublicKey publicKey, JsonObject jsonObject) {
        ExtendedCertificateData certificateData = null;

        try {
            certificateData = new ExtendedCertificateData(publicKey,
                    jsonObject.getString(SUBJECT_DN),
                    jsonObject.getString(ISSUER_DN),
                    DATE_FORMATTER.get().parse(jsonObject.getString(START_DATE)),
                    DATE_FORMATTER.get().parse(jsonObject.getString(END_DATE)),
                    jsonObject.getString(SERIAL_NUMBER),
                    jsonObject.getString(SIGNATURE_ALGORITHM),
                    jsonObject.getString(VERSION)
            );
        } catch (ParseException e) {
            logger.error("Could not parse certificate start/end date " + e, e);
            throw new IllegalStateException("Invalid date format!", e);
        }

        return certificateData;
    }
}
