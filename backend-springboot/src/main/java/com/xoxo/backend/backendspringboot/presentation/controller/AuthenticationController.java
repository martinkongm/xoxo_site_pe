package com.xoxo.backend.backendspringboot.presentation.controller;

import com.xoxo.backend.backendspringboot.presentation.dto.auth.AuthCreateUserRequest;
import com.xoxo.backend.backendspringboot.presentation.dto.auth.AuthLoginRequest;
import com.xoxo.backend.backendspringboot.presentation.dto.auth.AuthResponse;
import com.xoxo.backend.backendspringboot.service.implementation.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUser) {
        return new ResponseEntity<>(userDetailService.createUser(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        return new ResponseEntity<>(userDetailService.loginUser(userRequest), HttpStatus.OK);
    }
}
