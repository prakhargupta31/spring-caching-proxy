// CacheService.java
package com.example.proxy;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {
    private final ConcurrentHashMap<String, ResponseEntity<String>> cache = new ConcurrentHashMap<>();

    public ResponseEntity<String> get(String key) {
        return cache.get(key);
    }

    public void put(String key, ResponseEntity<String> response) {
        cache.put(key, response);
    }

    public void clear() {
        cache.clear();
    }
}
