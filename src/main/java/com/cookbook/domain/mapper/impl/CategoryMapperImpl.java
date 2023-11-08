package com.cookbook.domain.mapper.impl;

import com.cookbook.domain.dto.CategoryDTO;
import com.cookbook.domain.dto.CategoryRequest;
import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.mapper.CategoryMapper;
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public CategoryDTO categoryEntityToDto(CategoryEntity entity) {
        if(entity == null){
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(entity.getCategoryId());
        categoryDTO.setName(entity.getName());
        categoryDTO.setDeleted(entity.isDeleted());
        categoryDTO.setCreatedDate(entity.getCreatedDate());
        categoryDTO.setLastModified(entity.getLastModified());
        categoryDTO.setRecipeEntities(entity.getRecipeEntities());
        return categoryDTO;
    }

    @Override
    public CategoryEntity categoryDtoToEntity(CategoryDTO dto) {
        if(dto == null){
            return null;
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(dto.getCategoryId());
        categoryEntity.setName(dto.getName());
        categoryEntity.setDeleted(dto.isDeleted());
        categoryEntity.setRecipeEntities(dto.getRecipeEntities());
        categoryEntity.setLastModified(dto.getLastModified());
        categoryEntity.setCreatedDate(dto.getCreatedDate());
        return categoryEntity;
    }
    @Override
    public CategoryEntity categoryRequestToEntity(CategoryRequest categoryRequest){
        CategoryEntity category = new CategoryEntity();
        category.setCreatedDate(categoryRequest.getCreatedDate());
        category.setRecipeEntities(categoryRequest.getRecipeEntities());
        category.setName(categoryRequest.getName());
        category.setDeleted(categoryRequest.isDeleted());
        category.setLastModified(categoryRequest.getLastModified());
        return category;
    }

}
