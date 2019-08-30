package io.github.tyb.consumer.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tumblr.jumblr.types.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class GsonTests {

    @Test
    public void genericGsonDeserialization() {
        //gson.fromJson(responseBody, new TypeToken<T>() {}.getType());
        String jsonString = "[{id = 5, author = \"taha\"}, {age = 20, author = \"yavuz\"}]";
        List<Post> myTypes = fromJSonList(jsonString, Post.class);

        System.out.println("posts: " + myTypes.toString());

    }

    private <T> List<T> fromJSonList(String json, Class<T> myType) {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type collectionType = TypeToken.getParameterized(List.class, myType).getType();
        return gson.fromJson(json, collectionType);
    }
}
