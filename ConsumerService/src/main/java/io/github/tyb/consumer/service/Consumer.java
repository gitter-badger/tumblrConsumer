package io.github.tyb.consumer.service;

import com.google.gson.*;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.responses.JsonElementDeserializer;
import com.tumblr.jumblr.types.*;
import io.github.tyb.common.test.gson.GenericGson;
import io.github.tyb.common.test.util.ConsumerSecret;
import io.github.tyb.consumer.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Component
@Profile("dev")
public class Consumer {

    @Autowired
    private ConsumerSecret consumerSecret;

    private JumblrClient jumblrClient;

    @Autowired
    PostRepository postRepository;

    public void consume() {
        this.connect();

        // Write the user's name
        User user = jumblrClient.user();
        System.out.println(user.getName());

        /* And list their blogs */
        for(Blog blog : user.getBlogs()) {
            System.out.println("\t" + blog.getTitle());

            //get a post and save to database
            List<Post> posts =  blog.draftPosts();

            //Jumblr'da ResponseWrapper Gson ile response'den Object oluşturuyor generic bir biçimde.
            //Generic yapının gereği olarak Post supertype.
            //TextPost gibi xxxPost tipinde subtype'lar var.
            //List<TextPost> textPosts =  blog.draftPosts();
            String typeName = posts.get(0).getType().getValue();
            String className = typeName.substring(0, 1).toUpperCase() + typeName.substring(1) + "Post";
            try {
                Class<?> clz = Class.forName("com.tumblr.jumblr.types." + className);
            } catch (ClassNotFoundException e) { }

            Post post = posts.get(0);
            if(post instanceof TextPost) {
                System.out.println("textPost: " + ((TextPost) post).getTitle());
                System.out.println("textPost: " + ((TextPost) post).getBody());

                JsonSerializer<Post> postJsonSerializer =  new JsonSerializer<Post>() {

                    @Override
                    public JsonElement serialize(Post post, Type type, JsonSerializationContext jsonSerializationContext) {
                        JsonObject jsonPost = new JsonObject();
                        jsonPost.addProperty("postid", post.getId());
                        return jsonPost;
                    }
                };

                JsonSerializer<TextPost> textPostJsonSerializer =  new JsonSerializer<TextPost>() {

                    @Override
                    public JsonElement serialize(TextPost textPost, Type type, JsonSerializationContext jsonSerializationContext) {
                        JsonObject jsonPost = new JsonObject();
                        jsonPost.addProperty("textPostBody", textPost.getBody());
                        return jsonPost;
                    }
                };

                //Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Gson gson = new GsonBuilder().
                                    //registerTypeAdapter(TextPost.class, new PostInstanceCreator()).
                                    //registerTypeAdapter(JsonElement.class, new JsonElementDeserializer()).
                                    //registerTypeAdapter(JsonElement.class, new JsonElementSerializer()).
                        //registerTypeAdapter(TextPost.class, new PostSerializer()).
                        //registerTypeAdapter(Post.class, new PostDeserializer()).
                                    registerTypeAdapter(Post.class, postJsonSerializer).
                                    registerTypeAdapter(TextPost.class, textPostJsonSerializer).
                                    setPrettyPrinting().
                                    setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).
                                    //serializeNulls().
                                    create();

                /*
                 * Stackoverflowerror için potansiyel sorunlar:
                 * 1. Post objesinde Enum var.
                 * 2. Circular reference yok görünüyor.
                 * 3.
                 *
                 * Potansiyel çözümler:
                 * 1. Custom serialization to extract only some fields to test
                 * 2.
                 */
                String jsonStr = gson.toJson(post/*, Post.class*/);

                //java.lang.StackOverflowError
                //	at com.google.gson.internal.bind.TypeAdapters$16.write(TypeAdapters.java:406)
                //Bu sorun circular ref ya da lombok yani constructor, hashcode, tostring serializable(interface) den kaynaklanıyor.
                //String jsonStr = gson.toJson(post);

                System.out.println(jsonStr);

                //io.github.tyb.consumer.domain.types.post.Post savedPost = new Gson().fromJson(jsonStr, io.github.tyb.consumer.domain.types.post.Post.class);

                //postRepository.save(savedPost);
            }

            System.out.println("authorid:" + posts.get(0).getAuthorId());
            System.out.println("blogname:" + posts.get(0).getBlogName());
            System.out.println("date:" + posts.get(0).getDateGMT());
            System.out.println("format:" + posts.get(0).getFormat());
            System.out.println("posturl:" + posts.get(0).getPostUrl());
            System.out.println("reblogged from name:" + posts.get(0).getRebloggedFromName());
            System.out.println("shorturl:" + posts.get(0).getShortUrl());
            System.out.println("slug:" + posts.get(0).getSlug());
            System.out.println("sourcetitle:" + posts.get(0).getSourceTitle());
            System.out.println("sourceurl:" + posts.get(0).getSourceUrl());
            System.out.println("state:" + posts.get(0).getState());
            System.out.println("notecount:" + posts.get(0).getNoteCount());
            System.out.println("type:" + posts.get(0).getType());

        }

