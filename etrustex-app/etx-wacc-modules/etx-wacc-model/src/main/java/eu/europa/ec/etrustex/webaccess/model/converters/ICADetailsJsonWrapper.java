package eu.europa.ec.etrustex.webaccess.model.converters;

import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonObjectBuilderDecorator;
import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonSerializable;
import eu.europa.ec.etrustex.webaccess.model.vo.ICADetails;

import javax.json.JsonObjectBuilder;

public class ICADetailsJsonWrapper implements JsonSerializable {

    private ICADetails icaDetails;

    public ICADetailsJsonWrapper(ICADetails icaDetails) {
        this.icaDetails = icaDetails;
    }

    @Override
    public JsonObjectBuilder createJsonBuilder() {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilderDecorator();
        if (icaDetails != null) {
            jsonObjectBuilder
                    .add("isSignatureRequired", icaDetails.getIsSignatureRequired())
                    .add("isEncryptionRequired", icaDetails.getIsEncryptionRequired())
                    .add("X509SubjectName", icaDetails.getX509SubjectName());
        }

        return jsonObjectBuilder;
    }
}
