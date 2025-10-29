package com.acme.center.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Entry point for the ACME Learning Center Platform Spring Boot application.
 */
@EnableJpaAuditing
@SpringBootApplication
public class LearningCenterPlatformApplication {

    /**
     * Main method used by the JVM to start the application. Made public so
     * tooling and the Spring Boot repackage goal can reliably detect it.
     */
    public static void main(String[] args) {
        SpringApplication.run(LearningCenterPlatformApplication.class, args);
    }

}
