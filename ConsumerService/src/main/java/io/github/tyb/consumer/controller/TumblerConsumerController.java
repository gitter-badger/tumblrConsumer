package io.github.tyb.consumer.controller;

import io.github.tyb.consumer.service.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// To elaborate api to manage from security or request routing or api maintenance perspectives.
@RequestMapping("/api")
public class TumblerConsumerController {
    @Autowired
    private Consumer tumblrConsumer;

    @RequestMapping(method = RequestMethod.GET, value="/tumblr/posts")
    @ResponseBody
    public void getPosts()   {
        tumblrConsumer.consume();
    }
}