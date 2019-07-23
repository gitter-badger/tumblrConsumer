package io.github.tyb.consumer.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.TextPost;
import com.tumblr.jumblr.types.User;
import io.github.tyb.common.gson.GenericGson;
import io.github.tyb.common.util.ConsumerSecret;
import io.github.tyb.consumer.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
                String jsonStr = new Gson().toJson(post);
                io.github.tyb.consumer.domain.types.post.Post savedPost = new Gson().fromJson(jsonStr, io.github.tyb.consumer.domain.types.post.Post.class);
                postRepository.save(savedPost);
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
        System.out.println("ggson: " + ggson.getResponse(io.github.tyb.common.pojo.types.Post[].class, result2).get().toString());


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
        ggson.getResponse(io.github.tyb.common.pojo.types.Post.class, response);

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
