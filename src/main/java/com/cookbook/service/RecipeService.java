package com.cookbook.service;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.dto.RecipeRequest;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;

import java.util.List;

public interface RecipeService {
    List<RecipeDTO> findAllRecipes();
    RecipeDTO findRecipeById(Integer id);
    RecipeDTO createRecipe(RecipeRequest recipe);
    RecipeDTO updateRecipe(Integer id, RecipeRequest recipe);
    void deleteRecipe(Integer id);
    List<CommentDTO> findCommentsByRecipeId(Integer recipeId);
    List<RatingDTO> findRatingsByRecipeId(Integer recipeId);
    List<RecipeDTO> findPopularRecipes();
    List<RecipeDTO> findNewestRecipes();
    Integer countRecipesByCategory(Integer categoryId);
    Integer countRecipesByMember(Integer memberId);
}
