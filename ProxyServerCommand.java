// ProxyServerCommand.java
package com.example.proxy;

import org.springframework.boot.SpringApplication;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "caching-proxy", mixinStandardHelpOptions = true, version = "1.0",
        description = "Starts a caching proxy server")
public class ProxyServerCommand implements Runnable {

    @Option(names = "--port", description = "Port number to run the proxy server", required = true)
    private int port;

    @Option(names = "--origin", description = "Origin server URL", required = true)
    private String origin;

    @Override
    public void run() {
        System.setProperty("server.port", String.valueOf(port));
        System.setProperty("proxy.origin", origin);
        SpringApplication.run(CachingProxyApplication.class);
    }
}

