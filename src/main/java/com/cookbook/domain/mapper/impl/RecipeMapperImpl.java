package com.cookbook.domain.mapper.impl;

import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RatingRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.dto.RecipeRequest;
import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.mapper.RecipeMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

public class RecipeMapperImpl {

    public static RecipeDTO recipeEntityToDto(RecipeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RecipeDTO recipeDTO = new RecipeDTO();

        recipeDTO.setCreatedDate( entity.getCreatedDate() );
        recipeDTO.setLastModified( entity.getLastModified() );
        recipeDTO.setDeleted( entity.isDeleted() );
        recipeDTO.setRecipeId( entity.getRecipeId() );
        Set<CategoryEntity> set = entity.getCategories();
        if ( set != null ) {
            recipeDTO.setCategories( new LinkedHashSet<CategoryEntity>( set ) );
        }
        recipeDTO.setRecipeName( entity.getRecipeName() );
        recipeDTO.setIngredients( entity.getIngredients() );
        recipeDTO.setMemberEntity(entity.getMemberEntity());
        recipeDTO.setDescription( entity.getDescription() );
        recipeDTO.setLikes( entity.getLikes() );

        return recipeDTO;
    }

    public static RecipeEntity recipeDtoToEntity(RecipeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RecipeEntity recipeEntity = new RecipeEntity();

        recipeEntity.setCreatedDate( dto.getCreatedDate() );
        recipeEntity.setLastModified( dto.getLastModified() );
        recipeEntity.setDeleted( dto.isDeleted() );
        recipeEntity.setMemberEntity(dto.getMemberEntity());
        recipeEntity.setRecipeId( dto.getRecipeId() );
        Set<CategoryEntity> set = dto.getCategories();
        if ( set != null ) {
            recipeEntity.setCategories( new LinkedHashSet<CategoryEntity>( set ) );
        }
        recipeEntity.setRecipeName( dto.getRecipeName() );
        recipeEntity.setIngredients( dto.getIngredients() );
        recipeEntity.setDescription( dto.getDescription() );
        recipeEntity.setLikes( dto.getLikes() );

        return recipeEntity;
    }
    public static RecipeEntity requestDtoToEntity(RecipeRequest request) {
        if ( request == null ) {
            return null;
        }

        RecipeEntity recipeEntity = new RecipeEntity();

        recipeEntity.setCreatedDate( request.getCreatedDate() );
        recipeEntity.setLastModified( request.getLastModified() );
        recipeEntity.setDeleted( request.isDeleted() );
        recipeEntity.setMemberEntity(request.getMemberEntity());
        recipeEntity.setRecipeName( request.getRecipeName() );
        recipeEntity.setIngredients( request.getIngredients() );
        recipeEntity.setDescription( request.getDescription() );
        recipeEntity.setLikes( request.getLikes() );

        return recipeEntity;
    }
}
