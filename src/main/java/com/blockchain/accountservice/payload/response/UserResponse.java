package com.blockchain.accountservice.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    public UserResponse(String userId, String username, String email, String firstName, String lastName, String phone) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public UserResponse(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
}
