package com.example.sink.service;

import com.example.sink.configuration.Consume;
import com.example.sink.domain.User;
import com.example.sink.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = Logger.getLogger(Consume.class);

    public void saveToMongo(User user) {
        try {
            user.setSaved(true);
                userRepository.save(user);
            LOGGER.info("User with ID: " + user.getId() + " successfully saved to the DB.");
        } catch (Exception e) {
            LOGGER.error("Exception occurred while inserting in DB, {} ",e.getCause());
        }
    }
}
