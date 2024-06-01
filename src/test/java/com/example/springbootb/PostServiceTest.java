package com.example.springbootb;

import com.example.springbootb.dto.PostDto;
import com.example.springbootb.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPostByUser() {
        List<PostDto> posts = postService.findPostByUser();

        assertNotNull(posts);
    }

    @Test
    void testFindAllPosts() {
        List<PostDto> posts = postService.findAllPosts();

        assertNotNull(posts);
    }

    @Test
    void testCreatePost() {
        PostDto postDto = new PostDto();

        postService.createPost(postDto);

        verify(postService, times(1)).createPost(postDto);
    }

    @Test
    void testFindPostById() {
        Long postId = 1L;

        PostDto post = postService.findPostById(postId);

        assertNull(post);
    }

    @Test
    void testUpdatePost() {
        PostDto postDto = new PostDto();

        postService.updatePost(postDto);

        verify(postService, times(1)).updatePost(postDto);
    }

    @Test
    void testDeletePost() {
        Long postId = 1L;

        postService.deletePost(postId);

        verify(postService, times(1)).deletePost(postId);
    }

    @Test
    void testFindPostByUrl() {
        String postUrl = "test-post-url";

        PostDto post = postService.findPostByUrl(postUrl);

        assertNull(post);
    }

    @Test
    void testSearchPosts() {
        String query = "test-query";

        List<PostDto> posts = postService.searchPosts(query);

        assertNotNull(posts);
    }
}
