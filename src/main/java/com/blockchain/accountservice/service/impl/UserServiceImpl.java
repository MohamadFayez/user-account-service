package com.blockchain.accountservice.service.impl;


import com.blockchain.accountservice.constant.ErrorCodes;
import com.blockchain.accountservice.entity.User;
import com.blockchain.accountservice.exception.BusinessException;
import com.blockchain.accountservice.payload.response.UserResponse;
import com.blockchain.accountservice.repository.UserRepository;
import com.blockchain.accountservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public UserResponse registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new BusinessException(ErrorCodes.USERNAME_TAKEN);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BusinessException(ErrorCodes.EMAIL_TAKEN);
        }
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(String.valueOf(Base64.getEncoder().encode(user.getPassword().getBytes())));

        user = userRepository.save(user);
        return new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhone()
        );
    }

    public UserResponse findByEmailOrPhone(String email, String phone) {
        Optional<User> userOptional = userRepository.findByEmailOrPhone(email, phone);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserResponse(
                    user.getUserId(),
                    user.getUsername(),
                    user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhone()
            );
        } else {
            throw new BusinessException(ErrorCodes.USER_NOT_EXISTS);
        }
    }

}
