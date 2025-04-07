package com.example.proxy;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Instant;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ProxyController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>();

    @Value("${proxy.origin}")
    private String origin;

    @Value("${proxy.expiration}")
    private long expiration;

    @Autowired
    private FileLogger fileLogger;

    @RequestMapping("/**")
    public ResponseEntity<String> proxyRequest(HttpServletRequest request, @RequestBody(required = false) String body) throws IOException {
        String targetUrl = extractTargetUrl(request);
        String cacheKey = request.getMethod() + ":" + targetUrl;

        // Handle supported HTTP methods
        HttpMethod httpMethod;
        try {
            httpMethod = HttpMethod.valueOf(request.getMethod());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Unsupported HTTP method");
        }

        // Check cache
        CacheEntry cached = cache.get(cacheKey);
        if (cached != null && !cached.isExpired()) {
            fileLogger.log("CACHE HIT: " + cacheKey);
            return ResponseEntity.ok(cached.getResponse());
        }

        // Forward the request
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            headers.set(header, request.getHeader(header));
        }

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(targetUrl, httpMethod, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                cache.put(cacheKey, new CacheEntry(response.getBody(), Instant.now().toEpochMilli(), expiration));
                fileLogger.log("CACHE MISS: " + cacheKey + " -> Cached");
            } else {
                fileLogger.log("NON-200 RESPONSE: " + cacheKey + " -> " + response.getStatusCode());
            }
            return response;
        } catch (Exception e) {
            fileLogger.log("ERROR: " + cacheKey + " -> " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error forwarding request: " + e.getMessage());
        }
    }

    private String extractTargetUrl(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        return origin + uri + (query != null ? "?" + query : "");
    }
}
