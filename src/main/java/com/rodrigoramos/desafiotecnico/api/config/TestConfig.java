package com.rodrigoramos.desafiotecnico.api.config;

import com.rodrigoramos.desafiotecnico.api.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    private final DatabaseService dbService;

    @Autowired
    public TestConfig(DatabaseService dbService) {
        this.dbService = dbService;
    }

    @Value("${data.carga-inicial}")
    private String pathStr;

    @Bean
    public boolean instantiateDatabase() {
        dbService.instantiateDatabase(pathStr);
        dbService.generateReport();
        return true;
    }

}