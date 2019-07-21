package io.github.tyb.common.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.tyb.common.pojo.Response;
import io.github.tyb.common.pojo.types.TextPost;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericGson {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Response<TextPost> createGeneric() {
        TextPost textPost = new TextPost();
        textPost.setBody("body");
        textPost.setTitle("title");
        Response<TextPost> type2 = new Response<TextPost>(textPost, null, null);

        //type2.set(textPost);

        // Serialization of generic User type to json
        Type Type2 = new TypeToken<Response<TextPost>>() {}.getType();
        String postJson = gson.toJson(type2, Type2);
        System.out.println(postJson);

        //De-serialization of generic User type to json
        Response<TextPost> response = gson.fromJson(postJson, Type2);
        System.out.println(response.get().toString());
        return null;
    }

    private Type getType(Class<?> rawClass, Class<?> parameter) {

        return new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] {parameter};
            }

            @Override
            public Type getRawType() {
                return rawClass;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    public <T> Response<T> getResponse(/* parameters for retrieval ,*/ final Class<T> dataClass
            , final String jsonResponse) {
        //json
        //final String rawResponse = getRawResponse(); // ... e.g. via some http client library

        //return gson.fromJson(rawResponse, getType(Response.class, dataClass));
        return gson.fromJson(jsonResponse, getType(Response.class, dataClass));
    }
}
