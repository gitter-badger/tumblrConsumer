package io.github.tyb.common.test.util.responses;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TumblrDeserializer implements JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return jsonObject;

        //List<String> tags = jsonObject.get("tags").getAsJsonArray();
        /*
        Date date = new Date(
                jsonObject.get("tags").getAsJsonArray();
                jsonObject.get("month").getAsInt(),
                jsonObject.get("day").getAsInt()
        );

        return new UserDate(
                jsonObject.get("name").getAsString(),
                jsonObject.get("email").getAsString(),
                jsonObject.get("isDeveloper").getAsBoolean(),
                jsonObject.get("age").getAsInt(),
                date
        );
        */
    }
}
