package com.assessment.retailwebsite.service.serviceImpl;

import com.assessment.retailwebsite.exception.AppEntityException;
import com.assessment.retailwebsite.model.Role;
import com.assessment.retailwebsite.model.User;
import com.assessment.retailwebsite.payload.request.RegisterUserRequest;
import com.assessment.retailwebsite.payload.response.RegisteredUserResponse;
import com.assessment.retailwebsite.repository.RoleRepository;
import com.assessment.retailwebsite.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Role affiliate;
    private RegisterUserRequest req;
    private User affiliateUser;

    @BeforeEach
    void setUp() {
        affiliate = new Role(1l, "Affiliate");

        req = new RegisterUserRequest();
        req.setFirstName("Test");
        req.setLastName("Affiliate");
        req.setEmail("TestCase1@gmail.com");
        req.setPassword("testing123");
        req.setRole(affiliate.getName());

        affiliateUser = new User();
        affiliateUser.setFirstName("Test");
        affiliateUser.setLastName("Affiliate");
        affiliateUser.setEmail("TestCase1@gmail.com");
        affiliateUser.setPassword("testing123");
        affiliateUser.setId(5l);
        affiliateUser.setRole(affiliate);
    }

    @Test
    void registerUser() {
        when(userRepository.findByEmail(req.getEmail())).thenReturn(Optional.empty());
        when(roleRepository.findByName(req.getRole())).thenReturn(Optional.of(affiliate));
        RegisteredUserResponse res = userService.registerUser(req);
        assertNotNull(res);
        assertEquals("User Successfully Registered", res.getMessage());
        assertEquals("TestCase1@gmail.com", res.getUser().getEmail());
    }

    @Test
    void userExists() {
        when(userRepository.findByEmail(req.getEmail())).thenReturn(Optional.of(affiliateUser));

        Exception exception = assertThrows(AppEntityException.class, () -> {
            userService.registerUser(req);
        });

        String expectedMessage = "This user already exists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}