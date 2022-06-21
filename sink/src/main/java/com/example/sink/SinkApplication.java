package com.example.sink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.example.sink.repository")
public class SinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SinkApplication.class, args);
    }

}
