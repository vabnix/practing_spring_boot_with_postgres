package com.vaibhav.spring.service;

import com.vaibhav.spring.configuration.GlobalExceptionHandler;
import com.vaibhav.spring.data.UserData;
import com.vaibhav.spring.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserData createUser(UserData userData) {
        if(userData == null) {
            logger.error("User data cannot be null");
            throw new IllegalArgumentException("User data cannot be null");
        } else {
            logger.info("Creating user with data: {}", userData);
            return userRepository.save(userData);
        }
    }

    public List<UserData> getUsers() {
        logger.info("Checking Repository to get all users information");
        return userRepository.findAll();
    }

    public UserData getUserById(String id) {
        logger.info("Checking Repository to get user details for id: {}", id);
        return userRepository.findById(id).orElseThrow(()->new GlobalExceptionHandler.ResourceNotFoundException("User not found"));
    }

    public UserData updateUser(String id, UserData userData) {
        if(userData == null) {
            logger.error("User data cannot be null for updating the user information");
            throw new IllegalArgumentException("User data cannot be null");
        }
        // find the user id if exist then update the user data
        UserData existingUserData = userRepository.findById(id)
                .orElseThrow(()->new GlobalExceptionHandler.ResourceNotFoundException("User not found"));
        if(existingUserData == null) {
            logger.error("User not found");
            throw new IllegalArgumentException("User not found");
        } else {
            existingUserData.setId(userData.getId());
            existingUserData.setFirstName(userData.getFirstName());
            existingUserData.setLastName(userData.getLastName());
            existingUserData.setLocation(userData.getLocation());
        }
        return userRepository.save(existingUserData);
    }

    public void deleteUser(String id) {
        logger.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }
}
