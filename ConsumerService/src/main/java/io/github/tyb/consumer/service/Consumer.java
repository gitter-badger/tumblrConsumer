package io.github.tyb.consumer.service;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.User;
import io.github.tyb.common.util.ConsumerSecret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class Consumer {

    @Autowired
    private ConsumerSecret consumerSecret;

    private JumblrClient jumblrClient;

    public void consume() {
        this.connect();

        // Write the user's name
        User user = jumblrClient.user();
        System.out.println(user.getName());

        /* And list their blogs */
        for(Blog blog : user.getBlogs()) {
            System.out.println("\t" + blog.getTitle());
        }

        // Like the most recent "lol" tag
        System.out.println("most recent: " + jumblrClient.tagged("lol").get(0));
        jumblrClient.tagged("lol").get(0).like();
    }

    private void connect() {
        // Create a new client
        this.jumblrClient = new JumblrClient(consumerSecret.getKey()
                ,consumerSecret.getSecret());

        jumblrClient.setToken(consumerSecret.getOauth_token()
                , consumerSecret.getOauth_token_secret());
    }



}
