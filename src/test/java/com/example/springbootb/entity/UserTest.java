package com.example.springbootb.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testId() {
        Long idValue = 1L;
        user.setId(idValue);
        assertEquals(idValue, user.getId());
    }

    @Test
    void testName() {
        String name = "Test User";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    void testEmail() {
        String email = "test@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    void testPassword() {
        String password = "password123";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    void testRoles() {
        Role role1 = new Role();
        role1.setName("ROLE_USER");
        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");

        user.setRoles(Arrays.asList(role1, role2));
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().stream().anyMatch(role -> "ROLE_USER".equals(role.getName())));
        assertTrue(user.getRoles().stream().anyMatch(role -> "ROLE_ADMIN".equals(role.getName())));
    }
}