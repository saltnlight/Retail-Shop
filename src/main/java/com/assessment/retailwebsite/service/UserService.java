package com.assessment.retailwebsite.service;

import com.assessment.retailwebsite.payload.request.RegisterUserRequest;
import com.assessment.retailwebsite.payload.response.RegisteredUserResponse;

public interface UserService {

    RegisteredUserResponse registerUser(RegisterUserRequest registerUserRequest);
}
