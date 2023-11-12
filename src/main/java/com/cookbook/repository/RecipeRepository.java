package com.cookbook.repository;

import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;

import java.util.List;

public interface RecipeRepository {
    List<RecipeEntity> findAllRecipes(Integer pageNumber, Integer pageSize);
    RecipeEntity findRecipeById(Integer id);
    RecipeEntity createRecipe(RecipeEntity recipe);
    RecipeEntity updateRecipe(Integer id, RecipeEntity recipe);
    RecipeEntity deleteRecipe(Integer id);
    List<CommentEntity> findCommentsByRecipeId(Integer recipeId);
    List<RatingEntity> findRatingsByRecipeId(Integer recipeId);
    List<RecipeEntity> findPopularRecipes();
    List<RecipeEntity> findNewestRecipes();
    Integer countRecipesByCategory(Integer categoryId);
    Integer countRecipesByMember(Integer memberId);

    List<RecipeEntity> findRecipesByMember(MemberEntity memberEntity);
}
