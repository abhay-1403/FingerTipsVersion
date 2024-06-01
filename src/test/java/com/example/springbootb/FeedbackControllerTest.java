package com.example.springbootb;
import com.example.springbootb.controller.FeedbackController;
import com.example.springbootb.entity.Feedback;
import com.example.springbootb.repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class FeedbackControllerTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private Model model;

    @InjectMocks
    private FeedbackController feedbackController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void showFeedbackForm_ShouldReturnFeedbackView() {
        String viewName = feedbackController.showFeedbackForm(model);

        verify(model, times(1)).addAttribute("message", null);
        assertEquals("feedback", viewName);
    }

    @Test
    public void submitFeedback_ShouldSaveFeedbackAndReturnFeedbackView() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String message = "Great website!";

        String viewName = feedbackController.submitFeedback(name, email, message, model);

        verify(feedbackRepository, times(1)).save(any(Feedback.class));
        verify(model, times(1)).addAttribute("message", "Feedback has been received. Thank you for your feedback");
        assertEquals("feedback", viewName);
    }
}