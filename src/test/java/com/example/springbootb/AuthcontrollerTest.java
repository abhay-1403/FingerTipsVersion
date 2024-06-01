package com.example.springbootb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.springbootb.controller.AuthController;
import com.example.springbootb.dto.RegistrationDto;
import com.example.springbootb.entity.User;
import com.example.springbootb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loginPage_ShouldReturnLoginView() {
        String viewName = authController.loginPage();
        assertEquals("login", viewName);
    }

    @Test
    public void showRegistrationForm_ShouldReturnRegisterView() {
        String viewName = authController.showRegistrationForm(model);
        verify(model, times(1)).addAttribute(eq("user"), any(RegistrationDto.class));
        assertEquals("register", viewName);
    }


    @Test
    public void register_ShouldRedirectToLogin_WhenRegistrationIsSuccessful() {
        RegistrationDto userDto = new RegistrationDto();
        userDto.setEmail("newuser@example.com");

        when(userService.findByEmail(anyString())).thenReturn(null);
        when(result.hasErrors()).thenReturn(false);

        String viewName = authController.register(userDto, result, model, redirectAttributes);

        verify(userService, times(1)).saveUser(userDto);
        verify(redirectAttributes, times(1)).addFlashAttribute("successMessage", "Registration successful! Please log in.");
        assertEquals("redirect:/login", viewName);
    }
}
