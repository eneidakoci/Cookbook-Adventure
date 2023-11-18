package com.cookbook.service;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.dto.RecipeRequest;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.repository.CommentRepository;
import com.cookbook.repository.RatingRepository;
import com.cookbook.repository.RecipeRepository;
import com.cookbook.service.impl.RecipeServiceImpl;
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

class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RecipeService recipeService = Mockito.spy(new RecipeServiceImpl());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllRecipes() {
        when(recipeRepository.findAllRecipes(any())).thenReturn(new ArrayList<>());

        List<RecipeDTO> result = recipeService.findAllRecipes();

        Mockito.verify(recipeRepository).findAllRecipes();
        assertEquals(0, result.size());
    }

    @Test
    void testFindRecipeById() {
        when(recipeRepository.findRecipeById(anyInt())).thenReturn(new RecipeEntity());

        RecipeDTO result = recipeService.findRecipeById(1);

        assertEquals(RecipeDTO.class, result.getClass());
    }

    @Test
    void testCreateRecipe() {
        RecipeRequest recipeRequest = new RecipeRequest();
        recipeRequest.setRecipeName("Test Recipe");

        when(recipeRepository.createRecipe(any(RecipeEntity.class))).thenReturn(new RecipeEntity());

        RecipeDTO result = recipeService.createRecipe(recipeRequest);

        assertEquals(RecipeDTO.class, result.getClass());
    }

    @Test
    void testUpdateRecipe() {
        RecipeRequest recipeRequest = new RecipeRequest();
        recipeRequest.setRecipeName("Updated Recipe");

        when(recipeRepository.findRecipeById(anyInt())).thenReturn(new RecipeEntity());

        when(recipeRepository.updateRecipe(anyInt(), any(RecipeEntity.class))).thenAnswer(invocation -> {
            RecipeEntity argument = invocation.getArgument(1);
            return argument;
        });

        RecipeDTO result = recipeService.updateRecipe(1, recipeRequest);

        assertEquals(RecipeDTO.class, result.getClass());
        assertEquals("Updated Recipe", result.getRecipeName());
    }

    @Test
    void testDeleteRecipe() {
        when(recipeRepository.deleteRecipe(anyInt())).thenReturn(new RecipeEntity());

        RecipeDTO result = recipeService.deleteRecipe(1);

        assertEquals(RecipeDTO.class, result.getClass());
    }

    @Test
    void testFindCommentsByRecipeId() {
        when(recipeRepository.findRecipeById(anyInt())).thenReturn(new RecipeEntity());
        when(commentRepository.findCommentsByRecipeId(anyInt())).thenReturn(new ArrayList<>());

        List<CommentDTO> result = recipeService.findCommentsByRecipeId(1);

        assertEquals(0, result.size());
    }

    @Test
    void testFindRatingsByRecipeId() {
        when(ratingRepository.findRatingsByRecipeId(anyInt())).thenReturn(new ArrayList<>());

        List<RatingDTO> result = recipeService.findRatingsByRecipeId(1);

        assertEquals(0, result.size());
    }

    @Test
    void testFindPopularRecipes() {
        when(recipeRepository.findPopularRecipes()).thenReturn(new ArrayList<>());

        List<RecipeDTO> result = recipeService.findPopularRecipes();

        assertEquals(0, result.size());
    }

    @Test
    void testFindNewestRecipes() {
        when(recipeRepository.findNewestRecipes()).thenReturn(new ArrayList<>());

        List<RecipeDTO> result = recipeService.findNewestRecipes();

        assertEquals(0, result.size());
    }

    @Test
    void testCountRecipesByCategory() {
        when(recipeRepository.countRecipesByCategory(anyInt())).thenReturn(5);

        Integer result = recipeService.countRecipesByCategory(1);

        assertEquals(5, result);
    }

    @Test
    void testCountRecipesByMember() {
        when(recipeRepository.countRecipesByMember(anyInt())).thenReturn(3);

        Integer result = recipeService.countRecipesByMember(1);

        assertEquals(3, result);
    }
}
