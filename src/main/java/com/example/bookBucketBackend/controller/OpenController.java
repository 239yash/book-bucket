package com.example.bookBucketBackend.controller;

import com.example.bookBucketBackend.config.JwtService;
import com.example.bookBucketBackend.dto.model.AuthRequest;
import com.example.bookBucketBackend.entity.UserInfo;
import com.example.bookBucketBackend.service.user.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class OpenController {
    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secured";
    }

    @PostMapping("/addNewUser")
    @Operation(summary = "Add user(Register)", description = "API for adding a new user")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "user details like roles, userId")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @PostMapping("/generateToken")
    @Operation(summary = "Validate user(Login)", description = "API for fetching user token after password validation")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmailAddress(),
                authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmailAddress());
        } else {
            throw new Exception("invalid user request !");
        }
    }
}