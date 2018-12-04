package eu.europa.ec.etrustex.webaccess.model.converters.util;

import javax.json.JsonObjectBuilder;

public interface JsonSerializable {

    JsonObjectBuilder createJsonBuilder();
}
