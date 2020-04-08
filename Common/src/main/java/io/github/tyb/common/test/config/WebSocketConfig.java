package io.github.tyb.common.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
/*
@EnableWebSocketMessageBroker enables WebSocket message handling, backed by a message broker.
 */
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /*
    The configureMessageBroker() method implements the default method in WebSocketMessageBrokerConfigurer
    to configure the message broker.
    It starts by calling enableSimpleBroker() to enable a simple memory-based message broker
    to carry the greeting messages back to the client on destinations prefixed with "/topic".
    It also designates the "/app" prefix for messages that are bound for @MessageMapping-annotated methods.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /*
        to enable a simple memory-based message broker
        to carry the greeting messages back
        to the client on destinations prefixed with /topic.
         */
        config.enableSimpleBroker("/topic"); //giden
        config.setApplicationDestinationPrefixes("/app"); //gelen yani client servera mesaj gonderdiginde bununla baslayacak. neden?
    }

    /*
    The registerStompEndpoints() method registers the "/gs-guide-websocket" endpoint,
    enabling SockJS fallback options so that alternate transports may be used if WebSocket is not available.
    The SockJS client will attempt to connect to "/gs-guide-websocket"
    and use the best transport available (websocket, xhr-streaming, xhr-polling, etc).
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
        The registerStompEndpoints() method registers the /gs-guide-websocket endpoint,
        enabling SockJS fallback options so that alternate transports can be used if WebSocket is not available.
        The SockJS client will attempt to connect to /gs-guide-websocket
        and use the best available transport (websocket, xhr-streaming, xhr-polling, and so on).
         */
        registry.addEndpoint("/gs-guide-websocket")
                //.setAllowedOrigins("http://localhost:8000")
                .setAllowedOrigins("*")
                .withSockJS();
    }

}
