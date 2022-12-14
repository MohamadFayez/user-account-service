package com.blockchain.accountservice.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserRequest {

    @Size(max = 15)
    private String phone;

    @Size(max = 50)
    @Email
    private String email;
}
