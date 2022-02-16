package com.assessment.retailwebsite.service.serviceImpl;

import com.assessment.retailwebsite.exception.AppEntityException;
import com.assessment.retailwebsite.model.Role;
import com.assessment.retailwebsite.model.User;
import com.assessment.retailwebsite.payload.request.RegisterUserRequest;
import com.assessment.retailwebsite.payload.response.RegisteredUserResponse;
import com.assessment.retailwebsite.repository.RoleRepository;
import com.assessment.retailwebsite.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Role affiliate;
    private Role longTerm;
    private RegisterUserRequest req;
    private User longTermUser;
    private User affiliateUser = new User();

    @BeforeEach
    void setUp() {
        affiliate = new Role(1l, "Affiliate");
        longTerm = new Role(2l, "LongTermCustomer");

        req = new RegisterUserRequest();
        req.setFirstName("Test");
        req.setLastName("Affiliate");
        req.setEmail("TestCase1@gmail.com");
        req.setPassword("testing123");
        req.setRole(affiliate.getName());

        longTermUser = new User();
        longTermUser.setFirstName("Test");
        longTermUser.setLastName("Case");
        longTermUser.setEmail("TestCase2@gmail.com");
        longTermUser.setPassword("testing123");
        longTermUser.setId(5l);
        longTermUser.setRole(longTerm);
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
        when(userRepository.findByEmail(req.getEmail())).thenReturn(Optional.of(longTermUser));

        Exception exception = assertThrows(AppEntityException.class, () -> {
            userService.registerUser(req);
        });

        String expectedMessage = "This user already exists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}