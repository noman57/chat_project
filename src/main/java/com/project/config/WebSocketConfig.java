package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by abdullah.alnoman on 06.08.17.
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig  extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // chaning the message broker can help scale up by using rabbit mq
        config.enableSimpleBroker("/topic","/user");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
    }




}
