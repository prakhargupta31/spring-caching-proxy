package com.example.proxy;

public class ProxyConfig {
    private final String origin;
    private final int port;
    private final long expiration;

    public ProxyConfig(String origin, int port, long expiration) {
        this.origin = origin;
        this.port = port;
        this.expiration = expiration;
    }

    public String getOrigin() {
        return origin;
    }

    public int getPort() {
        return port;
    }

    public long getExpiration() {
        return expiration;
    }
}
