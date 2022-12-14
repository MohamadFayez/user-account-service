package com.blockchain.accountservice.service;

import com.blockchain.accountservice.entity.User;
import com.blockchain.accountservice.payload.response.UserResponse;

public interface UserService {

    UserResponse registerUser(User user);

    UserResponse findByEmailOrPhone(String email, String phone);
}
