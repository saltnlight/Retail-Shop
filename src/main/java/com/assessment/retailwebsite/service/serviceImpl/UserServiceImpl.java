package com.assessment.retailwebsite.service.serviceImpl;

import com.assessment.retailwebsite.exception.AppEntityException;
import com.assessment.retailwebsite.model.Role;
import com.assessment.retailwebsite.model.User;
import com.assessment.retailwebsite.model.enums.RoleType;
import com.assessment.retailwebsite.payload.request.RegisterUserRequest;
import com.assessment.retailwebsite.payload.response.RegisteredUserResponse;
import com.assessment.retailwebsite.repository.RoleRepository;
import com.assessment.retailwebsite.repository.UserRepository;
import com.assessment.retailwebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public RegisteredUserResponse registerUser(RegisterUserRequest registerUserRequest) {

        Optional<User> existingUser= userRepository.findByEmail(registerUserRequest.getEmail());
        if(existingUser.isPresent()) throw new AppEntityException("This user already exists");

        User user = new User();
        user.setEmail(registerUserRequest.getEmail());
        user.setFirstName(registerUserRequest.getFirstName());
        user.setLastName(registerUserRequest.getLastName());
        user.setPassword(registerUserRequest.getPassword());
        user.setRole(getRole(RoleType.valueOf(registerUserRequest.getRole())));
        userRepository.save(user);
        return new RegisteredUserResponse("User Successfully Registered", user);
    }



    private Role getRole(RoleType roleType) {
        Optional<Role> optionalRole = roleRepository.findByName(roleType.name());
        return optionalRole
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .name(roleType.name())
                        .build()));
    }
}
