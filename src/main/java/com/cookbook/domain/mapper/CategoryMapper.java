package com.cookbook.domain.mapper;

import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.RecipeEntity;

import java.util.ArrayList;
import java.util.List;


public class CategoryMapper {

    public static CategoryDTO categoryEntityToDto(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCreatedDate(entity.getCreatedDate());
        categoryDTO.setLastModified(entity.getLastModified());
        categoryDTO.setDeleted(entity.isDeleted());
        categoryDTO.setCategoryId(entity.getCategoryId());
        List<RecipeEntity> list = entity.getRecipeEntities();
        categoryDTO.setName(entity.getName());
        if (list != null) {
            categoryDTO.setRecipeEntities(new ArrayList<RecipeEntity>(list));
        }
        return categoryDTO;
    }


    public static CategoryEntity categoryDtoToEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCreatedDate(dto.getCreatedDate());
        categoryEntity.setLastModified(dto.getLastModified());
        categoryEntity.setDeleted(dto.isDeleted());
        categoryEntity.setCategoryId(dto.getCategoryId());
        categoryEntity.setName(dto.getName());
        List<RecipeEntity> list = dto.getRecipeEntities();
        if (list != null) {
            categoryEntity.setRecipeEntities(new ArrayList<RecipeEntity>(list));
        }

        return categoryEntity;
    }


    public static CategoryEntity categoryRequestToEntity(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCreatedDate(categoryRequest.getCreatedDate());
        categoryEntity.setLastModified(categoryRequest.getLastModified());
        categoryEntity.setDeleted(categoryRequest.isDeleted());
        categoryEntity.setName(categoryRequest.getName());
        List<RecipeEntity> list = categoryRequest.getRecipeEntities();
        if (list != null) {
            categoryEntity.setRecipeEntities(new ArrayList<RecipeEntity>(list));
        }

        return categoryEntity;
    }
}