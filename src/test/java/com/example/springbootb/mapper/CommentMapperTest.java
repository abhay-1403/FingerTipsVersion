package com.example.springbootb.mapper;

import com.example.springbootb.dto.CommentDto;
import com.example.springbootb.entity.Comment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentMapperTest {

    private final Comment comment = Comment.builder()
            .id(1L)
            .name("Test Name")
            .email("test@example.com")
            .content("Test Content")
            .createdOn(LocalDateTime.now())
            .updatedOn(LocalDateTime.now())
            .build();

    private final CommentDto commentDto = CommentDto.builder()
            .id(1L)
            .name("Test Name")
            .email("test@example.com")
            .content("Test Content")
            .createdOn(LocalDateTime.now())
            .updatedOn(LocalDateTime.now())
            .build();

    @Test
    void mapToCommentDto() {
        CommentDto mappedDto = CommentMapper.mapToCommentDto(comment);

        assertEquals(comment.getId(), mappedDto.getId());
        assertEquals(comment.getName(), mappedDto.getName());
        assertEquals(comment.getEmail(), mappedDto.getEmail());
        assertEquals(comment.getContent(), mappedDto.getContent());
        assertEquals(comment.getCreatedOn(), mappedDto.getCreatedOn());
        assertEquals(comment.getUpdatedOn(), mappedDto.getUpdatedOn());
    }

    @Test
    void mapToComment() {
        Comment mappedComment = CommentMapper.mapToComment(commentDto);

        assertEquals(commentDto.getId(), mappedComment.getId());
        assertEquals(commentDto.getName(), mappedComment.getName());
        assertEquals(commentDto.getEmail(), mappedComment.getEmail());
        assertEquals(commentDto.getContent(), mappedComment.getContent());
        assertEquals(commentDto.getCreatedOn(), mappedComment.getCreatedOn());
        assertEquals(commentDto.getUpdatedOn(), mappedComment.getUpdatedOn());
    }
}