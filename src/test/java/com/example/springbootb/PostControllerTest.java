package com.example.springbootb;

import com.example.springbootb.controller.PostController;
import com.example.springbootb.dto.CommentDto;
import com.example.springbootb.dto.PostDto;
import com.example.springbootb.service.CommentService;
import com.example.springbootb.service.PostService;
import com.example.springbootb.util.Role;
import com.example.springbootb.util.SecurityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private CommentService commentService;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void deleteComment_ShouldRedirectToAdminComments() {
        Long commentId = 1L;

        String viewName = postController.deleteComment(commentId);

        verify(commentService, times(1)).deleteComment(commentId);
        assertEquals("redirect:/admin/posts/comments", viewName);
    }

    @Test
    public void newPostForm_ShouldReturnCreatePostView() {
        String viewName = postController.newPostForm(model);

        verify(model, times(1)).addAttribute(eq("post"), any(PostDto.class));
        assertEquals("admin/create_post", viewName);
    }

    @Test
    public void createPost_ShouldReturnCreatePostView_WhenValidationFails() {
        when(result.hasErrors()).thenReturn(true);
        PostDto postDto = new PostDto();

        String viewName = postController.createPost(postDto, result, model);

        verify(model, times(1)).addAttribute("post", postDto);
        assertEquals("admin/create_post", viewName);
    }

    @Test
    public void createPost_ShouldRedirectToAdminPosts_WhenValidationSucceeds() {
        when(result.hasErrors()).thenReturn(false);
        PostDto postDto = new PostDto();
        postDto.setTitle("Sample Title");

        String viewName = postController.createPost(postDto, result, model);

        verify(postService, times(1)).createPost(postDto);
        assertEquals("redirect:/admin/posts", viewName);
    }

    @Test
    public void editPostForm_ShouldReturnEditPostView() {
        Long postId = 1L;
        PostDto postDto = new PostDto();
        when(postService.findPostById(postId)).thenReturn(postDto);

        String viewName = postController.editPostForm(postId, model);

        verify(model, times(1)).addAttribute("post", postDto);
        assertEquals("admin/edit_post", viewName);
    }

    @Test
    public void updatePost_ShouldReturnEditPostView_WhenValidationFails() {
        when(result.hasErrors()).thenReturn(true);
        PostDto postDto = new PostDto();
        Long postId = 1L;

        String viewName = postController.updatePost(postId, postDto, result, model);

        verify(model, times(1)).addAttribute("post", postDto);
        assertEquals("admin/edit_post", viewName);
    }

    @Test
    public void updatePost_ShouldRedirectToAdminPosts_WhenValidationSucceeds() {
        when(result.hasErrors()).thenReturn(false);
        PostDto postDto = new PostDto();
        Long postId = 1L;

        String viewName = postController.updatePost(postId, postDto, result, model);

        verify(postService, times(1)).updatePost(postDto);
        assertEquals("redirect:/admin/posts", viewName);
    }

    @Test
    public void deletePost_ShouldRedirectToAdminPosts() {
        Long postId = 1L;

        String viewName = postController.deletePost(postId);

        verify(postService, times(1)).deletePost(postId);
        assertEquals("redirect:/admin/posts", viewName);
    }

    @Test
    public void viewPost_ShouldReturnViewPostView() {
        String postUrl = "sample-post-url";
        PostDto postDto = new PostDto();
        when(postService.findPostByUrl(postUrl)).thenReturn(postDto);

        String viewName = postController.viewPost(postUrl, model);

        verify(model, times(1)).addAttribute("post", postDto);
        assertEquals("admin/view_post", viewName);
    }

    @Test
    public void searchPosts_ShouldReturnAdminPostsView() {
        String query = "sample-query";
        List<PostDto> posts = new ArrayList<>();
        when(postService.searchPosts(query)).thenReturn(posts);

        String viewName = postController.searchPosts(query, model);

        verify(model, times(1)).addAttribute("posts", posts);
        assertEquals("admin/posts", viewName);
    }
}