package com.cookbook.domain.mapper.impl;

import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.dto.RecipeRequest;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.mapper.RecipeMapper;

public class RecipeMapperImpl implements RecipeMapper {
    @Override
    public RecipeDTO recipeEntityToDto(RecipeEntity entity) {
        if(entity == null){
            return null;
        }
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setDeleted(entity.isDeleted());
        recipeDTO.setCreatedDate(entity.getCreatedDate());
        recipeDTO.setLastModified(entity.getLastModified());
        recipeDTO.setRecipeId(entity.getRecipeId());
        recipeDTO.setRecipeName(entity.getRecipeName());
        recipeDTO.setCategories(entity.getCategories());
        recipeDTO.setIngredients(entity.getIngredients());
        recipeDTO.setDescription(entity.getDescription());
        recipeDTO.setCategories(entity.getCategories());
        recipeDTO.setLikes(entity.getLikes());
        return recipeDTO;
    }

    @Override
    public RecipeEntity recipeDtoToEntity(RecipeDTO dto) {
        if(dto == null){
            return null;
        }
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setDeleted(dto.isDeleted());
        recipeEntity.setCreatedDate(dto.getCreatedDate());
        recipeEntity.setLastModified(dto.getLastModified());
        recipeEntity.setRecipeId(dto.getRecipeId());
        recipeEntity.setRecipeName(dto.getRecipeName());
        recipeEntity.setCategories(dto.getCategories());
        recipeEntity.setIngredients(dto.getIngredients());
        recipeEntity.setDescription(dto.getDescription());
        recipeEntity.setLikes(dto.getLikes());
        recipeEntity.setCategories(dto.getCategories());
        return recipeEntity;
    }

    public static RecipeEntity recipeRequestToEntity(RecipeRequest recipeRequest){
        RecipeEntity rating = new RecipeEntity();
        rating.setDeleted(recipeRequest.isDeleted());
        rating.setCreatedDate(recipeRequest.getCreatedDate());
        rating.setLastModified(recipeRequest.getLastModified());
        rating.setRecipeName(recipeRequest.getRecipeName());
        rating.setIngredients(recipeRequest.getIngredients());
        rating.setDescription(recipeRequest.getDescription());
        rating.setLikes(recipeRequest.getLikes());
        return rating;
    }
}
