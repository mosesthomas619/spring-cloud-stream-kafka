package com.example.sink.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean(name="objectMapper")
    ObjectMapper returnObjectMapper() {
        return new ObjectMapper();
    }
}
