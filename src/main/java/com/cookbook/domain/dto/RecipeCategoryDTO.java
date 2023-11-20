package com.cookbook.domain.dto;

import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.RecipeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCategoryDTO extends BaseDomainDTO{
        private RecipeEntity recipeEntity;
        private CategoryEntity categoryEntity;

    public RecipeCategoryDTO(LocalDateTime createdDate, LocalDateTime lastModified, boolean deleted, RecipeEntity recipeEntity, CategoryEntity categoryEntity) {
        super(createdDate, lastModified, deleted);
        this.recipeEntity = recipeEntity;
        this.categoryEntity = categoryEntity;
    }

    }