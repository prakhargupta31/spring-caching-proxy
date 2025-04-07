package com.example.proxy;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@CommandLine.Command(name = "caching-proxy", mixinStandardHelpOptions = true, version = "1.0",
        description = "Runs an HTTP caching proxy server.")
public class CachingProxyApplication implements Runnable {

    @Option(names = "--origin", description = "Origin server to proxy requests to", required = true)
    private String origin;

    @Option(names = "--port", description = "Port to run the proxy on", required = true)
    private int port;

    @Option(names = "--expiration", description = "Cache expiration time in milliseconds (default: 60000)")
    private long expiration = 60000;

    public static void main(String[] args) {
        new CommandLine(new CachingProxyApplication()).execute(args);
    }

    @Override
    public void run() {
        ProxyConfig config = new ProxyConfig(origin, port, expiration);
        SpringApplication app = new SpringApplication(CachingProxyApplication.class);
        app.addInitializers(new ProxyConfigInitializer(config));
        app.run();
    }
}
