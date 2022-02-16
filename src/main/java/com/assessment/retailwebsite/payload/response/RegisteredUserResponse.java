package com.assessment.retailwebsite.payload.response;

import com.assessment.retailwebsite.model.User;
import com.assessment.retailwebsite.model.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisteredUserResponse {
    private String message;
    private User user;
}
