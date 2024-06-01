package com.example.springbootb;

import com.example.springbootb.dto.PostDto;
import com.example.springbootb.entity.Post;
import com.example.springbootb.entity.User;
import com.example.springbootb.mapper.PostMapper;
import com.example.springbootb.repository.PostRepository;
import com.example.springbootb.repository.UserRepository;
import com.example.springbootb.service.impl.PostServiceImpl;
import com.example.springbootb.util.SecurityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testFindAllPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        posts.add(new Post());

        when(postRepository.findAll()).thenReturn(posts);

        List<PostDto> postDtos = postService.findAllPosts();

        assertEquals(posts.size(), postDtos.size());
    }



    @Test
    void testFindPostById() {
        Long postId = 1L;
        Post post = new Post();
        post.setId(postId);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        PostDto postDto = postService.findPostById(postId);

        assertNotNull(postDto);
        assertEquals(post.getId(), postDto.getId());
    }


    @Test
    void testDeletePost() {
        Long postId = 1L;

        postService.deletePost(postId);

        verify(postRepository, times(1)).deleteById(postId);
    }

    @Test
    void testFindPostByUrl() {
        String postUrl = "test-url";
        Post post = new Post();
        post.setUrl(postUrl);

        when(postRepository.findByUrl(postUrl)).thenReturn(Optional.of(post));

        PostDto postDto = postService.findPostByUrl(postUrl);

        assertNotNull(postDto);
        assertEquals(postUrl, postDto.getUrl());
    }

    @Test
    void testSearchPosts() {
        String query = "test";
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        posts.add(new Post());

        when(postRepository.searchPosts(query)).thenReturn(posts);

        List<PostDto> postDtos = postService.searchPosts(query);

        assertEquals(posts.size(), postDtos.size());
    }
}