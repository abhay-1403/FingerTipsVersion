package com.example.springbootb;

import com.example.springbootb.dto.CommentDto;
import com.example.springbootb.entity.Comment;
import com.example.springbootb.entity.Post;
import com.example.springbootb.entity.User;
import com.example.springbootb.repository.CommentRepository;
import com.example.springbootb.repository.PostRepository;
import com.example.springbootb.repository.UserRepository;
import com.example.springbootb.service.impl.CommentServiceImpl;
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
import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateComment() {
        String postUrl = "test-url";
        CommentDto commentDto = new CommentDto();

        Post post = new Post();
        post.setId(1L);

        when(postRepository.findByUrl(postUrl)).thenReturn(Optional.of(post));

        commentService.createComment(postUrl, commentDto);

        verify(commentRepository, times(1)).save(any());
    }

    @Test
    void testFindAllComments() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());

        when(commentRepository.findAll()).thenReturn(comments);

        List<CommentDto> commentDtos = commentService.findAllComments();

        assertEquals(comments.size(), commentDtos.size());
    }

    @Test
    void testDeleteComment() {
        Long commentId = 1L;

        commentService.deleteComment(commentId);

        verify(commentRepository, times(1)).deleteById(commentId);
    }

}