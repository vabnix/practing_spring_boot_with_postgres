package com.vaibhav.spring.data;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String location;

    public static UserResponse fromEntity(UserData userData) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userData.getId());
        userResponse.setFirstName(userData.getFirstName());
        userResponse.setLastName(userData.getLastName());
        userResponse.setLocation(userData.getLocation());
        return userResponse;
    }

    public static UserResponse fromEntityWithFields(UserData userData, List<String> fields) {
        UserResponse userResponse = new UserResponse();
        fields.forEach(field -> {
            switch (field) {
                case "id" -> userResponse.setId(userData.getId());
                case "firstName"-> userResponse.setFirstName(userData.getFirstName());
                case "lastName"-> userResponse.setLastName(userData.getLastName());
                case "location" -> userResponse.setLocation(userData.getLocation());
            }
        });
        return userResponse;
    }
}
