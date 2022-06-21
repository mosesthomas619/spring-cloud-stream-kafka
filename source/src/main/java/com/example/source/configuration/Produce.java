package com.example.source.configuration;

import java.util.function.Supplier;

import com.example.source.domain.User;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Produce {
    private static final Logger LOGGER = Logger.getLogger(Produce.class);

    @Bean
    public Supplier<User> source() {
        return () -> {
            User randomUser = User.getRandomUser();
            System.out.println("randomUser-----------------------" + randomUser);
            LOGGER.info(randomUser.toString());
            return randomUser;
        };
    }
}
