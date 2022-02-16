package com.assessment.retailwebsite.controller;

import com.assessment.retailwebsite.payload.request.RegisterUserRequest;
import com.assessment.retailwebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        return ResponseEntity.ok(userService.registerUser(registerUserRequest));
    }
}
