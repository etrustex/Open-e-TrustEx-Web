package eu.europa.ec.etrustex.webaccess.model.converters.util;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonStructure;
import javax.json.JsonWriter;
import javax.xml.bind.DatatypeConverter;
import java.io.StringWriter;
import java.util.List;

public class JsonUtil {

    public static String objectToJson(JsonSerializable serializableObject) {
        return jsonStructureToString(serializableObject.createJsonBuilder().build());
    }

    public static <T extends JsonSerializable> String arrayToJson(List<T> serializableArray) {
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(T elem: serializableArray) {
            arrayBuilder.add(elem.createJsonBuilder());
        }

        return jsonStructureToString(arrayBuilder.build());
    }

    public static String jsonStructureToString(JsonStructure jsonStructure) {
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter writer = Json.createWriter(stringWriter)) {
            writer.write(jsonStructure);
        }

        return stringWriter.getBuffer().toString();
    }

    public static String toBase64(byte[] byteArray) {
        return byteArray != null ? DatatypeConverter.printBase64Binary(byteArray) : null;

    }

    public static byte[] fromBase64(String base64ByteArray) {
        return base64ByteArray != null ? DatatypeConverter.parseBase64Binary(base64ByteArray) : null;

    }
}
