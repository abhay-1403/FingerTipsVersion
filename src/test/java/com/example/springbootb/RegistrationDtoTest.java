package com.example.springbootb;

import com.example.springbootb.dto.RegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenFirstNameIsEmpty_thenShouldGiveConstraintViolations() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName("");
        registrationDto.setLastName("Doe");
        registrationDto.setEmail("test@example.com");
        registrationDto.setPassword("password");

        Set<ConstraintViolation<RegistrationDto>> violations = validator.validate(registrationDto);

        assertEquals(1, violations.size());
        ConstraintViolation<RegistrationDto> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("firstName", violation.getPropertyPath().toString());
    }

    @Test
    public void whenLastNameIsEmpty_thenShouldGiveConstraintViolations() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName("John");
        registrationDto.setLastName("");
        registrationDto.setEmail("test@example.com");
        registrationDto.setPassword("password");

        Set<ConstraintViolation<RegistrationDto>> violations = validator.validate(registrationDto);

        assertEquals(1, violations.size());
        ConstraintViolation<RegistrationDto> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("lastName", violation.getPropertyPath().toString());
    }

    @Test
    public void whenEmailIsEmpty_thenShouldGiveConstraintViolations() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName("John");
        registrationDto.setLastName("Doe");
        registrationDto.setEmail("");
        registrationDto.setPassword("password");

        Set<ConstraintViolation<RegistrationDto>> violations = validator.validate(registrationDto);

        assertEquals(1, violations.size());
        ConstraintViolation<RegistrationDto> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    public void whenEmailIsInvalid_thenShouldGiveConstraintViolations() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName("John");
        registrationDto.setLastName("Doe");
        registrationDto.setEmail("invalid-email");
        registrationDto.setPassword("password");

        Set<ConstraintViolation<RegistrationDto>> violations = validator.validate(registrationDto);

        assertEquals(1, violations.size());
        ConstraintViolation<RegistrationDto> violation = violations.iterator().next();
        assertEquals("must be a well-formed email address", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    public void whenPasswordIsEmpty_thenShouldGiveConstraintViolations() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName("John");
        registrationDto.setLastName("Doe");
        registrationDto.setEmail("test@example.com");
        registrationDto.setPassword("");

        Set<ConstraintViolation<RegistrationDto>> violations = validator.validate(registrationDto);

        assertEquals(1, violations.size());
        ConstraintViolation<RegistrationDto> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("password", violation.getPropertyPath().toString());
    }

    @Test
    public void whenAllFieldsAreValid_thenShouldHaveNoViolations() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName("John");
        registrationDto.setLastName("Doe");
        registrationDto.setEmail("test@example.com");
        registrationDto.setPassword("password");

        Set<ConstraintViolation<RegistrationDto>> violations = validator.validate(registrationDto);

        assertTrue(violations.isEmpty());
    }
}
