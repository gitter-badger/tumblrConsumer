package io.github.tyb.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value="classpath:common.properties")
@Component
public class ConsumerSecret {

    @Value("${tumblr.consumer.key}")
    private String key;

    @Value("${tumblr.consumer.secret}")
    private String secret;

    @Value("${tumblr.consumer.oauth.token}")
    private String oauth_token;

    @Value("${tumblr.consumer.oauth.token_secret}")
    private String oauth_token_secret;

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

    public String getOauth_token() {
        return oauth_token;
    }

    public String getOauth_token_secret() {
        return oauth_token_secret;
    }
}
