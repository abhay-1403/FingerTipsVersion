package com.example.springbootb;



import com.example.springbootb.controller.BlogController;
import com.example.springbootb.dto.CommentDto;
import com.example.springbootb.dto.PostDto;
import com.example.springbootb.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BlogControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private Model model;

    @InjectMocks
    private BlogController blogController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void viewBlogPosts_ShouldReturnViewPostsView() {
        List<PostDto> postsResponse = new ArrayList<>();
        when(postService.findAllPosts()).thenReturn(postsResponse);

        String viewName = blogController.viewBlogPosts(model);

        verify(postService, times(1)).findAllPosts();
        verify(model, times(1)).addAttribute("postsResponse", postsResponse);
        assertEquals("blog/view_posts", viewName);
    }

    @Test
    public void showPost_ShouldReturnBlogPostView() {
        PostDto post = new PostDto();
        when(postService.findPostByUrl(anyString())).thenReturn(post);

        String viewName = blogController.showPost("some-post-url", model);

        verify(postService, times(1)).findPostByUrl("some-post-url");
        verify(model, times(1)).addAttribute(eq("comment"), any(CommentDto.class));
        verify(model, times(1)).addAttribute("post", post);
        assertEquals("blog/blog_post", viewName);
    }

    @Test
    public void searchPosts_ShouldReturnViewPostsView() {
        List<PostDto> postsResponse = new ArrayList<>();
        when(postService.searchPosts(anyString())).thenReturn(postsResponse);

        String viewName = blogController.searchPosts("query", model);

        verify(postService, times(1)).searchPosts("query");
        verify(model, times(1)).addAttribute("postsResponse", postsResponse);
        assertEquals("blog/view_posts", viewName);
    }
}

