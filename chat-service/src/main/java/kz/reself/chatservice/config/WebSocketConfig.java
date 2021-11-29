package kz.reself.chatservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/secured/history", "/secured/reply");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry
                .addEndpoint("/secured/ws")
                .setAllowedOrigins("https://resod.perfectproperty.info", "https://resod.perfectproperty.info:80", "https://resod.perfectproperty.info:8080", "http://resod.perfectproperty.info:8080", "http://185.255.79.19/", "https://185.255.79.19:8080/", "http://185.255.79.19:8080/",
                        "http://localhost:4201", "http://localhost:4200", "http://localhost")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Nullable
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        try {
                            final String email = request.getURI().getQuery();

                            return () -> email;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                }).withSockJS();
    }
}
