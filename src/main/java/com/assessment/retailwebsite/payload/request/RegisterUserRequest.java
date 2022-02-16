package com.assessment.retailwebsite.payload.request;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

}
