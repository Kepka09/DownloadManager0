package com.example.downloadmanager0;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootConfiguration
public class Config {
    @Bean
    public Peer getCurrentPeer() {
        return new Peer(
                "localhost:8080",
                new ArrayList<>(),
                new HashSet<>());
    }
}
