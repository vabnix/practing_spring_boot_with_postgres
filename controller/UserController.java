package com.vaibhav.spring.controller;

import com.vaibhav.spring.data.UserData;
import com.vaibhav.spring.data.UserResponse;
import com.vaibhav.spring.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public UserData createUser(@RequestBody UserData userData) {
        logger.info("Creating user with data: {}", userData);
        return userService.createUser(userData);
    }

    @GetMapping("/user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users Found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<UserData> getUsers() {
        logger.info("Getting all users");
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public UserData getUserById(@PathVariable String id) {
        logger.info("Getting user with id: {}", id);
        return userService.getUserById(id);
    }

    @PutMapping("/user/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public UserData updateUser(@PathVariable String id, UserData userData) {
        logger.info("Updating user with id: {}", id);
        return userService.updateUser(id, userData);
    }

    @DeleteMapping("/user/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void deleteUser(@PathVariable String id) {
        logger.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
    }


    @GetMapping
    public List<UserResponse> getUsers(@RequestParam(required = false) List<String> fields) {
        logger.info("Another approach to get all users by fields");
        List<UserData> userDataList = userService.getUsers();

        if(fields == null || fields.isEmpty()) {
            return userDataList
                    .stream()
                    .map(UserResponse::fromEntity)
                    .collect(Collectors.toList());
        }

        return userDataList
                .stream()
                .map(userData -> UserResponse
                        .fromEntityWithFields(userData, fields))
                .collect(Collectors.toList());
    }
}
