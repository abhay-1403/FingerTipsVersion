package com.example.springbootb.mapper;

import com.example.springbootb.dto.PostDto;
import com.example.springbootb.entity.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostMapperTest {

    private Post post;
    private PostDto postDto;

    @BeforeEach
    void setUp() {
        post = Post.builder()
                .id(1L)
                .title("Test Title")
                .url("Test Url")
                .content("Test Content")
                .shortDescription("Test Short Description")
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .comments(Collections.emptySet())
                .build();

        postDto = PostDto.builder()
                .id(1L)
                .title("Test Title")
                .url("Test Url")
                .content("Test Content")
                .shortDescription("Test Short Description")
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .comments(Collections.emptySet())
                .build();
    }

    @Test
    void mapToPostDto() {
        PostDto mappedPostDto = PostMapper.mapToPostDto(post);

        assertEquals(post.getId(), mappedPostDto.getId());
        assertEquals(post.getTitle(), mappedPostDto.getTitle());
        assertEquals(post.getUrl(), mappedPostDto.getUrl());
        assertEquals(post.getContent(), mappedPostDto.getContent());
        assertEquals(post.getShortDescription(), mappedPostDto.getShortDescription());
        assertEquals(post.getCreatedOn(), mappedPostDto.getCreatedOn());
        assertEquals(post.getUpdatedOn(), mappedPostDto.getUpdatedOn());
        assertEquals(post.getComments().size(), mappedPostDto.getComments().size());
    }

    @Test
    void mapToPost() {
        Post mappedPost = PostMapper.mapToPost(postDto);

        assertEquals(postDto.getId(), mappedPost.getId());
        assertEquals(postDto.getTitle(), mappedPost.getTitle());
        assertEquals(postDto.getUrl(), mappedPost.getUrl());
        assertEquals(postDto.getContent(), mappedPost.getContent());
        assertEquals(postDto.getShortDescription(), mappedPost.getShortDescription());
        assertEquals(postDto.getCreatedOn(), mappedPost.getCreatedOn());
        assertEquals(postDto.getUpdatedOn(), mappedPost.getUpdatedOn());
    }
}