package com.example.springbootb.entity;

import com.example.springbootb.entity.Comment;
import com.example.springbootb.entity.Post;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommentTest {

    @Test
    public void testConstructor() {
        Post post = new Post();
        LocalDateTime now = LocalDateTime.now();
        Comment comment = new Comment(1L, "Name", "Email", "Content", now, now, post);

        assertEquals(1L, comment.getId());
        assertEquals("Name", comment.getName());
        assertEquals("Email", comment.getEmail());
        assertEquals("Content", comment.getContent());
        assertEquals(now, comment.getCreatedOn());
        assertEquals(now, comment.getUpdatedOn());
        assertEquals(post, comment.getPost());
    }

    @Test
    public void testGetterAndSetter() {
        Post post = new Post();
        LocalDateTime now = LocalDateTime.now();
        Comment comment = new Comment();

        comment.setId(1L);
        comment.setName("Name");
        comment.setEmail("Email");
        comment.setContent("Content");
        comment.setCreatedOn(now);
        comment.setUpdatedOn(now);
        comment.setPost(post);

        assertEquals(1L, comment.getId());
        assertEquals("Name", comment.getName());
        assertEquals("Email", comment.getEmail());
        assertEquals("Content", comment.getContent());
        assertEquals(now, comment.getCreatedOn());
        assertEquals(now, comment.getUpdatedOn());
        assertEquals(post, comment.getPost());
    }

}