package com.example.springbootb;


import com.example.springbootb.controller.CommentController;
import com.example.springbootb.dto.CommentDto;
import com.example.springbootb.dto.PostDto;
import com.example.springbootb.service.CommentService;
import com.example.springbootb.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @Mock
    private PostService postService;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createComment_ShouldReturnBlogPostView_WhenValidationFails() {
        String postUrl = "some-post-url";
        PostDto postDto = new PostDto();
        CommentDto commentDto = new CommentDto();

        when(postService.findPostByUrl(anyString())).thenReturn(postDto);
        when(result.hasErrors()).thenReturn(true);

        String viewName = commentController.createComment(postUrl, commentDto, result, model);

        verify(postService, times(1)).findPostByUrl(postUrl);
        verify(model, times(1)).addAttribute("post", postDto);
        verify(model, times(1)).addAttribute("comment", commentDto);
        assertEquals("blog/blog_post", viewName);
    }

    @Test
    public void createComment_ShouldRedirectToPost_WhenValidationSucceeds() {
        String postUrl = "some-post-url";
        CommentDto commentDto = new CommentDto();

        when(result.hasErrors()).thenReturn(false);

        String viewName = commentController.createComment(postUrl, commentDto, result, model);

        verify(commentService, times(1)).createComment(postUrl, commentDto);
        assertEquals("redirect:/post/" + postUrl, viewName);
    }
}

