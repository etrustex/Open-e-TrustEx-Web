package eu.europa.ec.etrustex.webaccess.model.converters.util;

import javax.json.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonObjectBuilderDecorator implements JsonObjectBuilder {

    private JsonObjectBuilder jsonObjectBuilder;

    public JsonObjectBuilderDecorator() {
        this.jsonObjectBuilder = Json.createObjectBuilder();
    }

    @Override
    public JsonObjectBuilder add(String key, JsonValue jsonValue) {
        if (jsonValue != null) {
            jsonObjectBuilder.add(key, jsonValue);
        }
        return this;
    }

    @Override
    public JsonObjectBuilder add(String key, String value) {
        if (value != null) {
            jsonObjectBuilder.add(key, value);
        }
        return this;
    }

    @Override
    public JsonObjectBuilder add(String key, BigInteger value) {
        if (value != null) {
            jsonObjectBuilder.add(key, value);
        }
        return this;
    }

    @Override
    public JsonObjectBuilder add(String key, BigDecimal value) {
        if (value != null) {
            jsonObjectBuilder.add(key, value);
        }
        return this;
    }

    @Override
    public JsonObjectBuilder add(String key, int value) {
        jsonObjectBuilder.add(key, value);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String key, long value) {
        jsonObjectBuilder.add(key, value);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String key, double value) {
        jsonObjectBuilder.add(key, value);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String key, boolean value) {
        jsonObjectBuilder.add(key, value);
        return this;
    }

    @Override
    public JsonObjectBuilder addNull(String key) {
        jsonObjectBuilder.addNull(key);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String key, JsonObjectBuilder jsonObjectBuilder) {
        jsonObjectBuilder.add(key, jsonObjectBuilder);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String key, JsonArrayBuilder jsonArrayBuilder) {
        jsonObjectBuilder.add(key, jsonArrayBuilder);
        return this;
    }

    @Override
    public JsonObject build() {
        return jsonObjectBuilder.build();
    }
}
