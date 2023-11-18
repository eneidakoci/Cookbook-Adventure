package com.cookbook.controller;
import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.dto.RecipeRequest;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.service.RecipeService;
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

class RecipeControllerMockTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllRecipes() {
        // Mocking the service method
        when(recipeService.findAllRecipes(any())).thenReturn(Arrays.asList(new RecipeDTO(), new RecipeDTO()));

        // Testing the controller method
        ResponseEntity<List<RecipeDTO>> responseEntity = recipeController.findAllRecipes(1, 10, null, null);

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    void testFindRecipeById() {
        when(recipeService.findRecipeById(anyInt())).thenReturn(new RecipeDTO());

        ResponseEntity<RecipeDTO> responseEntity = recipeController.findRecipeById(1);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(RecipeDTO.class, responseEntity.getBody().getClass());
    }

    @Test
    void testCreateRecipeWithValidRequest() {
        RecipeDTO expectedRecipe = new RecipeDTO();
        expectedRecipe.setRecipeName("Test Recipe");
        when(recipeService.createRecipe(any(RecipeRequest.class))).thenReturn(expectedRecipe);

        RecipeRequest validRequest = new RecipeRequest();
        validRequest.setRecipeName("Test Recipe");
        ResponseEntity<RecipeDTO> responseEntity = recipeController.createRecipe(validRequest);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(RecipeDTO.class, responseEntity.getBody().getClass());
        assertEquals("Test Recipe", responseEntity.getBody().getRecipeName());
    }

    @Test
    void testCreateRecipeWithoutName() {
        when(recipeService.createRecipe(any(RecipeRequest.class))).thenThrow(new GenericException("Recipe name is required."));

        GenericException exception = assertThrows(GenericException.class, () -> {
            recipeController.createRecipe(new RecipeRequest());
        });

        assertEquals("Recipe name is required.", exception.getMessage());
    }

}
