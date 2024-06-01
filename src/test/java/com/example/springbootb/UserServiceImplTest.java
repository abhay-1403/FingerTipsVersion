package com.example.springbootb;


import com.example.springbootb.dto.RegistrationDto;
import com.example.springbootb.entity.Role;
import com.example.springbootb.entity.User;
import com.example.springbootb.repository.RoleRepository;
import com.example.springbootb.repository.UserRepository;
import com.example.springbootb.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveUser() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName("John");
        registrationDto.setLastName("Doe");
        registrationDto.setEmail("john@example.com");
        registrationDto.setPassword("password");

        Role role = new Role();
        role.setName("ROLE_GUEST");

        when(roleRepository.findByName("ROLE_GUEST")).thenReturn(role);
        when(passwordEncoder.encode(registrationDto.getPassword())).thenReturn("hashedPassword");

        userService.saveUser(registrationDto);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testSaveUser_RoleNotFound() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName("John");
        registrationDto.setLastName("Doe");
        registrationDto.setEmail("john@example.com");
        registrationDto.setPassword("password");

        when(roleRepository.findByName("ROLE_GUEST")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userService.saveUser(registrationDto));
    }

    @Test
    void testFindByEmail() {
        String email = "john@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(user);

        User foundUser = userService.findByEmail(email);

        assertEquals(email, foundUser.getEmail());
    }
}