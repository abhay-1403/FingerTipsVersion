package com.example.springbootb;


import com.example.springbootb.dto.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommentDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenNameIsEmpty_thenShouldGiveConstraintViolations() {
        CommentDto commentDto = CommentDto.builder()
                .name("")
                .email("test@example.com")
                .content("This is a comment")
                .build();

        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);

        assertEquals(1, violations.size());
        ConstraintViolation<CommentDto> violation = violations.iterator().next();
        assertEquals("Name should not be empty", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }

    @Test
    public void whenEmailIsEmpty_thenShouldGiveConstraintViolations() {
        CommentDto commentDto = CommentDto.builder()
                .name("John Doe")
                .email("")
                .content("This is a comment")
                .build();

        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);

        assertEquals(1, violations.size());
        ConstraintViolation<CommentDto> violation = violations.iterator().next();
        assertEquals("Email should not be empty", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    public void whenEmailIsInvalid_thenShouldGiveConstraintViolations() {
        CommentDto commentDto = CommentDto.builder()
                .name("John Doe")
                .email("invalid-email")
                .content("This is a comment")
                .build();

        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);

        assertEquals(1, violations.size());
        ConstraintViolation<CommentDto> violation = violations.iterator().next();
        assertEquals("must be a well-formed email address", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    public void whenContentIsEmpty_thenShouldGiveConstraintViolations() {
        CommentDto commentDto = CommentDto.builder()
                .name("John Doe")
                .email("test@example.com")
                .content("")
                .build();

        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);

        assertEquals(1, violations.size());
        ConstraintViolation<CommentDto> violation = violations.iterator().next();
        assertEquals("Comment field should not be empty", violation.getMessage());
        assertEquals("content", violation.getPropertyPath().toString());
    }

    @Test
    public void whenAllFieldsAreValid_thenShouldHaveNoViolations() {
        CommentDto commentDto = CommentDto.builder()
                .name("John Doe")
                .email("test@example.com")
                .content("This is a comment")
                .build();

        Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);

        assertTrue(violations.isEmpty());
    }
}
