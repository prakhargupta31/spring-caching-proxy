package com.example.proxy;

import org.springframework.stereotype.Component;

@Component
public class OriginConfig {
    private String origin;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
