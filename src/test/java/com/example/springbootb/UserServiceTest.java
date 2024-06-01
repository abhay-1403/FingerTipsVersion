package com.example.springbootb;

import com.example.springbootb.dto.RegistrationDto;
import com.example.springbootb.entity.User;
import com.example.springbootb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("test@example.com");

        userService.saveUser(registrationDto);

        verify(userService, times(1)).saveUser(registrationDto);
    }

    @Test
    void testFindByEmail() {
        String email = "test@example.com";

        User user = userService.findByEmail(email);

        assertNull(user);
    }
}