        // Like the most recent "lol" tag
        System.out.println("most recent: " + jumblrClient.tagged("lol").get(0));
        jumblrClient.tagged("lol").get(0).like();

    }


    private class PostInstanceCreator implements InstanceCreator<TextPost> {
        public TextPost createInstance(Type type) {
            return new TextPost();
        }
    }

    private class JsonElementDeserializer implements JsonDeserializer<JsonElement> {
        @Override
        public JsonElement deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            return je;
        }
    }

    private class JsonElementSerializer implements JsonSerializer<JsonElement> {
        @Override
        public JsonElement serialize(JsonElement je, Type type, JsonSerializationContext jdc) throws JsonParseException {
            return je;
        }
    }

    private class PostDeserializer implements JsonDeserializer<Object> {

        @Override
        public Object deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            JsonObject jobject = je.getAsJsonObject();
            String typeName = jobject.get("type").getAsString();
            String className = typeName.substring(0, 1).toUpperCase() + typeName.substring(1) + "Post";
            try {
                Class<?> clz = Class.forName("com.tumblr.jumblr.types." + className);
                return jdc.deserialize(je, clz);
            } catch (ClassNotFoundException e) {
                System.out.println("deserialized post for unknown type: " + typeName);
                return jdc.deserialize(je, UnknownTypePost.class);
            }
        }

    }

    private class PostSerializer implements JsonSerializer<Post> {
        @Override
        public JsonElement serialize(Post o, Type type, JsonSerializationContext jsc) {

            //JsonObject jobject = jsc.serialize(o.)
            String typeName = o.getType().getValue();
            //String typeName = jobject.get("type").getAsString();
            String className = typeName.substring(0, 1).toUpperCase() + typeName.substring(1) + "Post";
            try {
                Class<?> clz = Class.forName("com.tumblr.jumblr.types." + className);
                return jsc.serialize(o, clz);
            } catch (ClassNotFoundException e) {
                System.out.println("deserialized post for unknown type: " + typeName);
                return jsc.serialize(o, UnknownTypePost.class);
            }
        }
    }

    public void consumeRaw() {
        // api.tumblr.com/v2/blog/{blog-identifier}/info?api_key={key}
        /*
        {
   "meta": {
      "status": 200,
      "msg": "OK"
   },
   "response": {
      "blog": {
         "title": "David's Log",
         "posts": 3456,
         "name": "david",
         "url": "https:\/\/david.tumblr.com\/",
         "updated": 1308953007,
         "description": "<p><strong>Mr. Karp<\/strong> is tall and skinny, with
            unflinching blue eyes a mop of brown hair.\r\n
            He speaks incredibly fast and in complete paragraphs.</p>",
         "ask": true,
         "ask_anon": false,
         "likes": 12345
      }
   }
}
         */
        String uri = "https://api.tumblr.com/v2/blog/tyb.tumblr.com/info?api_key=" + consumerSecret.getKey();
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);

        //https://api.tumblr.com/v2/blog/peacecorps.tumblr.com/posts/text?notes_info=true
        String uri2 = "https://api.tumblr.com/v2/blog/tyb.tumblr.com/posts/text?api_key=" + consumerSecret.getKey();
        String result2 = restTemplate.getForObject(uri2, String.class);
        System.out.println(result2);

        GenericGson ggson = new GenericGson();
        System.out.println("ggson: " + ggson.getResponse(io.github.tyb.common.test.pojo.types.Post[].class, result2).get().toString());


        /*
        String fetchUrl = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";
        fetchUrl = fetchUrl+this.screenName;
        URLConnection urlConnection =  new URL(fetchUrl).openConnection();
        urlConnection.connect();

        JsonReader reader = new JsonReader(
                new InputStreamReader(urlConnection.getInputStream()));
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        JsonArray tweetsJson = rootElement.getAsJsonArray();

        List timeline = new ArrayList();
        GenericGson ggson = new GenericGson();
        ggson.getResponse(Post.class, response);

         */

        /*
        Gson myGson = new Gson();
        for ( JsonElement tweetElement : tweetsJson){
            Tweet myTweet = myGson.fromJson(tweetElement, Tweet.class);
            timeline.add(myTweet);
        }

         */
    }

    private void connect() {
        // Create a new client
        this.jumblrClient = new JumblrClient(consumerSecret.getKey()
                ,consumerSecret.getSecret());

        jumblrClient.setToken(consumerSecret.getOauth_token()
                , consumerSecret.getOauth_token_secret());
    }



}
