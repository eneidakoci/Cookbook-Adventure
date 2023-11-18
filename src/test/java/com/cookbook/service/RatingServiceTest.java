package com.cookbook.service;

import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.domain.mapper.impl.RatingMapperImpl;
import com.cookbook.filter.Filter;
import com.cookbook.repository.RatingRepository;
import com.cookbook.service.impl.MemberServiceImpl;
import com.cookbook.service.impl.RatingServiceImpl;
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

class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService = Mockito.spy(new RatingServiceImpl());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllRatings() {
        when(ratingRepository.findAllRatings(any())).thenReturn(new ArrayList<>());

        List<RatingDTO> result = ratingService.findAllRatings();

        Mockito.verify(ratingRepository).findAllRatings();
        assertEquals(0, result.size());
    }

    @Test
    void testFindRatingById() {
        when(ratingRepository.findRatingById(anyInt())).thenReturn(new RatingEntity());

        RatingDTO result = ratingService.findRatingById(1);

        assertEquals(RatingDTO.class, result.getClass());
    }

    @Test
    void testCreateRating() {
        RatingRequest ratingRequest = new RatingRequest();
        ratingRequest.setRate(5);

        when(ratingRepository.createRating(any(RatingEntity.class))).thenReturn(new RatingEntity());

        RatingDTO result = ratingService.createRating(ratingRequest);

        assertEquals(RatingDTO.class, result.getClass());
    }

    @Test
    void testUpdateRating() {
        RatingRequest ratingRequest = new RatingRequest();
        ratingRequest.setRate(4);

        when(ratingRepository.findRatingById(anyInt())).thenReturn(new RatingEntity());

        when(ratingRepository.updateRating(anyInt(), any(RatingEntity.class))).thenAnswer(invocation -> {
            RatingEntity argument = invocation.getArgument(1);
            return argument;
        });

        RatingDTO result = ratingService.updateRating(1, ratingRequest);

        assertEquals(RatingDTO.class, result.getClass());
        assertEquals(4, result.getRate());
    }

    @Test
    void testDeleteRating() {
        when(ratingRepository.deleteRating(anyInt())).thenReturn(new RatingEntity());

        RatingDTO result = ratingService.deleteRating(1);

        assertEquals(RatingDTO.class, result.getClass());
    }

}
