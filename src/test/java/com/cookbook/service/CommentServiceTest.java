package com.cookbook.service;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.CommentRequest;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.repository.CommentRepository;
import com.cookbook.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllComments() {
        // Mocking the repository method
        when(commentRepository.findAllComments(any())).thenReturn(new ArrayList<>());

        // Testing the service method
        List<CommentDTO> result = commentService.findAllComments();

        // Assertions
        Mockito.verify(commentRepository).findAllComments();
        assertEquals(0, result.size());
    }

    @Test
    void testFindCommentById() {
        when(commentRepository.findCommentById(anyInt())).thenReturn(new CommentEntity());

        CommentDTO result = commentService.findCommentById(1);

        assertEquals(CommentDTO.class, result.getClass());
    }

    @Test
    void testCreateComment() {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentText("Test Content");

        when(commentRepository.createComment(any(CommentEntity.class))).thenReturn(new CommentEntity());
        CommentDTO result = commentService.createComment(commentRequest);

        assertEquals(CommentDTO.class, result.getClass());
    }

    @Test
    void testUpdateComment() {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentText("Updated Content");

        when(commentRepository.findCommentById(anyInt())).thenReturn(new CommentEntity());

        when(commentRepository.updateComment(anyInt(), any(CommentEntity.class))).thenAnswer(invocation -> {
            CommentEntity argument = invocation.getArgument(1);
            return argument;
        });

        CommentDTO result = commentService.updateComment(1, commentRequest);

        assertEquals(CommentDTO.class, result.getClass());
        assertEquals("Updated Content", result.getCommentText());
    }

    @Test
    void testDeleteComment() {
        when(commentRepository.deleteComment(anyInt())).thenReturn(new CommentEntity());

        CommentDTO result = commentService.deleteComment(1);

        assertEquals(CommentDTO.class, result.getClass());
    }

}