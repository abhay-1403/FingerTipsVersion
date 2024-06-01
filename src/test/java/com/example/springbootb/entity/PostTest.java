package com.example.springbootb.entity;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void testConstructor() {
        Post post = new Post();
        assertNull(post.getId());
        assertNull(post.getTitle());
        assertNull(post.getUrl());
        assertNull(post.getContent());
        assertNull(post.getShortDescription());
        assertNull(post.getCreatedOn());
        assertNull(post.getUpdatedOn());
        assertNull(post.getCreatedBy());
        assertTrue(post.getComments().isEmpty());
    }

    @Test
    void testSetters() {
        Post post = new Post();
        User user = new User();
        Comment comment = new Comment();
        Set<Comment> comments = new HashSet<>();
        comments.add(comment);

        post.setId(1L);
        post.setTitle("Test Title");
        post.setUrl("Test Url");
        post.setContent("Test Content");
        post.setShortDescription("Test Short Description");
        post.setCreatedBy(user);
        post.setComments(comments);

        assertEquals(1L, post.getId());
        assertEquals("Test Title", post.getTitle());
        assertEquals("Test Url", post.getUrl());
        assertEquals("Test Content", post.getContent());
        assertEquals("Test Short Description", post.getShortDescription());
        assertEquals(user, post.getCreatedBy());
        assertEquals(comments, post.getComments());
    }

    @Test
    void testGetters() {
        Post post = new Post();
        User user = new User();
        Comment comment = new Comment();
        Set<Comment> comments = new HashSet<>();
        comments.add(comment);

        post.setId(1L);
        post.setTitle("Test Title");
        post.setUrl("Test Url");
        post.setContent("Test Content");
        post.setShortDescription("Test Short Description");
        post.setCreatedBy(user);
        post.setComments(comments);

        assertEquals(1L, post.getId());
        assertEquals("Test Title", post.getTitle());
        assertEquals("Test Url", post.getUrl());
        assertEquals("Test Content", post.getContent());
        assertEquals("Test Short Description", post.getShortDescription());
        assertEquals(user, post.getCreatedBy());
        assertEquals(comments, post.getComments());
    }
}
