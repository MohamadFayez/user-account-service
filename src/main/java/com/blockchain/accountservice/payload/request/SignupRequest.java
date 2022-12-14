package com.blockchain.accountservice.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String firstName;

    private String lastName;
    @NotBlank
    @NotNull
    @Size(max = 15)
    private String phone;

    @NotBlank
    @Size(min = 3, max = 40)
    private String password;


}
