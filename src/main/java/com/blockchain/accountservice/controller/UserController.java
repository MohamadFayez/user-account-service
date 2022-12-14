package com.blockchain.accountservice.controller;

import com.blockchain.accountservice.common.APIResponse;
import com.blockchain.accountservice.constant.Constants;
import com.blockchain.accountservice.entity.User;
import com.blockchain.accountservice.payload.mapper.UserMapper;
import com.blockchain.accountservice.payload.request.SignupRequest;
import com.blockchain.accountservice.payload.request.UserRequest;
import com.blockchain.accountservice.payload.response.UserResponse;
import com.blockchain.accountservice.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("${app.config.user-service.base-uri}")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserMapper userMapper;

    @Tag(name = "register user data")
    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping("${app.config.user-service.api.register}")
    public APIResponse registerUser(@Valid @RequestBody SignupRequest registerUserRequest) {
        User user = userMapper.toEntity(registerUserRequest);
        UserResponse userResponse = userService.registerUser(user);
        return APIResponse.builder().code(Constants.OK).data(userResponse).message(Constants.SUCCESS).build();
    }

    @Tag(name = "find user data")
    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping("${app.config.user-service.api.query}")
    public APIResponse findUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.findByEmailOrPhone(userRequest.getEmail(), userRequest.getPhone());
        return APIResponse.builder().code(Constants.OK).data(userResponse).message(Constants.SUCCESS).build();
    }
}
