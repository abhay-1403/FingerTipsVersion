package com.example.springbootb;


import com.example.springbootb.dto.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenTitleIsEmpty_thenShouldGiveConstraintViolations() {
        PostDto postDto = PostDto.builder()
                .title("")
                .content("This is a post content")
                .shortDescription("This is a short description")
                .build();

        Set<ConstraintViolation<PostDto>> violations = validator.validate(postDto);

        assertEquals(1, violations.size());
        ConstraintViolation<PostDto> violation = violations.iterator().next();
        assertEquals("Post title should not be empty", violation.getMessage());
        assertEquals("title", violation.getPropertyPath().toString());
    }

    @Test
    public void whenContentIsEmpty_thenShouldGiveConstraintViolations() {
        PostDto postDto = PostDto.builder()
                .title("Post Title")
                .content("")
                .shortDescription("This is a short description")
                .build();

        Set<ConstraintViolation<PostDto>> violations = validator.validate(postDto);

        assertEquals(1, violations.size());
        ConstraintViolation<PostDto> violation = violations.iterator().next();
        assertEquals("Post content should not be empty", violation.getMessage());
        assertEquals("content", violation.getPropertyPath().toString());
    }

    @Test
    public void whenShortDescriptionIsEmpty_thenShouldGiveConstraintViolations() {
        PostDto postDto = PostDto.builder()
                .title("Post Title")
                .content("This is a post content")
                .shortDescription("")
                .build();

        Set<ConstraintViolation<PostDto>> violations = validator.validate(postDto);

        assertEquals(1, violations.size());
        ConstraintViolation<PostDto> violation = violations.iterator().next();
        assertEquals("Post description should not be empty", violation.getMessage());
        assertEquals("shortDescription", violation.getPropertyPath().toString());
    }

    @Test
    public void whenAllFieldsAreValid_thenShouldHaveNoViolations() {
        PostDto postDto = PostDto.builder()
                .title("Post Title")
                .content("This is a post content")
                .shortDescription("This is a short description")
                .build();

        Set<ConstraintViolation<PostDto>> violations = validator.validate(postDto);

        assertTrue(violations.isEmpty());
    }
}