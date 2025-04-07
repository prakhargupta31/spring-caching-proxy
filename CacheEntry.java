package com.example.proxy;

public class CacheEntry {
    private final String response;
    private final long timestamp;
    private final long expirationTime;

    public CacheEntry(String response, long timestamp, long expirationTime) {
        this.response = response;
        this.timestamp = timestamp;
        this.expirationTime = expirationTime;
    }

    public String getResponse() {
        return response;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - timestamp > expirationTime;
    }
}
