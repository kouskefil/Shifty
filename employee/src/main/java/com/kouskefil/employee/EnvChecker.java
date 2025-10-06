package com.kouskefil.employee;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvChecker {
    private static final Logger log = LoggerFactory.getLogger(EnvChecker.class);

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String mongoDb;

    @PostConstruct
    public void init() {
        log.info("=== TEST CONFIG ===");
        log.info("Mongo URI : {}", mongoUri);
        log.info("Mongo DB  : {}", mongoDb);
        log.info("===================");
    }
}
