package com.example.springbootb.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackTest {
    private Feedback feedback;

    @BeforeEach
    public void setUp() {
        feedback = new Feedback();
    }

    @Test
    public void testGetId() {
        Long idValue = 4L;
        feedback.setId(idValue);
        assertEquals(idValue, feedback.getId());
    }

    @Test
    public void testSetName() {
        String name = "Test Name";
        feedback.setName(name);
        assertEquals(name, feedback.getName());
    }

    @Test
    public void testSetEmail() {
        String email = "test@test.com";
        feedback.setEmail(email);
        assertEquals(email, feedback.getEmail());
    }

    @Test
    public void testSetMessage() {
        String message = "Test Message";
        feedback.setMessage(message);
        assertEquals(message, feedback.getMessage());
    }
}
