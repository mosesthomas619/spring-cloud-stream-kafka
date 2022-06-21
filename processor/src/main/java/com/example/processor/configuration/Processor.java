package com.example.processor.configuration;

import java.util.function.Function;

import com.example.processor.domain.User;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Processor {

    private static final Logger LOGGER = Logger.getLogger(Processor.class);

    @Bean
    public Function<User, User> process () {
        return input ->
        {
            LOGGER.trace("User Before Processing: " + input.toString());
            int id = input.getId();
            input.setProcessed(true);
            LOGGER.trace("User After Processing: " + input.toString());
            return input;
        };
    }
}
