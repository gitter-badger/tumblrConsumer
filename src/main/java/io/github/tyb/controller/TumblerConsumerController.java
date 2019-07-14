package io.github.tyb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TumblerConsumerController {

    @RequestMapping(method = RequestMethod.GET, value="/tumblr/posts")
    @ResponseBody
    public List<?> getPosts() {
        return null;
    }

}