package com.example.sink.configuration;

import java.util.function.Consumer;

import com.example.sink.domain.User;
import com.example.sink.service.PersistService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Consume {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private PersistService persistService;


    @Bean
    public Consumer<String> consumeData() {
        return input -> {
            if(input != null ) {
                try {
                    persistService.saveToMongo(objectMapper.readValue(input, User.class));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        };
    }


}

