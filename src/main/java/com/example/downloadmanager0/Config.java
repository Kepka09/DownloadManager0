package com.example.downloadmanager0;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * Spring Boot configuration class for the download manager application.
 * This class is responsible for setting up the application's configuration,
 * including the creation and initialization of beans required by the application.
 */
@SpringBootConfiguration
public class Config {
    /**
     * Creates and returns a Peer bean.
     * This method sets up a default Peer instance for the application.
     * The Peer is initialized with the address 'localhost:8080', and empty lists
     * for files to download and known peers.
     *
     * @return A Peer instance with default settings.
     */
    @Bean
    public Peer getCurrentPeer() {
        return new Peer(
                "localhost:8080",
                new ArrayList<>(),
                new HashSet<>());
    }
}
