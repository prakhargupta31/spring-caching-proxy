package com.example.proxy;

import java.util.Map;

public class CachedResponse {
    private byte[] body;
    private Map<String, String> headers;

    public CachedResponse(byte[] body, Map<String, String> headers) {
        this.body = body;
        this.headers = headers;
    }

    public byte[] getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
