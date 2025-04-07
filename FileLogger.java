package com.example.proxy;

import org.springframework.stereotype.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

@Component  // <-- Add this line
public class FileLogger {

    private final String logFile = "proxy-log.txt";

    public synchronized void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFile, true))) {
            out.println(Instant.now() + " - " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
