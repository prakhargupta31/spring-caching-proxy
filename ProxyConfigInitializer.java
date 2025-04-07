package com.example.proxy;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class ProxyConfigInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private final ProxyConfig config;

    public ProxyConfigInitializer(ProxyConfig config) {
        this.config = config;
    }

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();

        Map<String, Object> props = new HashMap<>();
        props.put("proxy.origin", config.getOrigin());
        props.put("server.port", config.getPort());
        props.put("proxy.expiration", config.getExpiration());

        environment.getPropertySources().addFirst(new MapPropertySource("proxyConfig", props));
    }
}
