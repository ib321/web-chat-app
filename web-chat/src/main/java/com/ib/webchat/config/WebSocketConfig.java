package com.ib.webchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic/", "/queue/");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/greeting").setAllowedOrigins("http://localhost:4200");
		// registry.addEndpoint("/shockjs").setAllowedOrigins("*").withSockJS();
		// registry.addEndpoint("/shockjs").setAllowedOriginPatterns("*");
		// registry.addEndpoint("/shockjs").setAllowedOriginPatterns("*").withSockJS();
		// registry.addEndpoint("/shockjs").setAllowedOrigins("https://findaiforyou.web.app").withSockJS();
		registry.addEndpoint("/shockjs").setAllowedOrigins("http://localhost:4200").withSockJS();
	}

}