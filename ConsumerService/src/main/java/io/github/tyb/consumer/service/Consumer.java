package io.github.tyb.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.TextPost;
import com.tumblr.jumblr.types.User;
import io.github.tyb.common.test.gson.GenericGson;
import io.github.tyb.common.test.util.ConsumerSecret;
import io.github.tyb.consumer.repository.PostRepository;
import io.github.tyb.consumer.repository.TextPostRepository;
import io.github.tyb.consumer.utils.ConsumeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
//@Profile("dev")
@Slf4j
public class Consumer {

    @Autowired
    private ConsumerSecret consumerSecret;

    private JumblrClient jumblrClient;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TextPostRepository textPostRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SimpMessagingTemplate messagingTemplate; //simple degil simp :)

    @Autowired
    //private ConsumeUtils<Post, String, Integer> consumeUtils;
    private ConsumeUtils<Post, String, Long> consumeUtils;

    public void consume() {
        this.connect();

        User user = jumblrClient.user();
        System.out.println(user.getName());

        //https://api.tumblr.com/v2/blog/tyb.tumblr.com/posts/draft

        String startTs = "1999-11-27T16:30:00.21234Z";
        String stopTs = "2099-11-27T16:30:00.21234Z";
        Map<String, Long> requestOptions = new HashMap<String, Long>();
        //Map<String, Integer> requestOptions = new HashMap<String, Integer>();
        //requestOptions.put("limit", 20);

        ///posts/draft — Retrieve Draft Posts icin Request Parameter offset/limit degil; before_id
        //requestOptions.put("offset", 40); //DAMN! READ THE API!
        requestOptions.put("before_id", Long.MAX_VALUE); //DAMN! READ THE API!


        //user.getBlogs().get(0).draftPosts(requestOptions);

        //"id" -> "614716618088562688"
        //"id" -> "611090076806627328"

        //requestOptions.put("before", (int) Instant.parse(startTs).toEpochMilli()); //stop timestamp
        ///requestOptions.put("after", (int) Instant.parse(stopTs).toEpochMilli()); //start timestamp

        //Query query = new Query();
        //Function<Map<String, Integer>, List<Post>> requestFn = q -> {

        //Function<Map<String, Integer>, List<Post>> requestFn = q -> {
        Function<Map<String, Long>, List<Post>> requestFn = q -> {
            return user.getBlogs().get(0).draftPosts(q);
        };
        Stream<Post> postStream = consumeUtils.getPagingQueryStream(requestOptions
                                                                    ,requestFn
                                                                    ,r -> r.get(r.size() - 1)
                                                                    ,r -> requestOptions.put("before_id", r.getId()));
                                                                    //,r -> requestOptions.put("offset", r));
        //user.getBlogs().stream()
                //TODO: make parallel processing for other types.
                //TODO: resimler sadece okuma amacli kullanilacagindan Mongo ya da Cassandra ya yazilabilir.
        //        .flatMap(blog -> blog.draftPosts(requestOptions).stream())
        postStream
                .filter(TextPost.class::isInstance)
                .map(TextPost.class::cast)
                .forEach(textPost -> {
                    log.info(textPost.getTitle());
                    log.info(textPost.getBody());
                    log.info(textPost.getType().getValue());
                    try {
                        String postStr = objectMapper.writeValueAsString(textPost);
                        io.github.tyb.consumer.domain.types.post.TextPost myPost = objectMapper.readValue(postStr, io.github.tyb.consumer.domain.types.post.TextPost.class);
                        //postRepository.save(myPost);
                        log.info(myPost.getBody());
                        log.info(myPost.getTitle());
                        log.info(myPost.getAuthor());
                        textPostRepository.save(myPost);

                        //TODO: split to another service or make threaded or async.
                        //the server will push a greeting into a queue to which the client is subscribed.
                        messagingTemplate.convertAndSend("/topic/progress", myPost);
                        //messagingTemplate.convertAndSend("/topic/progress", MyMessage.builder().text("mesaj...").build());

                    } catch (Exception ex) {
                        log.error("can not be able to convert Jumblr type to my entity.");
                    }

                });
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
        this.jumblrClient = new JumblrClient(consumerSecret.getKey(),consumerSecret.getSecret());

        jumblrClient.setToken(consumerSecret.getOauth_token()
                , consumerSecret.getOauth_token_secret());
    }

}
