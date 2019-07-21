package io.github.tyb.consumer.service;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.User;
import io.github.tyb.common.util.ConsumerSecret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

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

            //get a post and save to database
            List<Post> posts =  blog.draftPosts();
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

    private void connect() {
        // Create a new client
        this.jumblrClient = new JumblrClient(consumerSecret.getKey()
                ,consumerSecret.getSecret());

        jumblrClient.setToken(consumerSecret.getOauth_token()
                , consumerSecret.getOauth_token_secret());
    }



}
