package eu.europa.ec.etrustex.webaccess.model.converters;

import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonObjectBuilderDecorator;
import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonSerializable;
import eu.europa.ec.etrustex.webaccess.model.vo.CertificateData;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageSignatureData;

import javax.json.JsonObjectBuilder;

public class MessageSignatureDataJsonWrapper implements JsonSerializable {

    private MessageSignatureData msgSignData;

    public MessageSignatureDataJsonWrapper(MessageSignatureData msgSignData) {
        this.msgSignData = msgSignData;
    }

    @Override
    public JsonObjectBuilder createJsonBuilder() {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilderDecorator();
        if (msgSignData != null) {
            jsonObjectBuilder
                    .add("isSignatureValid", msgSignData.isSignatureValid());

            final CertificateData certData = msgSignData.getCertificateData();
            if (certData != null) {
                JsonObjectBuilder certJsonObjectBuilder = new JsonObjectBuilderDecorator()
                        .add("serialNumber", certData.getSerialNumber())
                        .add("commonName", certData.getCommonName())
                        .add("organizationUnit", certData.getOrganizationUnit())
                        .add("organizationName", certData.getOrganizationName())
                        .add("locality", certData.getLocality())
                        .add("state", certData.getState())
                        .add("country", certData.getCountry())
                        .add("email", certData.getEmail())
                        .add("thumbPrint", certData.getThumbPrint());

                jsonObjectBuilder.add("certificate", certJsonObjectBuilder);
            }
        }

        return jsonObjectBuilder;

    }
}
