package com.cookbook.controller;

import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RatingControllerMockTest {

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private RatingController ratingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllRatings() {
        // Mocking the service method
        when(ratingService.findAllRatings(any())).thenReturn(Arrays.asList(new RatingDTO(), new RatingDTO()));

        // Testing the controller method
        ResponseEntity<List<RatingDTO>> responseEntity = ratingController.findAllRatings(1, 10, null, null);

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    void testFindRatingById() {
        when(ratingService.findRatingById(anyInt())).thenReturn(new RatingDTO());

        ResponseEntity<RatingDTO> responseEntity = ratingController.findRatingById(1);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(RatingDTO.class, responseEntity.getBody().getClass());
    }

    @Test
    void testCreateRatingWithValidRequest() {
        RatingDTO expectedRating = new RatingDTO();
        expectedRating.setRate(5);
        when(ratingService.createRating(any(RatingRequest.class))).thenReturn(expectedRating);

        RatingRequest validRequest = new RatingRequest();
        validRequest.setRate(5);
        ResponseEntity<RatingDTO> responseEntity = ratingController.createRating(validRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(RatingDTO.class, responseEntity.getBody().getClass());
        assertEquals(5, responseEntity.getBody().getRate());
    }

    @Test
    void testCreateRatingWithoutRate() {
        when(ratingService.createRating(any(RatingRequest.class))).thenThrow(new GenericException("Rating is required."));

        GenericException exception = assertThrows(GenericException.class, () -> {
            ratingController.createRating(new RatingRequest());
        });

        assertEquals("Rating is required.", exception.getMessage());
    }

}