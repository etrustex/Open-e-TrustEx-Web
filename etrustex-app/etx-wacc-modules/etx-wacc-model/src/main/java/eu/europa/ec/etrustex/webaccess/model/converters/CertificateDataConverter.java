package eu.europa.ec.etrustex.webaccess.model.converters;

import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonObjectBuilderDecorator;
import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonUtil;
import eu.europa.ec.etrustex.webaccess.model.vo.CertificateData;
import org.apache.log4j.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.persistence.Converter;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//TODO: implement AttributeConverter<CertificateData, String> when JPA2.1 is available
@Converter
public class CertificateDataConverter {
    private static final Logger logger = Logger.getLogger(CertificateDataConverter.class);
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a Z");
        }
    };

    private static final String SERIAL_NUMBER = "serialNumber";
    private static final String COMMON_NAME = "commonName";
    private static final String ORGANIZATION_UNIT = "organizationUnit";
    private static final String ORGANIZATION_NAME = "organizationName";
    private static final String LOCALITY = "locality";
    private static final String STATE = "state";
    private static final String COUNTRY = "country";
    private static final String EMAIL = "email";
    private static final String THUMB_PRINT = "thumbPrint";
    private static final String EXPIRY_DATE = "expiryDate";

    private static final CertificateDataConverter INSTANCE = new CertificateDataConverter();

    public static CertificateDataConverter getInstance() {
        return INSTANCE;
    }

    private CertificateDataConverter() {
    }

    public String convertToDatabaseColumn(CertificateData certificateData) {
        String certificateDataString = null;
        if (certificateData != null) {
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilderDecorator();
            jsonObjectBuilder.add(SERIAL_NUMBER, certificateData.getSerialNumber())
                    .add(COMMON_NAME, certificateData.getCommonName())
                    .add(ORGANIZATION_UNIT, certificateData.getOrganizationUnit())
                    .add(ORGANIZATION_NAME, certificateData.getOrganizationName())
                    .add(LOCALITY, certificateData.getLocality())
                    .add(STATE, certificateData.getState())
                    .add(COUNTRY, certificateData.getCountry())
                    .add(EMAIL, certificateData.getEmail())
                    .add(THUMB_PRINT, certificateData.getThumbPrint())
                    .add(EXPIRY_DATE, DATE_FORMATTER.get().format(certificateData.getExpiryDate()));
            certificateDataString = JsonUtil.jsonStructureToString(jsonObjectBuilder.build());

        }

        return certificateDataString;
    }

    public CertificateData convertToEntityAttribute(String jsonData) {
        CertificateData certificateData = null;
        if (jsonData != null) {
            JsonObject jsonObject;
            try (JsonReader reader = Json.createReader(new StringReader(jsonData))) {
                jsonObject = reader.readObject();
            }

            Date expiryDate;
            try {
                expiryDate = DATE_FORMATTER.get().parse(jsonObject.getString(EXPIRY_DATE));
            } catch (ParseException e) {
                logger.error("Could not parse certificate expiry date");
                throw new IllegalStateException("Invalid date format!", e);
            }

            certificateData = new CertificateData(jsonObject.getJsonNumber(SERIAL_NUMBER).bigIntegerValue(),
                    jsonObject.getString(COMMON_NAME),
                    jsonObject.getString(ORGANIZATION_UNIT),
                    jsonObject.getString(ORGANIZATION_NAME),
                    jsonObject.getString(LOCALITY),
                    jsonObject.getString(STATE),
                    jsonObject.getString(COUNTRY),
                    jsonObject.getString(EMAIL),
                    jsonObject.getString(THUMB_PRINT),
                    expiryDate
            );
        }
        return certificateData;
    }
}
