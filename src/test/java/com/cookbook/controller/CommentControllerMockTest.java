package com.cookbook.controller;
import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.CommentRequest;
import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CommentControllerMockTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllComments() {
        // Mocking the service method
        when(commentService.findAllComments(any())).thenReturn(Arrays.asList(new CommentDTO(), new CommentDTO()));

        // Testing the controller method
        ResponseEntity<List<CommentDTO>> responseEntity = commentController.findAllComments(1, 10, null, null);

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    void testFindCommentById() {
        when(commentService.findCommentById(anyInt())).thenReturn(new CommentDTO());

        ResponseEntity<CommentDTO> responseEntity = commentController.findCommentById(1);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(CommentDTO.class, responseEntity.getBody().getClass());
    }

    @Test
    void testCreateCommentWithValidRequest() {
        CommentDTO expectedComment = new CommentDTO();
        expectedComment.setCommentText("Test Comment");
        when(commentService.createComment(any(CommentRequest.class))).thenReturn(expectedComment);

        CommentRequest validRequest = new CommentRequest();
        validRequest.setCommentText("Test Comment");
        ResponseEntity<CommentDTO> responseEntity = commentController.createComment(validRequest);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(CommentDTO.class, responseEntity.getBody().getClass());
        assertEquals("Test Comment", responseEntity.getBody().getCommentText());
    }

    @Test
    void testCreateCommentWithoutContent() {
        when(commentService.createComment(any(CommentRequest.class))).thenThrow(new GenericException("Comment content is required."));

        GenericException exception = assertThrows(GenericException.class, () -> {
            commentController.createComment(new CommentRequest());
        });

        assertEquals("Comment content is required.", exception.getMessage());
    }

}