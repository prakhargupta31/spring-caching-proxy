#  Caching Proxy Server (Spring Boot + Picocli)

A command-line based HTTP caching proxy built with **Spring Boot 3.4.4** and **Picocli**.  
It intercepts requests, caches responses with expiration, and logs all cache hits and misses to a file.


#  Features

- Supports **GET**, **POST**, **PUT**, and **DELETE** requests
- Intelligent **response caching** with configurable expiration
- File-based logging** of cache hits and misses (with timestamps)
- Loads **proxy configuration** and **origin server** details from a JSON config file
- Built-in **CLI interface** using Picocli
- Minimal dependencies and easy to set up

---

caching-proxy1/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.proxy/
│   │   │       ├── CachedResponse.java
│   │   │       ├── CacheEntry.java
│   │   │       ├── CacheManager.java
│   │   │       ├── CacheService.java
│   │   │       ├── CachingProxyApplication.java
│   │   │       ├── FileLogger.java
│   │   │       ├── OriginConfig.java
│   │   │       ├── ProxyConfig.java
│   │   │       ├── ProxyConfigInitializer.java
│   │   │       ├── ProxyController.java
│   │   │       └── ProxyServerCommand.java
│   ├── main/resources/
│   └── test/java/
├── target/
├── HELP.md
├── Maven Dependencies
└── JRE System Library [JavaSE-17]


---

# How to Run

# 1. Build the Project

mvn clean package

java -jar target/caching-proxy1-0.0.1-SNAPSHOT.jar \
  --origin=https://jsonplaceholder.typicode.com \
  --port=8080 \
  --expiration=60000

---

Tech Stack

Java 17
Spring Boot 3.4.4
Picocli for CLI parsing
Maven

